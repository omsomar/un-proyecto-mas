package co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions.listarnotificacion;


import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;
import co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion.ValidarElementosPaginacion;
import co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion.ValidarPaginacionLista;
import co.com.bancolombia.certificacion.infologtransaccional.tasks.listarnotificacion.ListarNotificacionPagina;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

public class ListarNotifPagStepDefinitions {


    @Cuando("^se obtienen las notificaciones de la segunda pagina de resultado$")
    public void seObtienenLasNotificacionesDeLaSegundaPaginaDeResultado(List<FiltrosListar> filtros) {
        OnStage.theActorInTheSpotlight().attemptsTo(ListarNotificacionPagina.de(filtros.get(0)));
    }

    @Entonces("^el podra ver que los resultados en cada pagina son distintos$")
    public void elPodraVerQueLosResultadosEnCadaPaginaSonDistintos() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarPaginacionLista.de(), Matchers.is(true)));
    }

    @Entonces("^se verifica que la paginacion sea la esperada$")
    public void seVerificarQueLaPaginacionSeaLaEsperada() {
        OnStage.theActorInTheSpotlight().should(GivenWhenThen.seeThat(ValidarElementosPaginacion.de()));
    }

}
