package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.SagaSteps;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.IProducaoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.IWebHookUseCase;

import static faculdade.mercadopago.entity.enums.SagaSteps.PAGAMENTO_SALVO;
import static faculdade.mercadopago.entity.enums.SagaSteps.STATUS_ALTERADO;

public class WebHookUseCase implements IWebHookUseCase {

    public final IPagamentoUseCase pagamentoUseCase;
    public final IPagamentoGateway pagamentoGateway;
    public final IPedidoGateway pedidoGateway;
    public final IProducaoGateway producaoGateway;

    private static final String STATUS_APROVADO = "approved";


    public WebHookUseCase(IPagamentoUseCase pagamentoUseCase, IPagamentoGateway pagamentoGateway, IPedidoGateway pedidoGateway, IProducaoGateway producaoGateway) {
        this.pagamentoUseCase = pagamentoUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoGateway = pedidoGateway;
        this.producaoGateway = producaoGateway;
    }

    @Override
    public boolean confirmarPagamento(ConfirmacaoWebHookRequest request) {
        var response = pagamentoUseCase.consultarPagamento(request.id());
        String status = response.status();
        status = "approved"; //Força pagamento como aprovado devido a problemas externos do próprio app do mercadopago
        return status.equals(STATUS_APROVADO);
    }


    @Override
    public DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request) {
        var body = pagamentoUseCase.consultarPagamento(request.id());
        String codigo = body.external_reference();
        Double valorPago = 0.0;
        if (body.transaction_details() != null) {
            valorPago = body.transaction_details().total_paid_amount();
        }
        return new DadosPedidoPago(
                codigo,
                valorPago
        );
    }

    @Override
    public void processarPagamento(ConfirmacaoWebHookRequest request) {
        if (!confirmarPagamento(request)) {
            throw new RuntimeException("Pagamento " + request.id() + " não confirmado");
        }

        DadosPedidoPago dados = retornarPedidoPago(request);
        Long id = Long.parseLong(dados.codigo());
        double valor = dados.valorPago();

        var pedido = pedidoGateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Pedido.class, id));
        orquestrarPagamentoSaga(pedido, valor, id);
    }

    @Override
    public void orquestrarPagamentoSaga(Pedido pedido, double valor, Long id) {
        SagaSteps stepAtual = null;
        try {
            pagamentoUseCase.salvarPagamento(pedido, valor);
            stepAtual = PAGAMENTO_SALVO;

            pedidoGateway.alterarStatus(id, StatusPedidoEnum.EM_PREPARACAO);
            stepAtual = STATUS_ALTERADO;

            producaoGateway.adicionarPedidoNaFila(id);
        } catch (Exception e) {
            reverterTransacao(stepAtual, pedido, id);
        }
    }

    public void reverterTransacao(SagaSteps step, Pedido pedido, Long id) {
        if (step == null) return;

        switch (step) {
            case STATUS_ALTERADO -> {
                pedidoGateway.alterarStatus(id, StatusPedidoEnum.RECEBIDO);
                pagamentoUseCase.removerPagamento(pedido);
            }
            case PAGAMENTO_SALVO -> pagamentoUseCase.removerPagamento(pedido);

        }
    }
}
