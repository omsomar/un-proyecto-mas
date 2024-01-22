package co.com.bancolombia.certificacion.vinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoContacto;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoNegocio;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoRteLegal;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.ValidarInfoVinculacion;
import cucumber.api.java.es.Cuando;
import net.serenitybdd.screenplay.actors.OnStage;

import java.util.List;

public class VinculacionValidacionPendienteStepDefinition {

    @Cuando("^el comienza el proceso de vinculacion y visualiza el estado de solicitud pendiente (.*)$")
    public void elComienzaElProcesoDeVinculacion(String estado, List<DataVinculacion> data) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarInfoContacto.vinculacion(data.get(0)),
                RegistrarInfoNegocio.con(data.get(0)),
                RegistrarInfoRteLegal.con(data.get(0)),
                ValidarInfoVinculacion.con(estado)
        );
    }
}
