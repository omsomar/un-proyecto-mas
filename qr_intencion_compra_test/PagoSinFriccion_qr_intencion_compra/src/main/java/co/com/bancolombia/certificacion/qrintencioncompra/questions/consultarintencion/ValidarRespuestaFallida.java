package co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarRespuestaFallida implements Question<Boolean> {

    private String escenario;

    public ValidarRespuestaFallida(String escenario) {
        this.escenario = escenario;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int codeRespuesta = SerenityRest.lastResponse().getStatusCode();

        if ("qrSinNingunaIntencion".equalsIgnoreCase(this.escenario) ||
                "qrSinintencionPendiente".equalsIgnoreCase(this.escenario)) {
            return codeRespuesta == 204;
        } else if ("recursoErroneo".equalsIgnoreCase(this.escenario)) {
            return codeRespuesta == 404;
        }
        return codeRespuesta == 400;
    }

    public static ValidarRespuestaFallida consultarIntenPorQr(String escenario) {
        return new ValidarRespuestaFallida(escenario);
    }
}
