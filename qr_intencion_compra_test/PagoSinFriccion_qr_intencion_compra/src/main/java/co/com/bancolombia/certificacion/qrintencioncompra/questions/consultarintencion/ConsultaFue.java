package co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.DataConsult;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Convertir;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Map;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.ESTADO;

public class ConsultaFue implements Question<Boolean> {
    DataConsult hope;

    ConsultaFue(DataConsult data) {
        this.hope = data;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        String respuestaPostData = SerenityRest.lastResponse().getBody().jsonPath().get().toString();
        Map<String, String> pathData = Convertir.respuestaServicioListArreglar(respuestaPostData).get(0);
        if (pathData.get("qrId").equals(hope.getIdQr())
                && pathData.get("amount").equals(hope.getValorIntencion())
                && pathData.get(ESTADO).equals(hope.getEstado())
                && pathData.get("creationDate").equals(hope.getFechaHoraIntencion())
                && pathData.containsKey("lastUpdateDate")) {
            if ("CANCELADO".equals(pathData.get(ESTADO)) || "EXPIRADA".equals(pathData.get(ESTADO)))
                return pathData.containsKey("reason");
            return true;
        }
        return false;

    }

    public static ConsultaFue exitosa(DataConsult data) {
        return new ConsultaFue(data);
    }

}
