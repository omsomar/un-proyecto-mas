package co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions.consultarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.DataConsult;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.ElCodigoDeRespuesta;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion.ConsultaFue;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion.ValidarConsultaIntenFallida;
import co.com.bancolombia.certificacion.qrintencioncompra.tasks.ConsultarIntencion;
import cucumber.api.java.en.Given;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.URI_INTEGRATE;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class ConsultIntentionStepDefinition {
    @Dado("^que el usuario quiere consumir uno de los servicios del modelo estatico dinamico$")
    public void queElUsuarioQuiereConsumirUnoDeLosServiciosDelModeloEstaticoDinamico() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("user");
        theActorInTheSpotlight().whoCan(CallAnApi.at(URI_INTEGRATE));
    }

    @Cuando("^el utiliza el servicio de consultar intencion de compra (.*)$")
    public void elUtilizaElServicioDeConsultarIntencionDeCompra(String idIntention) {
        theActorInTheSpotlight().attemptsTo(
                ConsultarIntencion.deCompra(idIntention)
        );
    }

    @Entonces("^el podra ver el resultado exitoso en la consulta de la intencion$")
    public void elPodraVerElResultadoExitosoEnLaConsultaDeLaIntencion(List<DataConsult> data) {
        theActorInTheSpotlight()
                .should(seeThat("El codigo de respuesta es de exito",
                        ElCodigoDeRespuesta.es(), equalTo(200)),
                        seeThat("La información traida corresponde con la esperada",
                                ConsultaFue.exitosa(data.get(0)), Matchers.is(true)
                        ));

    }

    @Entonces("^el podra ver el resultado fallido en la consulta del recurso inexistente (.*)$")
    public void elPodraVerElResultadoFallidoEnLaConsultaDelRecursoInexistente(String codigoError) {
        theActorInTheSpotlight()
                .should(GivenWhenThen.seeThat(ValidarConsultaIntenFallida.de(codigoError), Matchers.is(true)));

    }
}
