package faculdade.mercadopago.controller.mapper.dto.response;

public record QrCodeResponse(
        String in_store_order_id,
        String qr_data
) {
}