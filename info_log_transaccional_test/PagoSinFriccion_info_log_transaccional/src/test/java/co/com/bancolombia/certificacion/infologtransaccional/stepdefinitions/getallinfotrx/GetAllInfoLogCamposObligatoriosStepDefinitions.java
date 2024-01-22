package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.getallinfotrx;

import co.com.bancolombia.certificacion.infologtransaccional.models.getinfo.DataFeature;
import co.com.bancolombia.certificacion.infologtransaccional.tasks.getinfo.ConsultarLog;
import cucumber.api.java.es.Cuando;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class GetAllInfoLogCamposObligatoriosStepDefinitions {

    @Cuando("^el utiliza el servicio de Get All Info Log Transaccional$")
    public void elUtilizaElServicioDeGetAllInfoLogTransaccional(List<DataFeature> dataFeature) {
        theActorInTheSpotlight().attemptsTo(ConsultarLog.transaccional(dataFeature.get(0)));
    }
}
