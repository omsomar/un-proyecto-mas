package co.com.bancolombia.certificacion.vinculacion.stepdefinitions;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

import co.com.bancolombia.certificacion.vinculacion.questions.ValidarVinculacionExitosa;
import co.com.bancolombia.certificacion.vinculacion.questions.ValidarVinculacionGuardada;

import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import org.hamcrest.Matchers;

import java.util.List;

public class GuardarVinculacionStepDefinition {

    @Entonces("^el podra ver el resultado exitoso en el registro de la vinculación$")
    public void elPodraVerElResultadoExitosoEnElRegistroDeLaVinculacion(List<DataVinculacion> data) {
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(
                ValidarVinculacionGuardada.con(data.get(0)), Matchers.is(true)));
        theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarVinculacionExitosa.con(), Matchers.equalTo(Constantes.MENSAJE_VINCULACION_EXITOSA)));
    }
}
