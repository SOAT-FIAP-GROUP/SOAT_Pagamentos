package faculdade.mercadopago.gateway.adapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "ProducaoFeingClient", url = "${services.producao.url}")
public interface ProducaoFeingClient {
    @PostMapping("/api/fila/adicionar/{id}")
    void adicionarNaFila(@PathVariable Long id);
}