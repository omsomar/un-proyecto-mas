package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.listarnotificacion;


import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;
import co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion.ValidarConsultaFallida;
import co.com.bancolombia.certificacion.infologtransaccional.tasks.listarnotificacion.ListarNotificacionFallida;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

public class ListarNotificacionFallidaStepDefinitions {


    @Cuando("^se filtran por los campos de consulta incorrectos$")
    public void seFiltranPorLosCamposDeConsultarIncorrectos(List<FiltrosListar> filtros) {
        OnStage.theActorInTheSpotlight().attemptsTo(ListarNotificacionFallida.de(filtros.get(0)));
    }

    @Entonces("^el podra ver el resultado fallido de la consulta de notificaciones (.*) (.*)$")
    public void elPodraVerElResultadoExitosoDeLaConsultaDeNotificaciones(String codigoError, String campo) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarConsultaFallida.de(codigoError, campo), Matchers.is(true))
        );
    }
}
