package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.getinfo;

import co.com.bancolombia.certificacion.infologtransaccional.models.getinfo.DataFeature;
import co.com.bancolombia.certificacion.infologtransaccional.questions.getinfo.InfoLogFueExitosa;
import co.com.bancolombia.certificacion.infologtransaccional.tasks.getinfo.ConsultarLog;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static co.com.bancolombia.certificacion.infologtransaccional.utils.Constantes.URL_BASE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class GetInfoLogCamposObligatoriosStepDefinitions {

    @Before
    public void configuracionInicial() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Junior");
    }

    @Dado("^que el usuario quiere consumir uno de los servicios de pago sin friccion$")
    public void queElUsuarioQuiereConsumirUnoDeLosServiciosDePagoSinFriccion() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(URL_BASE));
    }

    @Cuando("^el utiliza el servicio de Get Info Log Transaccional$")
    public void elUtilizaElServicioDeGetInfoLogTransaccional(List<DataFeature> dataFeature) {
        theActorInTheSpotlight().attemptsTo(ConsultarLog.transaccional(dataFeature.get(0)));
    }


    @Entonces("^el podra ver el resultado exitoso de la consulta$")
    public void elPodraVerElResultadoExitosoEnLaConsulta(List<DataFeature> dataFeature) {
        theActorInTheSpotlight().should(seeThatResponse("el servicio responde correctamente",
                response -> response.statusCode(200)),
                seeThat("La respuesta fue exitosa", InfoLogFueExitosa.consultaFueExitosa(dataFeature.get(0)),
                        Matchers.is(true)));
    }

}
