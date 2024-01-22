package co.com.bancolombia.certificacion.eliminacionexoneraciones.stepdefinitions;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.CamposEliminarExoneracion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.questions.ValidarEliminacionExoneraciones;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.questions.ValidarVigenciaExoneraciones;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.questions.ValidarVinculacionBdVigentes;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.tasks.ConsumirMetodo;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class EliminarExoneraNoVencidaStepDefinition {

        @Cuando("^el utiliza el servicio de eliminar las exoneraciones teniendo exoneraciones vigentes$")
        public void elUtilizaElServicioDeEliminarLasExoneracionesTeniendoExoneracionesVigentes(List<CamposEliminarExoneracion> filtros) {
            /*  se hace el consumo del servicio eliminar exoneraciones  */
            theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarVinculacionBdVigentes.con(), Matchers.is(true)));
            theActorInTheSpotlight().attemptsTo(ConsumirMetodo.eliminacion(filtros.get(0)));
        }


        @Entonces("^el podra ver que las exoneraciones vigentes no se inactivaron$")
        public void elPodraVerQueLasExoneracionesVigentesNoSeInactivaron() {

            OnStage.theActorInTheSpotlight().should(
                   GivenWhenThen.seeThat(ValidarVigenciaExoneraciones.de(), Matchers.is(true)),
                    GivenWhenThen.seeThat(ValidarEliminacionExoneraciones.de(), Matchers.is(true))
            );


        }

    }
