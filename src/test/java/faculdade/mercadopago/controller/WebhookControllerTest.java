package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.IWebHookUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WebhookControllerTest {

    private WebHookController webHookController;
    private IWebHookUseCase webHookUseCase;

    @BeforeEach
    void setUp() {
        webHookUseCase = mock(IWebHookUseCase.class);
        webHookController = new WebHookController(webHookUseCase);
    }


    @Test
    void deveProcessarPagamentoComSucesso() {
        ConfirmacaoWebHookRequest request = MockGenerator.generateConfirmacaoWebHookRequestMock();
        webHookController.confirmarPagamento(request);
        verify(webHookUseCase, times(1)).processarPagamento(request);
        verifyNoMoreInteractions(webHookUseCase);
    }
}
