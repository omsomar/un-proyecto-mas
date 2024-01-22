package co.com.bancolombia.certificacion.qrestaticodinamico.tasks;

import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Post;
import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Wait;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.postintencion.DataRequest;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.body.BodyResquestPost;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CrearIntencionConsecutiva implements Task {

    protected String servicio;
    protected String escenarios;
    protected String valorIntencion;

    public CrearIntencionConsecutiva(DataServices dataServices) {
        this.servicio = dataServices.getServicio();
        this.escenarios = dataServices.getEscenario();
        this.valorIntencion = dataServices.getValorIntencion();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        /*Se guarda la primera intención para el QR */
        actor.remember("primeraIntencion", SerenityRest.lastResponse().getBody()
                .jsonPath().getMap("data").get("qrIntentionId").toString());

        Map<String, String> header = new HashMap<>();
        header.put(Constantes.CAMPO_CLIENT_SECRET, Constantes.CLIENT_SECRECT);
        header.put(Constantes.CAMPO_CLIENT_ID, Constantes.CLIENT_ID);
        header.put(Constantes.CAMPO_MESSAGE_ID, Constantes.MESSAGE_ID);
        actor.attemptsTo(peticionPost(header));

        actor.attemptsTo(Wait.per(2000));
        /*Se guarda la segunda intención creada para el mismo QR */
        actor.remember("segundaIntencion", SerenityRest.lastResponse().getBody()
                .jsonPath().getMap("data").get("qrIntentionId").toString());

    }

    public Performable peticionPost(Map<String, String> header) {
        return Post.to(servicio).with(
                requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .headers(header)
                        .contentType(ContentType.JSON)
                        .body(bodyResquestPost())
        );
    }

    public DataRequest bodyResquestPost() {
        DataRequest data = new DataRequest();
        if ("camposIntencionesConsecutivas".equals(this.escenarios))
            data = BodyResquestPost.intencionesConsecutivas(this.valorIntencion);
        return data;
    }

    public static CrearIntencionConsecutiva crearIntencionConsecutiva(DataServices dataServices) {
        return instrumented(CrearIntencionConsecutiva.class, dataServices);
    }
}
