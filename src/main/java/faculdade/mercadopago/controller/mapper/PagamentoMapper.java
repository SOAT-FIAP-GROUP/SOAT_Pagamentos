package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.PagamentoStatusResponse;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import org.springframework.http.ResponseEntity;

public class PagamentoMapper {
    public static QrCodeResponse toResponse(QrCodeRes entity) {
        return new QrCodeResponse(
                entity.in_store_order_id(),
                entity.qr_data()
        );
    }

    public static PagamentoStatusResponse toResponse(ResponseEntity<?> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao consultar o pagamento ");
        }
        ConfirmacaoPagamentoRes body = (ConfirmacaoPagamentoRes) response.getBody();
        if (body == null) {
            throw new RuntimeException("Mercado Pago retornou uma resposta vazia");
        }
        return new PagamentoStatusResponse(
                body.external_reference(),
                body.id(),
                body.status()
        );
    }
}