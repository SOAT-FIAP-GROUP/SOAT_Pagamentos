package faculdade.mercadopago.gateway;

import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface IPagamentoGateway {

    <T, R> ResponseEntity<R> sendRequest(String url,
                                         HttpMethod method,
                                         T request,
                                         Class<R> responseType,
                                         Map<String, String> extraHeaders);

    <R> ResponseEntity<?> sendRequest(
            String url,
            HttpMethod method,
            Class<R> responseType
    );


    PagamentoEntity save(Pedido pedido, Double valor);

    void remove(String pedidoId);
}