package faculdade.mercadopago.controller.mapper.dto.request;

import java.math.BigDecimal;
import java.util.List;

public record QrCodeRequest(
        Long OrderId,
        double TotalAmount,
        List<QrCodeRequest.ItemPedido> Itens
) {
    public record ItemPedido(
            Long Codigo,
            Integer quantidade,
            BigDecimal Valor
    ) {
    }
}