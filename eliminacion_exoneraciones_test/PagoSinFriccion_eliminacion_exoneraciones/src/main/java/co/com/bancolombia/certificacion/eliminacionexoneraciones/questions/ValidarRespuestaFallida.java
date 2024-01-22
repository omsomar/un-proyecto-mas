package co.com.bancolombia.certificacion.eliminacionexoneraciones.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarRespuestaFallida implements Question<Boolean> {

    private int codigoResp;

    public ValidarRespuestaFallida(int codigoResp) {
        this.codigoResp = codigoResp;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        int codRespuesta = SerenityRest.lastResponse().getStatusCode();

        if(codRespuesta == 200){
           return  SerenityRest.lastResponse().getBody().jsonPath().getList("$").isEmpty() &&
                   codRespuesta == codigoResp;
        } else{
            return codRespuesta == codigoResp;
        }

    }
    public static ValidarRespuestaFallida de(int codigoResp){
        return new ValidarRespuestaFallida(codigoResp);
    }
}
