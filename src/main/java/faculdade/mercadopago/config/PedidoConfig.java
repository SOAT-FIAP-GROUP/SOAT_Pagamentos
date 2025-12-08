package faculdade.mercadopago.config;

import faculdade.mercadopago.controller.mapper.PedidoItemMapper;
import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.gateway.adapter.PedidoFeingClient;
import faculdade.mercadopago.gateway.impl.PedidoGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PedidoConfig {

    @Bean
    PedidoMapper pedidoMapper() {
        return new PedidoMapper();
    }

    @Bean
    PedidoItemMapper pedidoItemMapper() {
        return new PedidoItemMapper();
    }

    @Bean
    PedidoGateway pedidoGateway(PedidoFeingClient pedidoFeingClient) {
        return new PedidoGateway(pedidoFeingClient);
    }

}
