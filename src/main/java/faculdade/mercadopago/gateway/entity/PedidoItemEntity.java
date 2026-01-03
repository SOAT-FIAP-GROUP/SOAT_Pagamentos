package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class PedidoItemEntity {

    private Long id;

    @JsonIgnore
    private Long pedidoId;

    @JsonIgnore
    private Long produtoId;

    private int quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;

}
