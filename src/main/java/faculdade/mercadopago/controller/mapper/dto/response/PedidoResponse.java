package faculdade.mercadopago.controller.mapper.dto.response;

import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(Long id, String idUsuario, StatusPedidoEnum status, BigDecimal valorTotal,
                             LocalDateTime dataHoraSolicitacao, Time tempoTotalPreparo,
                             List<PedidoItemResponse> itens) {
}
