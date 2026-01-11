package faculdade.mercadopago.gateway.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@DynamoDbBean
public class PagamentoEntity {

    private String id;

    @NonNull
    private String pedidoId;

    @NonNull
    private double valor;

    @NonNull
    private String status;

    @NonNull
    private String dataPagamento;

    @DynamoDbPartitionKey
    public String getPedidoId() {
        return pedidoId;
    }
}
