package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.crearintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.questions.RespuestaDelServicio;
import co.com.bancolombia.certificacion.qrestaticodinamico.tasks.ConsumirMetodoPost;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes.URL_INTEGRACION;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CrearIntenExitosaStepDefinition {

    @Before
    public void configuracionInicial() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Cajero");
    }

    @Dado("^que el usuario quiere consumir uno de los servicios del modelo estatico dinamico$")
    public void queElUsuarioQuiereConsumirUnoDeLosServiciosDelModeloEstaticoDinamico() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(URL_INTEGRACION));
    }

    @Cuando("^el utiliza el servicio de crear intencion de compra$")
    public void elUtilizaElServicioDeCrearIntencionDeCompra(List<DataServices> datos) {
        theActorInTheSpotlight().attemptsTo(ConsumirMetodoPost.consumirMetodoPost(datos.get(0)));
    }


    @Entonces("^el podra ver el resultado exitoso en la creacion de la intencion$")
    public void elPodraVerElResultadoExitosoEnLaCreacionDeLaIntencion(List<DataServices> datos) {
        theActorInTheSpotlight().should(
                seeThatResponse("El servicio responde correctamente",
                        response -> response.statusCode(201)),
                seeThat(RespuestaDelServicio.respuestaDelServicio(datos.get(0)), Matchers.is(true))
        );
    }

}
