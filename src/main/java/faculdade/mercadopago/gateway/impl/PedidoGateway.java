package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.gateway.adapter.PedidoFeingClient;
import faculdade.mercadopago.gateway.entity.PedidoEntity;

import java.util.Optional;

public class PedidoGateway implements IPedidoGateway {

    private final PedidoFeingClient pedidoRepository;

    public PedidoGateway(PedidoFeingClient pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public Optional<Pedido> findById(Long id) {
        return pedidoRepository.buscarPedido(id).map(PedidoEntity::toModel);
    }

    @Override
    public void alterarStatus(Long id, StatusPedidoEnum status) {
        pedidoRepository.alterarStatus(id, status);
    }

    @Override
    public void adicionarPedidoNaFila(Long id) {
        pedidoRepository.adicionarNaFila(id);
    }


}
