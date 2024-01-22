package co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions.crearintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.questions.ValidarExpiracion;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.GivenWhenThen;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

public class ExpirarIntencionStepDefinition {

    @Entonces("^se agota tiempo de espera y el logra ver que la intencion queda expirada$")
    public void seAgotaTiempoDeEsperaYElLograVerQueLaIntencionQuedaExpirada() {
        OnStage.theActorInTheSpotlight().should(
                GivenWhenThen.seeThat(ValidarExpiracion.validarIntencionExpirada(), Matchers.is(true))
        );
    }
}
