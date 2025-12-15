package faculdade.mercadopago.gateway.adapter;

import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.gateway.entity.PedidoEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "PedidoFeingClient", url = "${services.pedido.url}")
public interface PedidoFeingClient {
    @GetMapping("/pedidos/{id}")
    Optional<PedidoEntity> buscarPedido(@PathVariable Long id);

    @PutMapping("/pedidos/{id}/status")
    void alterarStatus(@PathVariable Long id, @RequestBody StatusPedidoEnum statusPedidoEnum);

    @PostMapping("/pedidos/{id}/fila")
    void adicionarNaFila(@PathVariable Long id);
}
