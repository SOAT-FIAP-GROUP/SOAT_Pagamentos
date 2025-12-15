package faculdade.mercadopago.controller.mapper.dto.response;

public record PagamentoStatusResponse(
        String pedidoId,
        Long mercadoPagoIdPagamento,
        String status
) {
}
