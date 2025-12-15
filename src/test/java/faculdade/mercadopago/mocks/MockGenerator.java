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
                null,           // accounts_info
                List.of(),                  // acquirer_reconciliation
                null,                       // additional_info
                null,                       // authorization_code
                false,                      // binary_mode
                null,                       // brand_id
                null,                       // build_version
                null,                       // call_for_authorize_id
                null,                       // callback_url
                true,                       // captured
                null,                       // card (@JsonIgnore)
                List.of(),                  // charges_details
                null,                       // collector_id
                null,                       // corporation_id
                "BRL",                      // counter_currency
                0.0,                        // coupon_amount
                "BRL",                      // currency_id
                null,                       // date_approved
                null,                       // date_created
                null,                       // date_last_updated
                null,                       // date_of_expiration
                null,                       // deduction_schema
                null,                       // description
                null,                       // differential_pricing_id
                "999",                      // external_reference
                List.of(),                  // fee_details
                null,                       // financing_group
                123L,                       // id (mercadopagoIdpagamento)
                1,                          // installments
                null,                       // integrator_id
                null,                       // issuer_id
                true,                       // live_mode
                null,                       // marketplace_owner
                null,                       // merchant_account_id
                null,                       // merchant_number
                null,                       // metadata
                null,                       // money_release_date
                null,                       // money_release_schema
                null,                       // money_release_status
                null,                       // notification_url
                null,                       // operation_type
                null,                       // order (@JsonIgnore)
                null,                       // payer
                null,                       // payment_method
                null,                       // payment_method_id
                null,                       // payment_type_id
                null,                       // platform_id
                null,                       // point_of_interaction
                null,                       // pos_id
                null,                       // processing_mode
                List.of(),                  // refunds
                null,                       // release_info
                0.0,                        // shipping_amount
                null,                       // sponsor_id
                null,                       // statement_descriptor
                "approved",                 // status
                null,                       // status_detail
                null,                       // store_id
                null,                       // tags
                0.0,                        // taxes_amount
                0.0,                        // transaction_amount
                0.0,                        // transaction_amount_refunded
                null                        // transaction_details
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
