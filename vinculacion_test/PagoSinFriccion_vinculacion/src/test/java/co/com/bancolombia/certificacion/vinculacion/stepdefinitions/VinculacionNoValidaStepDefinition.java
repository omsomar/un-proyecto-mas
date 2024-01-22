package co.com.bancolombia.certificacion.vinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.questions.ValidarVinculacionFallida;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoContacto;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoNegocio;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoRteLegal;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

public class VinculacionNoValidaStepDefinition {

    @Cuando("^el comienza el proceso de vinculacion$")
    public void elComienzaElProcesoDeVinculacion(List<DataVinculacion> data) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarInfoContacto.vinculacion(data.get(0)),
                RegistrarInfoNegocio.con(data.get(0)),
                RegistrarInfoRteLegal.con(data.get(0))
        );
    }

    @Entonces("^el podra ver un mensaje solicitud de vinculacion no valida (.*)$")
    @Y("^el podra ver un mensaje con el estado fallido en la solicitud de la validacion (.*)$")
    public void elPodraVerUnMensajeSolicitudDeVinculacionNoValida(String estado) {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(
                ValidarVinculacionFallida.con(estado), Matchers.is(true)
        ));
    }
}
