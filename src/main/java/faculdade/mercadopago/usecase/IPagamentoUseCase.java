package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.Pagamento;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;

public interface IPagamentoUseCase {
    QrCodeRes processarQrCode(QrCodeRequest request);

    void salvarPagamento(Pedido pedido, Double valor);

    ConfirmacaoPagamentoRes consultarPagamento(String id);

    void removerPagamento(Pedido pedido);
}