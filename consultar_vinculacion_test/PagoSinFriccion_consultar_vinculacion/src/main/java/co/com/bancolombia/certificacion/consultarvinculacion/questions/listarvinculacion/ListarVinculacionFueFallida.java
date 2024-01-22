package co.com.bancolombia.certificacion.consultarvinculacion.questions.listarvinculacion;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Map;

public class ListarVinculacionFueFallida implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        Map<String, String> respuestaErrors = SerenityRest.lastResponse().getBody().jsonPath().getMap("errors");
        String title = respuestaErrors.get("title");
        String detail = respuestaErrors.get("detail");

        return ("ERROR EN QUERYPARAMS".equals(title) && ("paginationSize Campo requerido".equals(detail) ||
                "paginationKey Campo requerido".equals(detail) || "Revise los campos requeridos y sus formatos en la firma".equals(detail)));

    }

    public static ListarVinculacionFueFallida validacion() {
        return new ListarVinculacionFueFallida();
    }

}
