package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.cancelarintencion;

import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CanceIntenInexiStepDefinition {

    @Entonces("^el podra ver que la peticion fue fallida$")
    public void elPodraVerQueLaPeticionFueFallida() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El servicio responde de manera erronea",
                        response -> response.statusCode(400))
        );
    }

}
