package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.PagamentoStatusResponse;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;

public class PagamentoMapper {
    public static QrCodeResponse toResponse(QrCodeRes entity) {
        return new QrCodeResponse(
                entity.in_store_order_id(),
                entity.qr_data()
        );
    }

    public static PagamentoStatusResponse toResponse(ConfirmacaoPagamentoRes response) {
        return new PagamentoStatusResponse(
                response.external_reference(),
                response.id(),
                response.status()
        );
    }
}