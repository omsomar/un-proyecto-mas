package co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions.listarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.FiltrosIntencion;
import co.com.bancolombia.certificacion.qrintencioncompra.questions.listarintencion.ValidarPaginacion;
import co.com.bancolombia.certificacion.qrintencioncompra.tasks.ListarIntenPorPagina;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

public class PaginarListIntenStepDefinitions {

    @Cuando("^se obtienen las intenciones de la segunda pagina de resultado$")
    public void seObtienenLasIntencionesDeLaSegundaPaginaDeResultado(List<FiltrosIntencion> filtros) {
        OnStage.theActorInTheSpotlight().attemptsTo(ListarIntenPorPagina.con(filtros.get(0)));
    }

    @Entonces("^se podra obtener intenciones distintas en cada pagina de resultado$")
    public void sePodraObtenerIntencionesDistintasEnCadaPaginaDeResultados() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarPaginacion.de(), Matchers.is(true))
        );
    }


}
