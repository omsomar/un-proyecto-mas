package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.getinfo;

import co.com.bancolombia.certificacion.infologtransaccional.questions.getinfo.ElCodigoDeRespuesta;
import cucumber.api.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class GetInfoLogCamposFaltantesStepDefinitions {

    @Entonces("^el podra ver el resultado fallido en la consulta$")
    public void elPodraVerElResultadoFallidoEnLaConsulta() {
        theActorInTheSpotlight().should(seeThat("El codigo de respuesta es de fallo",
                ElCodigoDeRespuesta.es(), equalTo(400)));
    }
}
