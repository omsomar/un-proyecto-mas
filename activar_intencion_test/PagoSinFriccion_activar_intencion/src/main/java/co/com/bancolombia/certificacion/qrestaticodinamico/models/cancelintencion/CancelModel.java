package co.com.bancolombia.certificacion.qrestaticodinamico.models.cancelintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades.GeneradorArchivosJSON;
import lombok.extern.log4j.Log4j2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class CancelModel {

    private String qrIntentionId;
    private String reason;


    public static List<Map<String, Object>> getIntenciones() {

        List<Map<String, Object>> listIntenciones = new ArrayList<>();
        try {
            listIntenciones = GeneradorArchivosJSON.readFileToListOfMap("intenciones.json");
        } catch (IOException e) {
            log.error("error leyendo el archivo json", e);
        }
        return listIntenciones;
    }

    public void todosLosCampos(String razonCancel) {

        if (razonCancel.equalsIgnoreCase(Constantes.CANCEL_VALOR_ERRADO)) {
            this.qrIntentionId = getIntenciones().get(0).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
            this.reason = "Valor errado";
        } else if (razonCancel.equalsIgnoreCase(Constantes.CANCEL_CAMBIO_PRODUCTO)) {
            this.qrIntentionId = getIntenciones().get(1).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
            this.reason = "Cambio de productos";
        } else if (razonCancel.equalsIgnoreCase(Constantes.CANCEL_DESCTO_ERRADO)) {
            this.qrIntentionId = getIntenciones().get(2).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
            this.reason = "Descuento errado";
        } else if (razonCancel.equalsIgnoreCase(Constantes.CANCEL_PROPINA_ERRADA)) {
            this.qrIntentionId = getIntenciones().get(3).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
            this.reason = "Propina errada";
        } else if (razonCancel.equalsIgnoreCase(Constantes.CANCEL_CAMBIO_MEDIOPAGO)) {
            this.qrIntentionId = getIntenciones().get(4).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
            this.reason = "Cambio de medio de pago";
        } else if (razonCancel.equalsIgnoreCase(Constantes.CANCEL_CLIENTE)) {
            this.qrIntentionId = getIntenciones().get(5).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
            this.reason = "Cancelación por parte del cliente";
        }

    }

    public void camposObligatorios() {
        this.qrIntentionId = getIntenciones().get(6).get(Constantes.CAMPO_ID_INTENCION_JSON).toString();
        this.reason = "";
    }

    public void camposIncompletos() {
        this.qrIntentionId = "";
        this.reason = "";
    }

    public void camposIncorrectos(String campoIncorrecto) {
        /*Se realiza prueba enviando la razón de cancelación con un valor que no corresponde a ningún valor de la lista de razones de cancelación */
        if (campoIncorrecto.equalsIgnoreCase(Constantes.RAZON_CANCELACION)) {
            this.qrIntentionId = getIntenciones().get(7).get("idIntencion").toString();
            this.reason = "Se canceló la compra por parte del cliente..";
        } else if (campoIncorrecto.equalsIgnoreCase(Constantes.ID_INTENCION)) {
            this.qrIntentionId = "c4//..*#[]e6bd04";
            this.reason = "Cambio de medio de pago";
        } else if (campoIncorrecto.equalsIgnoreCase(Constantes.ID_INTENCION_INEXISTENTE)) {
            this.qrIntentionId = "e3cafeff-509f-46fa-a24e-8c60973e6708";
            this.reason = "Propina errada";
        } else if (campoIncorrecto.equalsIgnoreCase(Constantes.INTENCION_ESTADO_CANCELADO)) {
            this.qrIntentionId = Constantes.ID_INTENCION_CANCELADA;
        } else if (campoIncorrecto.equalsIgnoreCase(Constantes.INTENCION_ESTADO_EXPIRADA)) {
            this.qrIntentionId = Constantes.ID_INTENCION_EXPIRADA;
            this.reason = "";
        } else if (campoIncorrecto.equalsIgnoreCase(Constantes.INTENCION_ESTADO_EXITOSA)) {
            this.qrIntentionId = Constantes.ID_INTENCION_EXITOSA;
            this.reason = "";
        }
    }

    public void inexistente() {
        this.qrIntentionId = "000000-ass1-000s-d000-00000000wd00";
        this.reason = "Cambio de productos";
    }

    public String getQrIntentionId() {
        return qrIntentionId;
    }

    public String getReason() {
        return reason;
    }

}
