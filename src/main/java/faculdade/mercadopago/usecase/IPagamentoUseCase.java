package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ConcurrentModificationException;

public interface IPagamentoUseCase {
    QrCodeRes processarQrCode(QrCodeRequest request);

    void salvarPagamento(Pedido pedido, BigDecimal valor);


    ConfirmacaoPagamentoRes consultarPagamento(String id);
}