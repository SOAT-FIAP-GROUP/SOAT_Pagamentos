package faculdade.mercadopago.controller.mapper.dto.response;

import java.math.BigDecimal;

public record PedidoItemResponse(Long id, Long pedidoId, Long produtoId, int quantidade, BigDecimal precoUnitario,
                                 BigDecimal precoTotal) {
}
