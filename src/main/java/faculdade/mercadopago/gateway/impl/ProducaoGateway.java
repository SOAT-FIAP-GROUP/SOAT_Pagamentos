package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.gateway.IProducaoGateway;
import faculdade.mercadopago.gateway.adapter.PedidoFeingClient;
import faculdade.mercadopago.gateway.adapter.ProducaoFeingClient;

public class ProducaoGateway implements IProducaoGateway {

    private final ProducaoFeingClient producaoRepository;

    public ProducaoGateway(ProducaoFeingClient producaoRepository) {
        this.producaoRepository = producaoRepository;
    }

    @Override
    public void adicionarPedidoNaFila(Long id) {
        producaoRepository.adicionarNaFila(id);
    }
}