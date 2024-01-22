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

import java.util.Map;

public class ListarNotificacion implements Task {

    private FiltrosListar filtros;

    public ListarNotificacion(FiltrosListar filtros) {

        this.filtros = filtros;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to(Constantes.SERVICIO_LIST_NOTIFICACIONES).with(
                requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .contentType(ContentType.JSON)
                        .body(DataRequest.dataListar(this.filtros))));

        Map<String, Object> respuestaPaginacion = SerenityRest.lastResponse().getBody().jsonPath().getMap("pagination");
        /* Se guardan como variables de sesión los filtros que pueden ser utilizados al realizar una solicitud
        del servicio Listar notificaciones */
        actor.remember("tipoDoc", DataRequest.dataListar(this.filtros).getCommerceDocumentType());
        actor.remember("numDoc", DataRequest.dataListar(this.filtros).getCommerceDocumentNumber());
        actor.remember("tipoCuenta", DataRequest.dataListar(this.filtros).getCommerceAccountType());
        actor.remember("numCuenta", DataRequest.dataListar(this.filtros).getCommerceAccountNumber());
        actor.remember("fechaInicio", DataRequest.dataListar(this.filtros).getStartDate());
        actor.remember("fechaFin", DataRequest.dataListar(this.filtros).getEndDate());
        actor.remember("primerPagSize", DataRequest.dataListar(this.filtros).getPaginationSize());
        actor.remember("objetoPaginacion", respuestaPaginacion);
    }

    public static ListarNotificacion de(FiltrosListar filtros) {
        return Tasks.instrumented(ListarNotificacion.class, filtros);
    }
}
