package co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarConsultaFallida implements Question<Boolean> {

    private String codigo;
    private String campo;

    public ValidarConsultaFallida(String codigo, String campo) {
        this.codigo = codigo;
        this.campo = campo;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        int codRespuestaServicio = SerenityRest.lastResponse().getStatusCode();
        Boolean respuesta = false;

        if (codRespuestaServicio == Integer.parseInt(this.codigo)) {

            if ("200".equalsIgnoreCase(this.codigo) && !"paginationKeyInexistente".equalsIgnoreCase(this.campo)) {
                String total = SerenityRest.lastResponse().getBody().jsonPath().getMap("pagination").get("totalElements").toString();
                respuesta = "0".equalsIgnoreCase(total);
            } else {
                respuesta = true;
            }
        }

        return respuesta;
    }

    public static ValidarConsultaFallida de(String codigo, String campo) {
        return new ValidarConsultaFallida(codigo, campo);
    }
}
