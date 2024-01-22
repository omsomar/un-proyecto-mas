package co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions.listarvinculacion;

import co.com.bancolombia.certificacion.listarvinculacion.questions.ListarVinculacionFueFallida;
import cucumber.api.java.es.Entonces;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ListarVinculacionCamposIncompletosStepDefinition {



    @Entonces("^el podra ver el resultado fallido al faltar informacion en la peticion$")
    public void elPodraVerElResultadoFallidoAlFaltarInformacionEnLaPeticion() {

        theActorInTheSpotlight().should(
                seeThatResponse("El servicio respondió exitosamente", response -> response.statusCode(400)),
                seeThat("La respuesta de la peticion del servicio fue exitosa"
                        , ListarVinculacionFueFallida.validacion(), Matchers.is(true))
        );

    }

}
