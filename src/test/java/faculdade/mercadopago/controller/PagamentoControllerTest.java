package faculdade.mercadopago.controller;

import faculdade.mercadopago.controller.mapper.dto.request.QrCodeRequest;
import faculdade.mercadopago.controller.mapper.dto.response.QrCodeResponse;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;
import faculdade.mercadopago.mocks.MockGenerator;
import faculdade.mercadopago.usecase.IPagamentoUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PagamentoControllerTest {

    private PagamentoController pagamentoController;
    private IPagamentoUseCase pagamentoUseCase;

    @BeforeEach
    void setUp() {
        pagamentoUseCase = mock(IPagamentoUseCase.class);
        pagamentoController = new PagamentoController(pagamentoUseCase);
    }


    @Test
    void deveGerarQrCodeComSucesso() {
        QrCodeRequest request = MockGenerator.generateQrCodeRequestMock();
        QrCodeRes qrcodeResMock = MockGenerator.generateQrCodeResMock();

        when(pagamentoUseCase.processarQrCode(any(QrCodeRequest.class))).thenReturn(qrcodeResMock);

        QrCodeResponse response = pagamentoController.gerarQrCode(request);

        assertNotNull(response);
        assertEquals(qrcodeResMock.qr_data(), response.qr_data());
        assertEquals(qrcodeResMock.in_store_order_id(), response.in_store_order_id());

        verify(pagamentoUseCase, times(1)).processarQrCode(request);
    }

}
