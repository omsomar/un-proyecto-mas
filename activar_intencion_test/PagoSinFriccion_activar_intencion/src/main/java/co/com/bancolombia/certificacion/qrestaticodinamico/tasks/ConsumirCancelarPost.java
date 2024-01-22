package co.com.bancolombia.certificacion.qrestaticodinamico.tasks;

import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Post;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.cancelintencion.DataRequest;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.body.BodyRequestCancel;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsumirCancelarPost extends DataServices implements Task {

    private String servicio;
    private String escenarios;
    private String razonCancelacion;
    private String campoIncorrecto;

    public ConsumirCancelarPost(DataServices dataServices) {
        this.servicio = dataServices.getServicio();
        this.escenarios = dataServices.getEscenario();
        this.razonCancelacion = dataServices.getRazonCancelacion();
        this.campoIncorrecto = dataServices.getCampoIncorrecto();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> header = new HashMap<>();
        header.put(Constantes.CAMPO_CLIENT_SECRET, Constantes.CLIENT_SECRECT);
        header.put(Constantes.CAMPO_CLIENT_ID, Constantes.CLIENT_ID);
        header.put(Constantes.CAMPO_MESSAGE_ID, Constantes.MESSAGE_ID);
        actor.attemptsTo(
                Post.to(this.servicio).with(
                        requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .headers(header)
                                .contentType(ContentType.JSON)
                                .body(bodyRequestCancel())
                )
        );
        actor.remember("idIntencionCancelar", bodyRequestCancel().getData().getQrIntentionId());
    }

    public DataRequest bodyRequestCancel() {
        if ("todosLosCampos".equalsIgnoreCase(this.escenarios)) {
            return BodyRequestCancel.todosLosCampos(this.razonCancelacion);
        } else if ("camposObligatorios".equalsIgnoreCase(this.escenarios)) {
            return BodyRequestCancel.camposObligatorios();
        } else if ("camposIncompletos".equalsIgnoreCase(this.escenarios)) {
            return BodyRequestCancel.camposIncompletos();
        } else if ("camposIncorrectos".equalsIgnoreCase(this.escenarios)) {
            return BodyRequestCancel.camposIncorrectos(this.campoIncorrecto);
        } else {
            return BodyRequestCancel.inexistente();
        }
    }

    public static ConsumirCancelarPost consumirCancelarPost(DataServices dataServices) {
        return instrumented(ConsumirCancelarPost.class, dataServices);
    }
}
