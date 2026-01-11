package faculdade.mercadopago.gateway.persistence;

import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import faculdade.mercadopago.gateway.entity.PedidoEntity;

public interface IPagamentoRepository {
    PagamentoEntity save(PagamentoEntity pagamento);

    void remove(String pedidoId);
}