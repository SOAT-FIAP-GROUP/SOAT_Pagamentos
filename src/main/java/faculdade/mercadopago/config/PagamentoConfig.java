package faculdade.mercadopago.config;

import faculdade.mercadopago.api.controller.PagamentoAPIController;
import faculdade.mercadopago.controller.PagamentoController;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.adapter.PedidoFeingClient;
import faculdade.mercadopago.gateway.impl.PagamentoGateway;
import faculdade.mercadopago.gateway.impl.PagamentoRepository;
import faculdade.mercadopago.gateway.persistence.IPagamentoRepository;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import faculdade.mercadopago.usecase.impl.PagamentoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;

@Configuration
public class PagamentoConfig {
    @Bean
    PagamentoAPIController pagamentoAPIController(PagamentoController pagamentoController) {
        return new PagamentoAPIController(pagamentoController);
    }

    @Bean
    PagamentoController pagamentoController(IPagamentoUseCase pagamentoUseCase) {
        return new PagamentoController(pagamentoUseCase);
    }

    @Bean
    PagamentoUseCase pagamentoUseCase(IPagamentoGateway pagamentoGateway) {
        return new PagamentoUseCase(pagamentoGateway);
    }

    @Bean
    PagamentoGateway pagamentoGateway(IPagamentoRepository IPagamentoRepository, PedidoFeingClient pedidoRepository) {
        return new PagamentoGateway(IPagamentoRepository, new RestTemplate());
    }

    @Bean
    PagamentoRepository pagamentoRepository(DynamoDbEnhancedClient dynamoDBEnhancedClient) {
        return new PagamentoRepository(dynamoDBEnhancedClient);
    }
}