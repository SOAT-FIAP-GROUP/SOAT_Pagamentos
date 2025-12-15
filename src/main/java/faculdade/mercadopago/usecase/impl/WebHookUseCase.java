package faculdade.mercadopago.usecase.impl;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.exception.EntityNotFoundException;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.IWebHookUseCase;

import java.math.BigDecimal;

public class WebHookUseCase implements IWebHookUseCase {

    public final IPagamentoUseCase pagamentoUseCase;
    public final IPagamentoGateway pagamentoGateway;
    public final IPedidoGateway pedidoGateway;

    private static final String STATUS_APROVADO = "approved";


    public WebHookUseCase(IPagamentoUseCase pagamentoUseCase, IPagamentoGateway pagamentoGateway, IPedidoGateway pedidoGateway) {
        this.pagamentoUseCase = pagamentoUseCase;
        this.pagamentoGateway = pagamentoGateway;
        this.pedidoGateway = pedidoGateway;
    }

    @Override
    public boolean confirmarPagamento(ConfirmacaoWebHookRequest request) {
        var response = pagamentoUseCase.consultarPagamento(request.id());
        String status = response.status();
        //status = "approved";
        return status.equals(STATUS_APROVADO);
    }


    @Override
    public DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request) {
        var body = pagamentoUseCase.consultarPagamento(request.id());
        String codigo = body.external_reference();
        double valorPago = 0.0;
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
        BigDecimal valor = BigDecimal.valueOf(dados.valorPago());
        var pedido = pedidoGateway.findById(id).orElseThrow(() -> new EntityNotFoundException(Pedido.class, id));
        pagamentoUseCase.salvarPagamento(pedido, valor);
        pedidoGateway.alterarStatus(id, StatusPedidoEnum.EM_PREPARACAO);
        pedidoGateway.adicionarPedidoNaFila(id);
    }
}