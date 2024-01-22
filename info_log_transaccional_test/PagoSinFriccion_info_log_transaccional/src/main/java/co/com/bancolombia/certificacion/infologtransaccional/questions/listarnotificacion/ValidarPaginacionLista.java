package co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion.ListarNotificacionPaginacion;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidarPaginacionLista implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {

        List<Map<String, String>> primerResultado = ListarNotificacionPaginacion.obtenerInfoPagina(actor.recall("respPrimerPag"), "transferVoucher", "transferDate", "transferAmount");
        List<Map<String, String>> segundoResultado = ListarNotificacionPaginacion.obtenerInfoPagina(actor.recall("respSegundaPag"), "transferVoucher", "transferDate", "transferAmount");
        return !primerResultado.containsAll(segundoResultado);
    }

    public static ValidarPaginacionLista de() {
        return new ValidarPaginacionLista();
    }
}
