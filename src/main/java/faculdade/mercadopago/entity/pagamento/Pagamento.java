package faculdade.mercadopago.entity.pagamento;

import faculdade.mercadopago.entity.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Pagamento(
        Pedido pedidoId,
        BigDecimal valor,
        String status,
        LocalDateTime dataPagamento
) {
}
