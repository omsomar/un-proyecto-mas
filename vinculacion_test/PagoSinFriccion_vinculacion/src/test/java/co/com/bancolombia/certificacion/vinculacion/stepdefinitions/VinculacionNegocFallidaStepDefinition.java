package co.com.bancolombia.certificacion.vinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.questions.ValidarErrorEnNegociacion;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoNegociacion;
import co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado.RegistrarInfoRespTecnico;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

public class VinculacionNegocFallidaStepDefinition {

    @Cuando("^se complete el proceso de vinculacion con información para entrega de reporteria erronea$")
    public void seCompleteElProcesoDeVinculacion(List<DataVinculacion> lista) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                RegistrarInfoRespTecnico.con(lista.get(0)),
                RegistrarInfoNegociacion.con(lista.get(0))
        );
    }

    @Entonces("^no se debe permitir avanzar al siguiente formulario$")
    public void noSeDebePermitirAvanzarAlSiguienteFormulario() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarErrorEnNegociacion.con(), Matchers.is(true))
        );
    }
}
