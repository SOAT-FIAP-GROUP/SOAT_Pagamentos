package faculdade.mercadopago.gateway.entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "pagamentos")
public class PagamentoEntity {

    @Id
    private Long id;

    @NonNull
    private PedidoEntity pedidoId;

    @NonNull
    private BigDecimal valor;

    @NonNull
    private String status;

    @NonNull
    private LocalDateTime dataPagamento;
}
