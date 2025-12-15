package faculdade.mercadopago.gateway.impl;

import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.entity.PagamentoEntity;
import faculdade.mercadopago.gateway.persistence.PagamentoRepository;
import org.springframework.http.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class PagamentoGateway implements IPagamentoGateway {
    private final PagamentoRepository pagamentoRepository;
    private final RestTemplate _restTemplate;

    public PagamentoGateway(PagamentoRepository pagamentoRepository, RestTemplate restTemplate) {
        this.pagamentoRepository = pagamentoRepository;
        _restTemplate = restTemplate;
    }

    @Override
    public <T, R> ResponseEntity<R> sendRequest(String url, HttpMethod method, T request, Class<R> responseType, Map<String, String> extraHeaders) {
        try {
            var headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + AppConstants.ACCESS_TOKEN);
            headers.setContentType(MediaType.APPLICATION_JSON);

            if (extraHeaders != null) {
                extraHeaders.forEach(headers::set);
            }

            var entity = method == HttpMethod.GET || method == HttpMethod.DELETE
                    ? new HttpEntity<>(headers)
                    : new HttpEntity<>(request, headers);

            return _restTemplate.exchange(url, method, entity, responseType);

        } catch (HttpStatusCodeException ex) {
            throw new RuntimeException(
                    "Erro HTTP: " +
                            ex.getStatusCode() +
                            " - " +
                            ex.getResponseBodyAsString()
            );

        } catch (Exception ex) {
            throw new RuntimeException("Erro interno: " + ex.getMessage(), ex);
        }
    }

    @Override
    public <R> ResponseEntity<?> sendRequest(String url, HttpMethod method, Class<R> responseType) {
        var response = sendRequest(url, method, null, responseType, null);
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Erro ao consultar o pagamento ");
        }
        ConfirmacaoPagamentoRes body = (ConfirmacaoPagamentoRes) response.getBody();
        if (body == null) {
            throw new RuntimeException("Mercado Pago retornou uma resposta vazia");
        }
        return response;
    }

    @Override
    public PagamentoEntity save(Pedido pedido, BigDecimal valor) {
        var pedidoEntity = PedidoMapper.toEntityPersistence(pedido);
        String STATUS = "approved";
        var pagamento = new PagamentoEntity(
                pedidoEntity,
                valor,
                STATUS,
                LocalDateTime.now()
        );

        return pagamentoRepository.save(pagamento);
    }
}