package co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.Map;

public class ValidarElementosPaginacion implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {

        Map<String, Object> objetoPaginacion = actor.recall("objetoPaginacion");
        String paginationSize = actor.recall("primerPagSize");

        int numPaginas;

        if (Float.parseFloat(objetoPaginacion.get(Constantes.PAG_TOTAL_ELEMENTS).toString()) % Float.parseFloat(paginationSize) == 0) {
            numPaginas = (int) ((Float.parseFloat(objetoPaginacion.get(Constantes.PAG_TOTAL_ELEMENTS).toString()) / Float.parseFloat(paginationSize)) - 1);
        } else {
            numPaginas = (int) (Math.ceil(Float.parseFloat(objetoPaginacion.get(Constantes.PAG_TOTAL_ELEMENTS).toString()) / Float.parseFloat(paginationSize)))- 1;
        }
        return Integer.parseInt(objetoPaginacion.get("totalPages").toString()) == numPaginas;
    }

    public static ValidarElementosPaginacion de() {
        return new ValidarElementosPaginacion();
    }
}
