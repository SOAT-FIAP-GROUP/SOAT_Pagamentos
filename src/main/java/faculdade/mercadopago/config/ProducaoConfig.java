package faculdade.mercadopago.config;

import faculdade.mercadopago.gateway.adapter.ProducaoFeingClient;
import faculdade.mercadopago.gateway.impl.ProducaoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProducaoConfig {
    @Bean
    ProducaoGateway producaoGateway(ProducaoFeingClient producaoFeingClient) {
        return new ProducaoGateway(producaoFeingClient);
    }

}