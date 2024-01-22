package co.com.bancolombia.certificacion.vinculacion.questions;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.VinculacionExitosaUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import lombok.SneakyThrows;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import java.time.Duration;

public class ValidarVinculacionExitosa implements Question<String>{

    private ValidarVinculacionExitosa() {
    }

    @SneakyThrows
    @Override
    public String answeredBy(Actor actor) {
        actor.attemptsTo(
                Ensure.that(VinculacionExitosaUI.TITULO.waitingForNoMoreThan(Duration.ofSeconds(80))).text().isEqualTo(Constantes.MENSAJE_VINCULACION_EXITOSA),
                Ensure.that(VinculacionExitosaUI.DESCRIPCION.waitingForNoMoreThan(Duration.ofSeconds(80))).text().isNotEmpty()
        );
        return Text.of(VinculacionExitosaUI.TITULO).viewedBy(actor).asString();
    }

    public static ValidarVinculacionExitosa con() {
        return new ValidarVinculacionExitosa();
    }
}
