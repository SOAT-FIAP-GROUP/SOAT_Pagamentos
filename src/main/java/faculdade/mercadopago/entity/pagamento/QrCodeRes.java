package faculdade.mercadopago.entity.pagamento;

public record QrCodeRes(
        String in_store_order_id,
        String qr_data
) {
}