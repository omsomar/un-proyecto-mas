package co.com.bancolombia.certificacion.consultarvinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.DataServicios;
import co.com.bancolombia.certificacion.consultarvinculacion.questions.listarvinculacion.ListarVinculacionFueExitosa;
import co.com.bancolombia.certificacion.consultarvinculacion.questions.listarvinculacion.ValidarInformacion;
import co.com.bancolombia.certificacion.consultarvinculacion.tasks.listarvinculacion.ConsumirServicio;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ListarVinculacionTodosLosCamposStepDefinition {

    @Cuando("^el utiliza el servicio listar vinculacion$")
    public void elUtilizaElServicioListarVinculacion(List<DataServicios> data) {
        theActorInTheSpotlight().attemptsTo(ConsumirServicio.listarVinculacionGet(data.get(0)));
    }

    @Entonces("^el podra ver toda la informacion de los campos en el registro de vinculacion consultado (.*)$")
    @Y("^el podra ver los registros existentes de acuerdo a los parametros de busqueda$")
    public void elPodraVerTodaLaInformacionDeLosCamposEnElRegistroDeVinculacionConsultado(String escenario) {

        theActorInTheSpotlight().should(
                seeThatResponse("El servicio respondió exitosamente", response -> response.statusCode(200)),
                GivenWhenThen.seeThat("La respuesta de la peticion del servicio fue exitosa"
                        , ListarVinculacionFueExitosa.validacion(), Matchers.is(true))
        );
        theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarInformacion.de(escenario), Matchers.is(true))
        );
    }

}
