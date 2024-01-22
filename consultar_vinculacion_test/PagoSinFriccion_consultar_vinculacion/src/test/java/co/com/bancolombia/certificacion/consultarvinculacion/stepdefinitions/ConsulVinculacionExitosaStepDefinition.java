package co.com.bancolombia.certificacion.consultarvinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.consultarvinculacion.models.consultarvinculacion.FiltrosVinculacion;
import co.com.bancolombia.certificacion.consultarvinculacion.questions.consultarvinculacion.ValidarRespuesta;
import co.com.bancolombia.certificacion.consultarvinculacion.tasks.consultarvinculacion.ConsumirMetodo;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.Constantes;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsulVinculacionExitosaStepDefinition {

    @Dado("^que el usuario quiere consumir uno de los servicios de comision$")
    @Y("^que el usuario quiere consumir uno de los servicios de Pago sin Friccion en nube$")
    public void queElUsuarioQuiereConsumirUnoDeLosServiciosDeComision() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
        theActorInTheSpotlight().whoCan(CallAnApi.at(Constantes.URL));
    }

    @Cuando("^el utiliza el servicio de consultar vinculacion$")
    public void elUtilizaElServicioDeConsultarVinculacion(List<FiltrosVinculacion> datos) {
        theActorInTheSpotlight().attemptsTo(ConsumirMetodo.pos(datos.get(0)));
    }

    @Entonces("^el podra ver el resultado exitoso de la consulta realizada (.*)$")
    public void elPodraVerElResultadoExitosoDeLaConsultaRealizada(String escenario) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarRespuesta.de(escenario), Matchers.is(true)));

    }
}
