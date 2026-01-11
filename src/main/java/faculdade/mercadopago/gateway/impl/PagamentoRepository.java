package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import faculdade.mercadopago.gateway.persistence.IPagamentoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.*;

@Repository
public class PagamentoRepository implements IPagamentoRepository {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<PagamentoEntity> pagamentoTable;

    public PagamentoRepository(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
    }

    @PostConstruct
    public void init() {
        pagamentoTable = dynamoDbEnhancedClient.table("Pagamento", TableSchema.fromBean(PagamentoEntity.class));
    }

    @Override
    public PagamentoEntity save(PagamentoEntity pagamento) {
        pagamentoTable.putItem(r -> r
                .item(pagamento)
                .conditionExpression(
                        Expression.builder()
                                .expression("attribute_not_exists(pedidoId)")
                                .build()));
        return pagamento;
    }

    @Override
    public void remove(String pedido) {
        pagamentoTable.deleteItem(Key.builder().
                partitionValue(pedido
                ).build()
        );
    }
}
