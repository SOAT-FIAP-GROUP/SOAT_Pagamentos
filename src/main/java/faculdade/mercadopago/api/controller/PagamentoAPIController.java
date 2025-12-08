package faculdade.mercadopago.api.controller;

import faculdade.mercadopago.controller.PagamentoController;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.controller.mapper.dto.response.PagamentoStatusResponse;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pagamento")
public class PagamentoAPIController {

    private final PagamentoController pagamentoController;

    public PagamentoAPIController(PagamentoController pagamentoController) {
        this.pagamentoController = pagamentoController;
    }

    @Operation(summary = "Gerar um Qr Code", description = "Retorna uma string do Qr Code")
    @PostMapping
    public ResponseEntity<QrCodeResponse> gerarQrCode(@RequestBody QrCodeRequest request) throws Exception {
        var response = pagamentoController.gerarQrCode(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Consultar Pagamento", description = "Retorna o status de um pagamento ao passar o id gerado pelo Mercadopago")
    @GetMapping
    public ResponseEntity<PagamentoStatusResponse> consultarPagamento(@RequestParam String id) throws Exception {
        var response = pagamentoController.consultar(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}