package co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions.modificarvinculacion;

import co.com.bancolombia.certificacion.listarvinculacion.models.DataServicios;
import co.com.bancolombia.certificacion.listarvinculacion.tasks.ConsumirServicio;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

import java.util.List;

import static co.com.bancolombia.certificacion.listarvinculacion.utils.Constantes.URL_BASE;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class MvcCamposInvStepdefinition {


    @Before
    public void config(){
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Junior");
    }

    @Dado("^que el usuario quiere consumir el servicio de modificar vinculacion$")
    public void queElUsuarioQuiereConsumirElServicioDeModificarVinculacion() {
        OnStage.theActorInTheSpotlight().whoCan(CallAnApi.at(URL_BASE));
    }

    @Cuando("^el usuario realice la peticion al servicio$")
    public void elUsuarioRealiceLaPeticionAlServicio(List<DataServicios> data){
        OnStage.theActorInTheSpotlight().attemptsTo(
                ConsumirServicio.consumirServicio(data.get(0))
        );
    }

    @Entonces("^el  podra comprobar que el servicio responde erroneamente al servicio$")
    public void elPodraComprobarQueElServicioRespondeErroneamenteAlServicio(){
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El servicio reponde de manera erronea a la peticion",
                        response->response.statusCode(400))
        );
    }
}
