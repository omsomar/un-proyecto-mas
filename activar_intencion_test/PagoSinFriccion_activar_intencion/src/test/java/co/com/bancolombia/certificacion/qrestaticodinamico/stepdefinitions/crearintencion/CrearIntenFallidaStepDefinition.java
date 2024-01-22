package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.crearintencion;


import cucumber.api.java.es.Entonces;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CrearIntenFallidaStepDefinition {

    @Entonces("^el podra ver el resultado fallido en la creacion de la intencion (.+)$")
    public void elPodraVerElResultadoFallidoEnLaCreacionDeLaIntencion(String codigoError) {
        theActorInTheSpotlight()
                .should(seeThatResponse("El servicio responde de manera erronea",
                        response -> response.statusCode(Integer.parseInt(codigoError))));
    }

}
