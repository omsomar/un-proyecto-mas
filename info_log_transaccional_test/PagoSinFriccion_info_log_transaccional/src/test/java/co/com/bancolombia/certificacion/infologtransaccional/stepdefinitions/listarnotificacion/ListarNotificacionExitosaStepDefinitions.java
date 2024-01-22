package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.listarnotificacion;


import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;
import co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion.ValidarInformacion;
import co.com.bancolombia.certificacion.infologtransaccional.tasks.listarnotificacion.ListarNotificacion;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import cucumber.api.java.es.Y;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

public class ListarNotificacionExitosaStepDefinitions {


    @Cuando("^se filtran por los campos de consulta$")
    @Y("^se filtran por los campos de consulta y obtienen los resultados de la primer pagina$")
    public void seFiltranPorLosCamposDeConsulta(List<FiltrosListar> filtros) {
        OnStage.theActorInTheSpotlight().attemptsTo(ListarNotificacion.de(filtros.get(0)));
    }

    @Entonces("^el podra ver el resultado exitoso de la consulta de notificaciones$")
    public void elPodraVerElResultadoExitosoDeLaConsultaDeNotificaciones(List<FiltrosListar> filtros) {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarInformacion.notificaciones(filtros.get(0)), Matchers.is(true))
        );
    }
}
