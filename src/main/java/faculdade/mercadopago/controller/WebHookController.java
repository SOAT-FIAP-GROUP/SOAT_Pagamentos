package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.usecase.IWebHookUseCase;

public class WebHookController {
    private final IWebHookUseCase webHookUseCase;

    public WebHookController(IWebHookUseCase webHookUseCase) {
        this.webHookUseCase = webHookUseCase;
    }

    public void confirmarPagamento(ConfirmacaoWebHookRequest request) {
        webHookUseCase.processarPagamento(request);
    }
}
