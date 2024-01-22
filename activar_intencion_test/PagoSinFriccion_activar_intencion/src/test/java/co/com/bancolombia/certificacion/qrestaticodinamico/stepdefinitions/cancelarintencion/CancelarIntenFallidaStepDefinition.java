package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.cancelarintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.questions.ValidarError;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class CancelarIntenFallidaStepDefinition {

    @Entonces("^el podra ver el resultado fallido en la cancelacion de la intencion (.*) (.*)$")
    public void elPodraVerElResultadoFallidoEnLaCancelacionDeLaIntencion(String campoIncorrecto, String codigoError) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarError.de(campoIncorrecto, codigoError), Matchers.is(true)));
    }

}
