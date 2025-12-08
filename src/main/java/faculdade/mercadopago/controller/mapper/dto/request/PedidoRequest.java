package faculdade.mercadopago.controller.mapper.dto.request;

import java.util.List;

public record PedidoRequest(String idUsuario, List<PedidoItemRequest> itens) {
}
