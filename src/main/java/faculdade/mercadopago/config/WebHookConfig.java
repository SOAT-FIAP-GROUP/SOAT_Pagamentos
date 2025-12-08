package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.WebHookAPIController;
import faculdade.mercadopago.controller.WebHookController;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.IWebHookUseCase;
import faculdade.mercadopago.usecase.impl.WebHookUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebHookConfig {
    @Bean
    WebHookAPIController webHookAPIController(WebHookController webHookController) {
        return new WebHookAPIController(webHookController);
    }

    @Bean
    WebHookController webHookController(IWebHookUseCase webHookUseCase) {
        return new WebHookController(webHookUseCase);
    }

    @Bean
    WebHookUseCase webHookUseCase(IPagamentoUseCase pagamentoUseCase, IPagamentoGateway pagamentoGateway, IPedidoGateway pedidoGateway) {
        return new WebHookUseCase(
                pagamentoUseCase,
                pagamentoGateway,
                pedidoGateway
        );
    }
}