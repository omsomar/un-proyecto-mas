package co.com.bancolombia.certificacion.qrintencioncompra.questions.listarintencion;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

public class ValidarPaginacion implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        List<String> resultadoPrimerPag = actor.recall("resultadosPrimerPagina");
        List<String> resultadoSegundaPag = actor.recall("resultadosSegundaPagina");

        return !resultadoPrimerPag.containsAll(resultadoSegundaPag);
    }

    public static ValidarPaginacion de() {
        return new ValidarPaginacion();
    }


}
