package co.com.bancolombia.certificacion.eliminacionexoneraciones.stepdefinitions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.CamposEliminarExoneracion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.questions.ValidarExoneraciones;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.questions.ValidarVinculacionBd;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.tasks.ConsumirMetodo;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.constantes.Constantes;
import cucumber.api.java.Before;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abiities.CallAnApi;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class EliminarExoneraExitosaStepDefinition {

        @Before
        public void configuracionInicial() {
            OnStage.setTheStage(new OnlineCast());
            OnStage.theActorCalled("Sharon");
        }

        @Dado("^que el usuario quiere consumir uno de los servicios de pagos sin friccion$")
        public void queElUsuarioQuiereConsumirUnoDeLosServiciosDePagosSinFriccion() {
            theActorInTheSpotlight().whoCan(CallAnApi.at(Constantes.URL_BASE));
        }

        @Cuando("^el utiliza el servicio de eliminar las exoneraciones$")
        public void elUtilizaElServicioDeEliminarLasExoneraciones(List<CamposEliminarExoneracion> filtros) {
            /*  se hace una consulta de las vinculaciones que tienen exoneración vencida*/
            OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarVinculacionBd.con(filtros.get(0))));
            /*  se hace el consumo del servicio eliminar exoneraciones  */
            theActorInTheSpotlight().attemptsTo(ConsumirMetodo.eliminacion(filtros.get(0)));
        }


        @Entonces("^el podra ver que las exoneraciones vencidas se inactivaron$")
        public void elPodraVerQueLasExoneracionesVencidasSeInactivaron() {
            /*  se verifica que las vinculaciones que se consultaron al inicio que estaban vencidas,
            *   correspondan con las vinculaciones que realmente actualizó el servicio */
            OnStage.theActorInTheSpotlight().should(
                   GivenWhenThen.seeThat(ValidarExoneraciones.de(), Matchers.is(true))
            );

        }

    }
