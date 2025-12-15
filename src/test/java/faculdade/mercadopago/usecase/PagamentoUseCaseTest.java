package faculdade.mercadopago.usecase;

import faculdade.mercadopago.AppConstants;
import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.entity.Pedido;
import faculdade.mercadopago.entity.pagamento.ConfirmacaoPagamentoRes;
import faculdade.mercadopago.entity.pagamento.QrCodeOrder;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.gateway.IPagamentoGateway;
import faculdade.mercadopago.usecase.impl.PagamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoUseCaseTest {

    private IPagamentoGateway gateway;
    private PagamentoUseCase pagamentoUseCase;

    @BeforeEach
    void setup() {
        gateway = mock(IPagamentoGateway.class);
        pagamentoUseCase = new PagamentoUseCase(gateway);
    }

    @Test
    void deveProcessarQrCodeCorretamente() {
        QrCodeRequest.ItemPedido itemPedido = mock(QrCodeRequest.ItemPedido.class);
        when(itemPedido.Codigo()).thenReturn(1L);
        when(itemPedido.quantidade()).thenReturn(2);
        when(itemPedido.Valor()).thenReturn(BigDecimal.valueOf(10));

        QrCodeRequest request = mock(QrCodeRequest.class);
        when(request.Itens()).thenReturn(List.of(itemPedido));
        when(request.OrderId()).thenReturn(123L);
        when(request.TotalAmount()).thenReturn(20.0);

        QrCodeRes qrCodeResEsperado = mock(QrCodeRes.class);

        String urlEsperada = AppConstants.BASEURL_MERCADOPAGO + AppConstants.GENERATEQRCODEURL_MERCADOPAGO;

        when(gateway.sendRequest(eq(urlEsperada), eq(HttpMethod.POST), any(QrCodeOrder.class), eq(QrCodeRes.class), isNull()))
                .thenReturn(ResponseEntity.ok(qrCodeResEsperado));

        QrCodeRes resultado = pagamentoUseCase.processarQrCode(request);

        assertEquals(qrCodeResEsperado, resultado);

        ArgumentCaptor<QrCodeOrder> captor = ArgumentCaptor.forClass(QrCodeOrder.class);
        verify(gateway).sendRequest(eq(urlEsperada), eq(HttpMethod.POST), captor.capture(), eq(QrCodeRes.class), isNull());

        QrCodeOrder enviado = captor.getValue();
        assertEquals("123", enviado.external_reference());
        assertEquals(20.0, enviado.total_amount());
        assertEquals(1, enviado.items().size());

        QrCodeOrder.Item item = enviado.items().get(0);
        assertEquals("lanche", item.category());
        assertEquals(2, item.quantity());
        assertEquals(BigDecimal.valueOf(10), item.unit_price());
        assertEquals(BigDecimal.valueOf(20), item.total_amount());
    }

    @Test
    void deveChamarSalvarPagamento() {
        Pedido pedido = mock(Pedido.class);
        BigDecimal valor = BigDecimal.valueOf(100);

        pagamentoUseCase.salvarPagamento(pedido, valor);

        verify(gateway).save(pedido, valor);
    }

    @Test
    void deveConsultarPagamento() {
        String pedidoId = "888";
        String url = AppConstants.BASEURL_MERCADOPAGO
                + AppConstants.CONFIRMPAYMENT_MERCADOPAGO + "/" + pedidoId;

        ConfirmacaoPagamentoRes bodyMock = mock(ConfirmacaoPagamentoRes.class);

        ResponseEntity<ConfirmacaoPagamentoRes> responseEntity =
                ResponseEntity.ok(bodyMock);

        doReturn(responseEntity)
                .when(gateway)
                .sendRequest(
                        url,
                        HttpMethod.GET,
                        ConfirmacaoPagamentoRes.class);

        ConfirmacaoPagamentoRes resultado =
                pagamentoUseCase.consultarPagamento(pedidoId);

        assertEquals(bodyMock, resultado);

        verify(gateway).sendRequest(
                url,
                HttpMethod.GET,
                ConfirmacaoPagamentoRes.class
        );
    }

}
