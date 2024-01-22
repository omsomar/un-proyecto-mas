package co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions.listarvinculacion;

import co.com.bancolombia.certificacion.listarvinculacion.models.DataServicios;
import co.com.bancolombia.certificacion.listarvinculacion.questions.ListarVinculacionFueExitosa;
import co.com.bancolombia.certificacion.listarvinculacion.tasks.ListarVinculacionGet;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static co.com.bancolombia.certificacion.listarvinculacion.utils.Constantes.URL_BASE_SERVICIOS_3;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ListarVinculacionCamposObligatoriosStepDefinition {

    @Before
    public void configuracionInicial() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Laura");
    }

    @Dado("^que el usuario quiere consumir uno de los servicios de Pago sin Friccion en nube$")
    public void queElUsuarioQuiereConsumirUnoDeLosServiciosDePagoSinFriccionEnNube() {
        theActorInTheSpotlight().whoCan(CallAnApi.at(URL_BASE_SERVICIOS_3));
    }

    @Cuando("^el utiliza el servicio listar vinculacion$")
    public void elUtilizaElServicioListarVinculacion(List<DataServicios> data) {
        theActorInTheSpotlight().attemptsTo(ListarVinculacionGet.listarVinculacionGet(data.get(0)));
    }

    @Entonces("^el podra ver los registros existentes de acuerdo a los parametros de busqueda$")
    public void elPodraVerLosRegistrosExistentesDeAcuerdoALosParametrosDeBusqueda() {

        theActorInTheSpotlight().should(
                seeThatResponse("El servicio respondió exitosamente", response -> response.statusCode(200)),
                seeThat("La respuesta de la peticion del servicio fue exitosa"
                        , ListarVinculacionFueExitosa.validacion(), Matchers.is(true))
        );

    }

}
