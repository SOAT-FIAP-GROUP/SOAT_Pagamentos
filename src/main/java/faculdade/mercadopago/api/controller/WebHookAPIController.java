package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.WebHookController;
import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebHookAPIController {

    private final WebHookController webhookController;

    public WebHookAPIController(WebHookController webhookController) {
        this.webhookController = webhookController;
    }


    @Operation(summary = "Confirmar pagamento Mercado Pago", description = "Recebe a confirmação de pagamento recebido pelo mercado pago")
    @PostMapping(path = "/mercadopago/confirmapagamento")
    public ResponseEntity<Void> confirmaPagamento(@RequestBody @Valid ConfirmacaoWebHookRequest request) {
        webhookController.confirmarPagamento(request);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}