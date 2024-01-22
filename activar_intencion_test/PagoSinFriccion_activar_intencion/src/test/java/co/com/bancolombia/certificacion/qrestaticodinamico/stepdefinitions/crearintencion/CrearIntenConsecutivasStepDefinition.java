package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.crearintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.questions.ValidarRespuesta;
import co.com.bancolombia.certificacion.qrestaticodinamico.tasks.CrearIntencionConsecutiva;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Entonces;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class CrearIntenConsecutivasStepDefinition {

    @Cuando("^se crea otra intencion de compra para el mismo QR$")
    public void seCreaOtraIntencionDeCompraParaElMismoQR(List<DataServices> datos) {
        theActorInTheSpotlight().attemptsTo(CrearIntencionConsecutiva.crearIntencionConsecutiva(datos.get(0)));
    }

    @Entonces("^el podra ver que la intencion nueva queda pendiente y la anterior se cancele$")
    public void elPodraVerQueLaIntencionNuevaQuedaPendienteYLaAnteriorSeCancele() {
        theActorInTheSpotlight().should(
                seeThatResponse("El servicio responde correctamente", response -> response.statusCode(201)),
                seeThat(ValidarRespuesta.validarIntencionesConsecutivas(), Matchers.is(true)));
    }
}
