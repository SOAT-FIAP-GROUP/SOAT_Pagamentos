package faculdade.mercadopago.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import faculdade.mercadopago.entity.PedidoItem;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode(of = "codigo")
@NoArgsConstructor
@AllArgsConstructor
public class PedidoItemEntity {

    private Long codigo;

    @JsonIgnore
    private PedidoEntity pedido;

    @JsonIgnore
    private Long produtoCodigo;

    private int quantidade;

    private BigDecimal precoUnitario;

    private BigDecimal precoTotal;


    public PedidoItem toModel() {
        return new PedidoItem(this.codigo, this.pedido.getCodigo(), this.produtoCodigo, this.quantidade, this.precoUnitario, this.precoTotal);
    }

}
