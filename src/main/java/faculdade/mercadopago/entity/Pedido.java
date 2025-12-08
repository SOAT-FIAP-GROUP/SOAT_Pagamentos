package faculdade.mercadopago.entity;

import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public record Pedido(Long id, String idUsuario, StatusPedidoEnum status, BigDecimal valorTotal,
                     LocalDateTime dataHoraSolicitacao, Time tempoTotalPreparo, List<PedidoItem> itens) {
}
