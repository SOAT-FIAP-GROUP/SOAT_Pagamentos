package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.PedidoResponse;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.entity.PedidoEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PedidoMapper {

    public PedidoResponse toResponse(Pedido entity) {
        return new PedidoResponse(entity.id(), entity.idUsuario(), entity.status(), entity.valorTotal(), entity.dataHoraSolicitacao(),
                entity.tempoTotalPreparo(),
                entity.itens().stream().map(PedidoItemMapper::toResponse).toList());
    }

    public static PedidoEntity toEntityPersistence(Pedido pedido) {
        return new PedidoEntity(pedido.id(), pedido.idUsuario(), pedido.status(), pedido.valorTotal(), pedido.dataHoraSolicitacao(),
                pedido.tempoTotalPreparo(), pedido.itens().stream().map(PedidoItemMapper::toEntityPersistence).collect(Collectors.toCollection(ArrayList::new)));
    }
}
