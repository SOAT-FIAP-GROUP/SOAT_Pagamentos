package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;

public interface IWebHookUseCase {
    boolean confirmarPagamento(ConfirmacaoWebHookRequest request);

    DadosPedidoPago retornarPedidoPago(ConfirmacaoWebHookRequest request);

    void orquestrarPagamentoSaga(Pedido pedido, double valor, Long id);

    void processarPagamento(ConfirmacaoWebHookRequest request);
}