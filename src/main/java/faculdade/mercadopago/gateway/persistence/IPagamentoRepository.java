package faculdade.mercadopago.gateway.persistence;

import faculdade.mercadopago.gateway.entity.PagamentoEntity;

public interface IPagamentoRepository {
    PagamentoEntity save(PagamentoEntity pagamento);
}