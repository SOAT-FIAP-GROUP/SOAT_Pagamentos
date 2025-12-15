package faculdade.mercadopago.usecase;

import faculdade.mercadopago.controller.mapper.dto.request.ConfirmacaoWebHookRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.DadosPedidoPago;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.gateway.IPedidoGateway;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.impl.WebHookUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class WebhookUseCaseTest {

    private IPagamentoGateway gateway;
    private WebHookUseCase webHookUseCase;
    private IPagamentoUseCase pagamentoUseCase;
    private IPedidoGateway pedidoGateway;

    @BeforeEach
    void setup() {
        gateway = mock(IPagamentoGateway.class);
        pagamentoUseCase = mock(IPagamentoUseCase.class);
        pedidoGateway = mock(IPedidoGateway.class);
        webHookUseCase = new WebHookUseCase(pagamentoUseCase, gateway, pedidoGateway);
    }

    @Test
    void deveConfirmarPagamentoComSucesso() {
        ConfirmacaoWebHookRequest request =
                MockGenerator.generateConfirmacaoWebHookRequestMock();

        ConfirmacaoPagamentoRes pagamentoAprovado =
                mock(ConfirmacaoPagamentoRes.class);

        when(pagamentoAprovado.status()).thenReturn("approved");

        doReturn(pagamentoAprovado)
                .when(pagamentoUseCase)
                .consultarPagamento(request.id());

        boolean resultado = webHookUseCase.confirmarPagamento(request);

        assertTrue(resultado);
    }

    @Test
    void deveRetornarPedidoPagoComSucesso() {
        ConfirmacaoWebHookRequest request =
                MockGenerator.generateConfirmacaoWebHookRequestMock();

        ConfirmacaoPagamentoRes pagamentoAprovado =
                mock(ConfirmacaoPagamentoRes.class);

        when(pagamentoAprovado.external_reference()).thenReturn("1");

        doReturn(pagamentoAprovado)
                .when(pagamentoUseCase)
                .consultarPagamento(request.id());

        DadosPedidoPago resultado = webHookUseCase.retornarPedidoPago(request);

        assertEquals(request.id(), resultado.codigo());
    }

    @Test
    void deveProcessarPagamentoComSucesso() {
        ConfirmacaoWebHookRequest request =
                MockGenerator.generateConfirmacaoWebHookRequestMock();

        DadosPedidoPago dadosPago =
                new DadosPedidoPago("999", 100.00);

        Pedido pedido =
                MockGenerator.generatePedido();

        Long pedidoId = 999L;
        BigDecimal valor = BigDecimal.valueOf(100.00);

        WebHookUseCase spyUseCase = spy(webHookUseCase);
        doReturn(true)
                .when(spyUseCase)
                .confirmarPagamento(request);

        doReturn(dadosPago)
                .when(spyUseCase)
                .retornarPedidoPago(request);

        when(pedidoGateway.findById(pedidoId))
                .thenReturn(Optional.of(pedido));

        spyUseCase.processarPagamento(request);

        // assert
        verify(pagamentoUseCase)
                .salvarPagamento(pedido, valor);

        verify(pedidoGateway)
                .alterarStatus(pedidoId, StatusPedidoEnum.EM_PREPARACAO);

        verify(pedidoGateway)
                .adicionarPedidoNaFila(pedidoId);

    }


}
