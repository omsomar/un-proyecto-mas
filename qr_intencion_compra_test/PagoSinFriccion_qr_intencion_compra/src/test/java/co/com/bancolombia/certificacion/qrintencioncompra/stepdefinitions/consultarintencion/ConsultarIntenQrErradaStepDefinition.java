package co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions.consultarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion.ValidarRespuestaFallida;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

public class ConsultarIntenQrErradaStepDefinition {


    @Entonces("^el podra ver el resultado erroneo de la consulta filtrada por id qr (.*)$")
    public void elPodraVerElResultadoErroneoDeLaConsultaFiltradaPorIdQr(String escenario) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarRespuestaFallida.consultarIntenPorQr(escenario), Matchers.is(true))
        );

    }
}
