package faculdade.mercadopago.controller.mapper.dto.request;

public record ConfirmacaoWebHookRequest(
        String id,
        Boolean live_mode,
        String type,
        String date_created,
        Long user_id,
        String api_version,
        String action,
        ConfirmacaoWebHookRequest.PaymentData data
) {
    public record PaymentData(
            String id
    ) {
    }
}