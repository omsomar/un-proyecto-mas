package co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions.listarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.DataList;
import co.com.bancolombia.certificacion.qrintencioncompra.models.FiltrosIntencion;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.ElCodigoDeRespuesta;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.listarintencion.LaPaginacion;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.listarintencion.ListadoFue;
import co.com.bancolombia.certificacion.qrintencioncompra.tasks.ListarIntencion;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class ListIntenFilStepDefinitions {

    @Dado("^que el usuario quiere consumir el servicio de consultar intencion por id qr$")
    @Given("^se consume el servicio para listar las intenciones de compra$")
    public void seConsumeElServicioParaListarLasIntencionesDeCompra() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("UsuarioComercio");
        OnStage.theActorInTheSpotlight().can(CallAnApi.at(Constantes.URI_LISTAR));
    }


    @When("^se envian los filtros de consulta$")
    @Cuando("^se envian los filtros de consulta y obtienen las intenciones de la primer pagina de resultado$")
    public void seEnvianLosFiltrosDeConsulta(List<FiltrosIntencion> filtros) {
        OnStage.theActorInTheSpotlight().attemptsTo(ListarIntencion.con(filtros.get(0)));
    }

    @Then("^se verifica que el resultado sea exitoso$")
    public void seVerificaQueElResultadoSeaExitoso(List<DataList> intenciones) {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta es de exito",
                        ElCodigoDeRespuesta.es(), equalTo(200)),
                seeThat("la comparación de los datos es exitosa",
                        ListadoFue.exitosa(intenciones), equalTo(true))
        );
    }

    @Then("^se verifica que el servicio retorne informacion$")
    public void seVerificaQueElServicioRetorneInformacion() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta es de exito",
                        ElCodigoDeRespuesta.es(), equalTo(200))
        );
    }

    @Then("^se verifica que el servicio no retorne informacion$")
    public void seVerificaQueElServicioNoRetorneInformacion() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta es de fracaso",
                        ElCodigoDeRespuesta.es(), equalTo(400))
        );
    }

    @Then("^se verifica que la paginacion sea la esperada$")
    public void seVerificaQueLaPaginacionSeaLaEsperada() {
        OnStage.theActorInTheSpotlight().should(
                seeThat("El codigo de respuesta es de exito",
                        ElCodigoDeRespuesta.es(), equalTo(200)),
                seeThat("La paginación es exitosa",
                        LaPaginacion.es(), equalTo(true))
        );
    }
}
