package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

public class IngresarComision {

    private IngresarComision() {

    }

    public static void con(Actor actor, Target tipoComision,
                           Target valorComisionPorcentual,
                           Target valorComisionFija,
                           String tipoComisionParam,
                           String valorComisionParam) {
        actor.attemptsTo(
                WaitUntil.the(tipoComision, WebElementStateMatchers.isVisible()).forNoMoreThan(50).seconds(),
                SelectFromOptions.byValue(tipoComisionParam).from(tipoComision),
                Ensure.that(tipoComision.waitingForNoMoreThan(Duration.ofSeconds(70))).selectedValue().asAString().isEqualTo(tipoComisionParam)
        );

        switch (tipoComisionParam) {
            case "F":
                actor.attemptsTo(Enter.theValue(valorComisionParam).into(valorComisionFija));
                break;
            case "P":
                actor.attemptsTo(Enter.theValue(valorComisionParam).into(valorComisionPorcentual));
                break;
            default:
                break;
        }
    }


}
