package faculdade.mercadopago.entity.pagamento;

public record ConfirmacaoPagamentoRes(
        Long id,
        String status,
        String external_reference,
        TransactionDetails transaction_details,
        Double transaction_amount
) {
    public record TransactionDetails(
            Double total_paid_amount
    ) {
    }
}