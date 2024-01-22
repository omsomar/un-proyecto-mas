package co.com.bancolombia.certificacion.consultarvinculacion.questions.consultarvinculacion;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarRespuestaFallida implements Question<Boolean> {
    private String codigoRespuesta;

    public ValidarRespuestaFallida(String codigo) {
        this.codigoRespuesta = codigo;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int codigoResp = SerenityRest.lastResponse().getStatusCode();
        return String.valueOf(codigoResp).equalsIgnoreCase(codigoRespuesta);
    }

    public static ValidarRespuestaFallida de(String codigo) {
        return new ValidarRespuestaFallida(codigo);
    }
}
