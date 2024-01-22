package co.com.bancolombia.certificacion.listarvinculacion.questions;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ListarVinculacionFueExitosa implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        Map<String, Integer> respuestaPagination = SerenityRest.lastResponse().getBody().jsonPath().getMap("pagination");
        Integer totalPages = respuestaPagination.get("totalPages");
        Integer totalElements = respuestaPagination.get("totalElements");
        List<Map<String, String>> respuestaData = SerenityRest.lastResponse().getBody().jsonPath().getList("data");

        return (!("-1".equals(totalPages)) && !("0".equals(totalElements)) && !respuestaData.isEmpty());

    }

    public static ListarVinculacionFueExitosa validacion() {return new ListarVinculacionFueExitosa(); }

}
