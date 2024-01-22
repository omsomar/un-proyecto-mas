package co.com.bancolombia.certificacion.infologtransaccional.tasks.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.interactions.Post;
import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;
import co.com.bancolombia.certificacion.infologtransaccional.utils.Constantes;
import co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion.DataRequestFallida;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ListarNotificacionFallida implements Task {

    private FiltrosListar filtros;

    public ListarNotificacionFallida(FiltrosListar filtros) {
        this.filtros = filtros;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(enviarBody());
    }

    public Performable enviarBody() {
        Performable body;
        if ("recursoErroneo".equalsIgnoreCase(this.filtros.getEscenario())) {
            body = Post.to("/get-notifications").with(
                    requestSpecification -> requestSpecification
                            .relaxedHTTPSValidation()
                            .contentType(ContentType.JSON)
                            .body(DataRequestFallida.dataListar(this.filtros)));
        } else {
            body = Post.to(Constantes.SERVICIO_LIST_NOTIFICACIONES).with(
                    requestSpecification -> requestSpecification
                            .relaxedHTTPSValidation()
                            .contentType(ContentType.JSON)
                            .body(DataRequestFallida.dataListar(this.filtros)));
        }
        return body;
    }

    public static ListarNotificacionFallida de(FiltrosListar filtros) {
        return Tasks.instrumented(ListarNotificacionFallida.class, filtros);
    }
}
