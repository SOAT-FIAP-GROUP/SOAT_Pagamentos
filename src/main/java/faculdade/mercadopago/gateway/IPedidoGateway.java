package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;

import java.util.Optional;

public interface IPedidoGateway {
    Optional<Pedido> findById(Long id);

    void alterarStatus(Long id, StatusPedidoEnum status);
}
