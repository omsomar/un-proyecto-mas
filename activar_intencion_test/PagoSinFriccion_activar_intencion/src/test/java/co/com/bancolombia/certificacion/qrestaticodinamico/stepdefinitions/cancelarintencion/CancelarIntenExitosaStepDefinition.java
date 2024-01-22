package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.cancelarintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.questions.RespuestaServicioCancel;
import co.com.bancolombia.certificacion.qrestaticodinamico.tasks.ConsumirCancelarPost;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CancelarIntenExitosaStepDefinition {

    @Cuando("^el utiliza el servicio de cancelar intencion de compra$")
    public void elUtilizaElServicioDeCancelarIntencionDeCompra(List<DataServices> datos) {
        theActorInTheSpotlight().attemptsTo(ConsumirCancelarPost
                .consumirCancelarPost(datos.get(0)));
    }

    @Entonces("^el podra ver el resultado exitoso en la cancelacion de la intencion$")
    public void elPodraVerElResultadoExitosoEnLaCancelacionDeLaIntencion(List<DataServices> data) {
        theActorInTheSpotlight()
                .should(seeThat(RespuestaServicioCancel.respuestaServicioCancel(data.get(0)), Matchers.is(true)));

    }

}
