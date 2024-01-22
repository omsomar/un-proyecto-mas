package co.com.bancolombia.certificacion.eliminacionexoneraciones.stepdefinitions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.CamposEliminarExoneracion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.questions.ValidarRespuestaFallida;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.tasks.ConsumirMetodoFallido;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class EliminarExoneraFallidaStepDefinition {

        @Cuando("^se utiliza el servicio eliminacion de exoneracion con parametros de filtro invalidos$")
        public void seUtilizaElServicioEliminacionDeExoneracionConParametrosDeFiltroInvalidos(List<CamposEliminarExoneracion> filtros) {
            /*  se hace el consumo del servicio eliminar exoneraciones  */
            theActorInTheSpotlight().attemptsTo(ConsumirMetodoFallido.eliminacion(filtros.get(0)));
        }


        @Entonces("^el podra ver la respuesta de error del servicio (\\d+)$")
        public void elPodraVerLaRespuestaDeErrorDelServicio(int codigoResp) {

            OnStage.theActorInTheSpotlight().should(
                   GivenWhenThen.seeThat(ValidarRespuestaFallida.de(codigoResp), Matchers.is(true))
            );

        }

    }
