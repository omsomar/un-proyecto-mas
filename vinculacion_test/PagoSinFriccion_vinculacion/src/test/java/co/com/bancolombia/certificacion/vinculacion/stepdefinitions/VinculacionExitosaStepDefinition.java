package co.com.bancolombia.certificacion.vinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.questions.ValidarVinculacionExitosa;
import co.com.bancolombia.certificacion.vinculacion.tasks.Iniciar;
import co.com.bancolombia.certificacion.vinculacion.tasks.IniciarSesion;
import co.com.bancolombia.certificacion.vinculacion.tasks.SeleccionarPlan;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.*;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import org.hamcrest.Matchers;

import java.util.List;

public class VinculacionExitosaStepDefinition {

    @Dado("^que el usuario se encuentra en el Portal Interno de Generacion$")
    public void queElUsuarioSeEncuentraEnElPortalInternoDeGeneracion(List<DataVinculacion> data) {

        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Asesor");
        OnStage.theActorInTheSpotlight().attemptsTo(Iniciar.actividad(data.get(0)));
        OnStage.theActorInTheSpotlight().attemptsTo(IniciarSesion.con());
    }

    @Cuando("^el escoge el Plan Avanzado para Vinculacion QR (.*)$")
    public void elEscogeElPlanAvanzadoParaVinculacionQR(String planSeleccionado) {
        OnStage.theActorInTheSpotlight().attemptsTo(SeleccionarPlan.deVinculacion(planSeleccionado));
    }

    @Cuando("^el comienza el proceso de vinculacion con informacion validada exitosamente (.*)$")
    @Y("^el comienza el proceso de vinculacion incluyendo una cuenta no apta (.*)")
    public void elComienzaElProcesoDeVinculacionConInformacionValidadaExitosamente(String estado, List<DataVinculacion> data) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarInfoContacto.vinculacion(data.get(0)),
                RegistrarInfoNegocio.con(data.get(0)),
                RegistrarInfoRteLegal.con(data.get(0)),
                ValidarInfoVinculacion.con(estado)
        );
    }

    @Cuando("^se complete el proceso de vinculacion$")
    public void seCompleteElProcesoDeVinculacion(List<DataVinculacion> lista) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarInfoRespTecnico.con(lista.get(0)),
                RegistrarInfoNegociacion.con(lista.get(0)),
                AceptarAcuerdo.con()
        );
    }

    @Entonces("^el podra ver un mensaje de Vinculacion Exitosa$")
    public void elPodraVerUnMensajeDeVinculacionExitosa() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarVinculacionExitosa.con(), Matchers.equalTo(Constantes.MENSAJE_VINCULACION_EXITOSA))
        );

    }
}
