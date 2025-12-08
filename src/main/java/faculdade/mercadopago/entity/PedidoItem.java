package faculdade.mercadopago.entity;

import java.math.BigDecimal;

public record PedidoItem(Long id, Long pedidoId, Long produtoId, int quantidade, BigDecimal precoUnitario,
                         BigDecimal precoTotal) {

}
