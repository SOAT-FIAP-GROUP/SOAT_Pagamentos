package faculdade.mercadopago.bdd;

import faculdade.mercadopago.controller.WebHookController;
import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.IWebHookUseCase;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.mockito.Mockito.*;

public class WebhookControllerBDDTest {

    private WebHookController webHookController;
    private IWebHookUseCase webHookUseCase;
    private ConfirmacaoWebHookRequest request;

    @Before
    public void setUp() {
        this.webHookUseCase = mock(IWebHookUseCase.class);
        this.webHookController = new WebHookController(this.webHookUseCase);
    }

    @Given("que foi recebida uma mensagem no webhook indicando um pagamento efetuado")
    public void que_foi_recebida_uma_mensagem_no_webhook_indicando_um_pagamento_efetuado() {
        this.request = MockGenerator.generateConfirmacaoWebHookRequestMock();
    }

    @When("o servico invocar a funcao do processamento de pagamento")
    public void o_servico_invocar_a_funcao_do_processamento_de_pagamento() {
        this.webHookController.confirmarPagamento(this.request);
    }

    @Then("a funcao deve ser executada com sucesso")
    public void a_funcao_deve_ser_executada_com_sucesso() {
        verify(this.webHookUseCase, times(1)).processarPagamento(request);
        verifyNoMoreInteractions(this.webHookUseCase);
    }
}
