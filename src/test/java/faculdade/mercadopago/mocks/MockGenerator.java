package faculdade.mercadopago.mocks;

import faculdade.mercadopago.controller.mapper.PedidoMapper;
import faculdade.mercadopago.controller.mapper.dto.request.*;
import faculdade.mercadopago.controller.mapper.dto.response.*;
import faculdade.mercadopago.entity.*;
import faculdade.mercadopago.entity.enums.StatusPedidoEnum;
import faculdade.mercadopago.entity.pagamento.QrCodeRes;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public class MockGenerator {

    private static final Long ID = 1L;

    public static QrCodeRequest generateQrCodeRequestMock() {
        return new QrCodeRequest(
                123L,
                100.0,
                List.of(new QrCodeRequest.ItemPedido(ID, 2, BigDecimal.valueOf(50)))
        );
    }

    public static QrCodeRes generateQrCodeResMock() {
        return new QrCodeRes(
                "mocked_qrcode",
                "mocked_url"
        );
    }

}
