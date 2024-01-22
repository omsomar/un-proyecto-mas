package co.com.bancolombia.certificacion.qrestaticodinamico.tasks;

import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Post;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.postintencion.DataRequest;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.body.BodyResquestPost;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsumirMetodoPost implements Task {

    protected String servicio;
    protected String escenarios;
    protected String campoFaltante;
    protected String valorIntencion;
    protected String campoIncorrecto;

    public ConsumirMetodoPost(DataServices dataServices) {
        this.servicio = dataServices.getServicio();
        this.escenarios = dataServices.getEscenario();
        this.campoFaltante = dataServices.getCampoFaltante();
        this.valorIntencion = dataServices.getValorIntencion();
        this.campoIncorrecto = dataServices.getCampoIncorrecto();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (this.campoFaltante != null) {

            if ("clientSecret".equalsIgnoreCase(this.campoFaltante)) {
                Map<String, String> header = new HashMap<>();
                header.put(Constantes.CAMPO_CLIENT_ID, Constantes.CLIENT_ID);
                header.put(Constantes.CAMPO_MESSAGE_ID, Constantes.MESSAGE_ID);

                actor.attemptsTo(peticionPost(header));
            } else if ("sinCredenciales".equalsIgnoreCase(this.campoFaltante)) {
                Map<String, String> header = new HashMap<>();
                actor.attemptsTo(peticionPost(header));
            } else {
                Map<String, String> header = new HashMap<>();
                header.put(Constantes.CAMPO_CLIENT_SECRET, Constantes.CLIENT_SECRECT);
                header.put(Constantes.CAMPO_CLIENT_ID, Constantes.CLIENT_ID);
                header.put(Constantes.CAMPO_MESSAGE_ID, Constantes.MESSAGE_ID);
                actor.attemptsTo(peticionPost(header));
            }
        } else {
            Map<String, String> header = new HashMap<>();
            header.put(Constantes.CAMPO_CLIENT_SECRET, Constantes.CLIENT_SECRECT);
            header.put(Constantes.CAMPO_CLIENT_ID, Constantes.CLIENT_ID);
            header.put(Constantes.CAMPO_MESSAGE_ID, Constantes.MESSAGE_ID);
            actor.attemptsTo(peticionPost(header));
        }

        actor.remember("cantidadIntencion", bodyResquestPost().getData().getIntentionDetail().getAmount());
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
        if ("todosLosCampos".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.todosLosCampos(this.valorIntencion);
        } else if ("camposObligatorios".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.camposObligatorios(this.valorIntencion);
        } else if ("camposIncompletos".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.camposIncompletos(this.campoFaltante);
        } else if ("camposIncorrectos".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.camposIncorrectos(this.campoIncorrecto);
        } else if ("todosLosCamposDecimales".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.todosLosCamposDecimales(this.valorIntencion);
        } else if ("longitudMaxima".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.logitudMaxima(this.campoIncorrecto);
        } else if ("camposIntencionesConsecutivas".equalsIgnoreCase(this.escenarios)) {
            return BodyResquestPost.intencionesConsecutivas(this.valorIntencion);
        } else {
            return BodyResquestPost.camposVacio();
        }
    }

    public static ConsumirMetodoPost consumirMetodoPost(DataServices dataServices) {
        return instrumented(ConsumirMetodoPost.class, dataServices);
    }
}
