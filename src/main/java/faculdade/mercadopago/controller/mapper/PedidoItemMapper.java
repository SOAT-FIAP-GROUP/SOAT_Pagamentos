package faculdade.mercadopago.controller.mapper;

import faculdade.mercadopago.controller.mapper.dto.response.PedidoItemResponse;
import faculdade.mercadopago.entity.PedidoItem;
import faculdade.mercadopago.gateway.entity.PedidoItemEntity;

public class PedidoItemMapper {

    public static PedidoItemResponse toResponse(PedidoItem entity) {
        return new PedidoItemResponse(entity.id(), entity.pedidoId(), entity.produtoId(), entity.quantidade(), entity.precoUnitario(), entity.precoTotal());
    }

    public static PedidoItemEntity toEntityPersistence(PedidoItem pedidoItem) {
        return new PedidoItemEntity(pedidoItem.id(), pedidoItem.pedidoId(), pedidoItem.produtoId(), pedidoItem.quantidade(), pedidoItem.precoUnitario(), pedidoItem.precoTotal());
    }

    public static PedidoItem toModel(PedidoItemEntity pedidoItem) {
        return new PedidoItem(pedidoItem.getId(), pedidoItem.getPedidoId(), pedidoItem.getProdutoId(), pedidoItem.getQuantidade(), pedidoItem.getPrecoUnitario(), pedidoItem.getPrecoTotal());
    }
}
