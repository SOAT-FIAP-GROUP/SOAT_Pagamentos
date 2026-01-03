package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import faculdade.mercadopago.controller.mapper.PedidoItemMapper;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@DynamoDbBean
public class PedidoEntity {

    private Long id;
    private String idUsuario;
    private StatusPedidoEnum status;
    private BigDecimal valorTotal;
    private LocalDateTime dataHoraSolicitacao;
    private Time tempoTotalPreparo;
    private List<PedidoItemEntity> itens;

    public PedidoEntity(Long id) {
        this.id = id;
    }

    public Pedido toModel() {
        return new Pedido(
                id,
                idUsuario != null ? idUsuario : null,
                status,
                valorTotal,
                dataHoraSolicitacao,
                tempoTotalPreparo,
                itens != null
                        ? itens.stream().map(PedidoItemMapper::toModel).toList()
                        : List.of()
        );
    }
}
