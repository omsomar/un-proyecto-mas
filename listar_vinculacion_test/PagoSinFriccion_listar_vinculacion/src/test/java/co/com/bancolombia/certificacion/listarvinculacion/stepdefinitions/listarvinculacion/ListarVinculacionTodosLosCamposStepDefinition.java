package co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions.listarvinculacion;

import co.com.bancolombia.certificacion.listarvinculacion.questions.ListarVinculacionFueExitosa;
import cucumber.api.java.es.Entonces;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ListarVinculacionTodosLosCamposStepDefinition {

    @Entonces("^el podra ver toda la informacion de los campos en el registro de vinculacion consultado$")
    public void elPodraVerTodaLaInformacionDeLosCamposEnElRegistroDeVinculacionConsultado() {

        theActorInTheSpotlight().should(
                seeThatResponse("El servicio respondió exitosamente", response -> response.statusCode(200)),
                seeThat("La respuesta de la peticion del servicio fue exitosa"
                        , ListarVinculacionFueExitosa.validacion(), Matchers.is(true))
                );
    }

}
