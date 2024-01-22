package co.com.bancolombia.certificacion.consultarvinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.consultarvinculacion.questions.consultarvinculacion.ValidarRespuestaFallida;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class ConsulVinculacionFallidaStepDefinition {

    @Entonces("^el podra ver el resultado erroneo de la consulta (.*)$")
    public void elPodraVerElResultadoErroneoDeLaConsulta(String codigo) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarRespuestaFallida.de(codigo), Matchers.is(true)));

    }
}
