package co.com.bancolombia.certificacion.infologtransaccional.tasks.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.interactions.Post;
import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;
import co.com.bancolombia.certificacion.infologtransaccional.utils.Constantes;
import co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion.DataRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.List;
import java.util.Map;

public class ListarNotificacionPagina implements Task {

    private FiltrosListar filtros;

    public ListarNotificacionPagina(FiltrosListar filtros) {
        this.filtros = filtros;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, Object>> respuestaPrimerPag = SerenityRest.lastResponse().getBody().jsonPath().getList("data");

        actor.attemptsTo(Post.to(Constantes.SERVICIO_LIST_NOTIFICACIONES).with(
                requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .contentType(ContentType.JSON)
                        .body(DataRequest.dataListar(this.filtros))));

        List<Map<String, Object>> respuestaSegundaPag = SerenityRest.lastResponse().getBody().jsonPath().getList("data");
        actor.remember("respPrimerPag", respuestaPrimerPag);
        actor.remember("respSegundaPag", respuestaSegundaPag);
    }

    public static ListarNotificacionPagina de(FiltrosListar filtros) {
        return Tasks.instrumented(ListarNotificacionPagina.class, filtros);
    }

}
