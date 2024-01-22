package co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions.consultarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.FiltrosIntencion;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion.ValidarRespuesta;
import co.com.bancolombia.certificacion.qrintencioncompra.tasks.ConsultarIntencionQr;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class ConsultarIntenQrExitosoStepDefinition {

    @Cuando("^se aplica el filtro id qr$")
    public void seAplicaElFiltroIdQr(List<FiltrosIntencion> data) {
        OnStage.theActorInTheSpotlight().attemptsTo(ConsultarIntencionQr.con(data.get(0)));
    }

    @Entonces("^el podra ver el resultado exitoso de la consulta filtrada por id qr (.*)")
    public void elPodraVerElResultadoExitosoDeLaConsultaFiltradaPorIdQr(String escenario) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarRespuesta.consultarIntenPorQr(escenario), Matchers.is(true)),
                seeThatResponse("La respuesta fue exitosa",
                        response -> response.statusCode(200))
        );
    }
}
