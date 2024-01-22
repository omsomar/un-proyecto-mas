package co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.util.List;
import java.util.Map;

public class ValidarConsultaIntenFallida implements Question<Boolean>{

    private String codigoError;

    public ValidarConsultaIntenFallida(String codigoError) {
        this.codigoError = codigoError;
    }
    @Override
    public Boolean answeredBy(Actor actor) {
        Boolean resultado = false;
        List<Map<String, Object>> respuesta = SerenityRest.lastResponse().getBody().jsonPath().getList("errors");
        int codigoRespuesta = SerenityRest.lastResponse().getStatusCode();
        resultado = codigoRespuesta == Integer.parseInt(codigoError) && validarListaError(respuesta);
        return resultado;
    }
    public Boolean validarListaError(List<Map<String,Object>> lista){
        for(int i=0; i<lista.size(); i++) {
            if(lista.get(i).containsKey("code") && lista.get(i).get("code").toString().isEmpty()){
                return false;
            }
        }
        return true;
    }

    public static ValidarConsultaIntenFallida de(String codigoError){
        return new ValidarConsultaIntenFallida(codigoError);
    }
}
