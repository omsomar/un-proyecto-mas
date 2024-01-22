package co.com.bancolombia.certificacion.qrintencioncompra.tasks;

import co.com.bancolombia.certificacion.qrintencioncompra.interactions.Get;
import co.com.bancolombia.certificacion.qrintencioncompra.models.FiltrosIntencion;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ConsultarIntencionQr implements Task {

    private String idQr;
    private String escenario;

    public ConsultarIntencionQr(FiltrosIntencion data) {
        this.idQr = data.getIdQR();
        this.escenario = data.getEscenario();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(hacerPeticion());
        actor.remember("qr", idQr);
    }

    public Performable hacerPeticion() {

        Performable respuesta;

        if ("sinQr".equalsIgnoreCase(this.escenario)) {
            respuesta = Get.resource(Constantes.SERVICIO_CONSULTAR_INTEN_POR_QR).with(
                    requestSpecification -> requestSpecification
                            .relaxedHTTPSValidation());
        } else if ("recursoErroneo".equalsIgnoreCase(this.escenario)) {
            respuesta = Get.resource("/getIntention").with(
                    requestSpecification -> requestSpecification
                            .relaxedHTTPSValidation()
                            .queryParam("qrId", idQr));
        } else {
            respuesta = Get.resource(Constantes.SERVICIO_CONSULTAR_INTEN_POR_QR).with(
                    requestSpecification -> requestSpecification
                            .relaxedHTTPSValidation()
                            .queryParam("qrId", idQr));
        }
        return respuesta;
    }

    public static ConsultarIntencionQr con(FiltrosIntencion data) {
        return Tasks.instrumented(ConsultarIntencionQr.class, data);
    }
}
