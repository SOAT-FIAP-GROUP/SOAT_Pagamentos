package faculdade.mercadopago.gateway.persistence;

import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagamentoRepository extends MongoRepository<PagamentoEntity, Long> {
}