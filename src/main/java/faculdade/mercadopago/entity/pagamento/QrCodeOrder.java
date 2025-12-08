package faculdade.mercadopago.entity.pagamento;

import java.math.BigDecimal;
import java.util.List;

public record QrCodeOrder(
        String external_reference,
        String title,
        String description,
        Double total_amount,
        String notification_url,
        List<QrCodeOrder.Item> items
) {
    public record Item(
            String sku_number,
            String category,
            String title,
            String description,
            Integer quantity,
            String unit_measure,
            BigDecimal unit_price,
            BigDecimal total_amount
    ) {
    }
}