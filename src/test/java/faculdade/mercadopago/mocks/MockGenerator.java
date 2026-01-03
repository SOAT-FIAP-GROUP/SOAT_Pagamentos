package faculdade.mercadopago.mocks;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PagamentoStatusResponse;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public class MockGenerator {

    private static final Long ID = 1L;

    public static QrCodeRequest generateQrCodeRequestMock() {
        return new QrCodeRequest(
                123L,
                100.0,
                List.of(new QrCodeRequest.ItemPedido(ID, 2, BigDecimal.valueOf(50)))
        );
    }

    public static QrCodeRes generateQrCodeResMock() {
        return new QrCodeRes(
                "mocked_qrcode",
                "mocked_url"
        );
    }

    public static PagamentoStatusResponse generatePagamentoStatusResponse() {
        return new PagamentoStatusResponse(
                "999",
                123L,
                "approved"
        );
    }

    public static ConfirmacaoPagamentoRes generateConfirmacaoPagamentoResMock() {
        return new ConfirmacaoPagamentoRes(
                123L,
                "approved",
                "999",
                new ConfirmacaoPagamentoRes.TransactionDetails(
                        100.00
                ),
                100.00
        );

    }

    public static ConfirmacaoWebHookRequest generateConfirmacaoWebHookRequestMock() {
        return new ConfirmacaoWebHookRequest(
                "1",
                true,
                "payment",
                "2025-01-01T10:00:00Z",
                999L,
                "v1",
                "payment.updated",
                new ConfirmacaoWebHookRequest.PaymentData("1")
        );
    }

    public static Pedido generatePedido() {
        return new Pedido(
                999L,
                "user-123",
                StatusPedidoEnum.RECEBIDO,
                new BigDecimal("89.90"),
                LocalDateTime.of(2025, 1, 1, 12, 0),
                Time.valueOf("00:30:00"),
                List.of()
        );
    }
}
