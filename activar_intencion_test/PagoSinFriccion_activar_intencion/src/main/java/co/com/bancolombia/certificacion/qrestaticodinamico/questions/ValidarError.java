package co.com.bancolombia.certificacion.qrestaticodinamico.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidarError implements Question<Boolean> {

    private String campoIncorrecto;
    private String codigoError;

    public ValidarError(String campo, String codigo) {
        this.campoIncorrecto = campo;
        this.codigoError = codigo;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        Boolean respuesta = false;
        List<Map<String, Object>> lista = SerenityRest.lastResponse().getBody().jsonPath().getList("errors");
        int codigoRespuesta = SerenityRest.lastResponse().getStatusCode();

        for (int i = 0; i < lista.size(); i++) {
            if ("idIntenInexistente".equalsIgnoreCase(campoIncorrecto) ||
                    "intencionCancelada".equalsIgnoreCase(campoIncorrecto) ||
                    "intencionExpirada".equalsIgnoreCase(campoIncorrecto) ||
                    "intencionExitosa".equalsIgnoreCase(campoIncorrecto)) {
                respuesta = codigoRespuesta == Integer.parseInt(codigoError) && ("BPQR-B0015").equalsIgnoreCase(lista.get(i).get("code").toString());
            } else {
                respuesta = codigoRespuesta == Integer.parseInt(codigoError) && !lista.get(i).get("code").toString().isEmpty();
            }
        }
        return respuesta;
    }

    public static ValidarError de(String campoIncorrecto, String codigoError) {
        return new ValidarError(campoIncorrecto, codigoError);
    }
}
