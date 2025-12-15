package faculdade.mercadopago.entity.pagamento;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public record ConfirmacaoPagamentoRes(
        Object accounts_info,
        List<Object> acquirer_reconciliation,
        AdditionalInfo additional_info,
        String authorization_code,
        boolean binary_mode,
        String brand_id,
        String build_version,
        String call_for_authorize_id,
        String callback_url,
        boolean captured,
        @JsonIgnore
        Card card,
        List<ChargesDetail> charges_details,
        Long collector_id,
        String corporation_id,
        String counter_currency,
        double coupon_amount,
        String currency_id,
        String date_approved,
        String date_created,
        String date_last_updated,
        String date_of_expiration,
        String deduction_schema,
        String description,
        String differential_pricing_id,
        String external_reference,
        List<Object> fee_details,
        String financing_group,
        Long id,
        int installments,
        String integrator_id,
        String issuer_id,
        boolean live_mode,
        String marketplace_owner,
        String merchant_account_id,
        String merchant_number,
        Map<String, Object> metadata,
        String money_release_date,
        String money_release_schema,
        String money_release_status,
        String notification_url,
        String operation_type,
        @JsonIgnore
        Order order,
        Payer payer,

        PaymentMethod payment_method,

        String payment_method_id,
        String payment_type_id,
        String platform_id,

        PointOfInteraction point_of_interaction,

        String pos_id,
        String processing_mode,
        List<Object> refunds,
        String release_info,
        double shipping_amount,
        String sponsor_id,
        String statement_descriptor,
        String status,
        String status_detail,
        String store_id,
        String tags,
        double taxes_amount,
        double transaction_amount,
        double transaction_amount_refunded,
        TransactionDetails transaction_details

) {
    public record AdditionalInfo(
            String tracking_id
    ) {
    }

    public record Card() {
    }

    public record Order() {
    }

    public record Payer(
            Identification identification,
            String entity_type,
            Phone phone,
            String last_name,
            String id,
            String type,
            String first_name,
            String email
    ) {
        public record Identification(
                String number,
                String type
        ) {
        }

        public record Phone(
                String number,
                String extension,
                String area_code) {
        }
    }

    public record PaymentMethod(
            String id,
            String issuer_id,
            String type
    ) {
    }

    public record PointOfInteraction(
            ApplicationData application_data,
            BusinessInfo business_info,
            Location location,
            TransactionData transaction_data,
            String type
    ) {
        public record ApplicationData(
                String name,
                String operating_system,
                String version
        ) {
        }

        public record BusinessInfo(
                String branch,
                String sub_unit,
                String unit
        ) {
        }

        public record Location(
                String source,
                String state_id
        ) {
        }

        public record TransactionData(
                BankInfo bank_info,
                String bank_transfer_id,
                String e2e_id,
                String financial_institution,
                InfringementNotification infringement_notification,

                String qr_code,
                String qr_code_base64,
                String ticket_url,
                String transaction_id
        ) {
            public record BankInfo(
                    Account collector,
                    Boolean is_same_bank_account_owner,
                    String origin_bank_id,
                    String origin_wallet_id,
                    Account payer
            ) {
                public record Account(
                        String account_holder_name,
                        String account_id,
                        String long_name,
                        String transfer_account_id,
                        String branch,
                        String external_account_id,
                        String id,
                        Identification identification,
                        Boolean is_end_consumer
                ) {
                    public record Identification(
                            String number,
                            String type
                    ) {
                    }
                }
            }

            public record InfringementNotification(
                    String status,
                    String type
            ) {
            }
        }
    }

    public record TransactionDetails(
            String acquirer_reference,
            String bank_transfer_id,
            String external_resource_url,
            String financial_institution,
            double installment_amount,
            double net_received_amount,
            double overpaid_amount,
            String payable_deferral_period,
            String payment_method_reference_id,
            double total_paid_amount,
            String transaction_id
    ) {
    }

    public record ChargesDetail(
            Accounts accounts,
            Amounts amounts,
            long client_id,
            String date_created,
            String id,
            String last_updated,
            Metadata metadata,
            String name,
            List<Object> refund_charges,
            String reserve_id,
            String type
    ) {
        public record Accounts(
                String from,
                String to
        ) {
        }

        public record Amounts(
                double original,
                double refunded
        ) {
        }

        public record Metadata(
                String reason,
                String source
        ) {
        }
    }
}

