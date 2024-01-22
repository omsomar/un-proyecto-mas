package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.getinfo;

import co.com.bancolombia.certificacion.infologtransaccional.questions.LaValidacionEnBd;
import cucumber.api.java.es.Entonces;
import org.hamcrest.Matchers;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetInfoLogCamposEnBdStepDefinitions {

    @Entonces("^el podra verificar los campos en la BD Postgres$")
    public void elPodraVerificarLosCamposEnLaBDPostgres() {
        theActorInTheSpotlight().should(
                seeThatResponse("El servicio respondió exitosamente", response -> response.statusCode(200)),
                seeThat("La respuesta del servicio fue exitosa", LaValidacionEnBd.deGetInfoLog()
                        , Matchers.is(true))
        );
    }
}
