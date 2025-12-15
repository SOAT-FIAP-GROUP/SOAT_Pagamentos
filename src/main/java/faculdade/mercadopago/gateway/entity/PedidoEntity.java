package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"codigo"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PedidoEntity {

    private Long codigo;
    private String usuario;
    private StatusPedidoEnum status;
    private BigDecimal valorTotal;
    private LocalDateTime dataHoraSolicitacao;
    private Time tempoTotalPreparo;
    private List<PedidoItemEntity> itens;

    public PedidoEntity(Long codigo) {
        this.codigo = codigo;
    }

    public Pedido toModel() {
        return new Pedido(
                codigo,
                usuario != null ? usuario : null,
                status,
                valorTotal,
                dataHoraSolicitacao,
                tempoTotalPreparo,
                itens != null
                        ? itens.stream().map(PedidoItemEntity::toModel).toList()
                        : List.of()
        );
    }
}
