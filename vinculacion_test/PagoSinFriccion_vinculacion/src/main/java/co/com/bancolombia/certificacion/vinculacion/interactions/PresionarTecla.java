package co.com.bancolombia.certificacion.vinculacion.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

public class PresionarTecla implements Interaction {

    private Target target;
    private Keys key;

    public PresionarTecla(Target target, Keys tecla) {
        this.target = target;
        this.key = tecla;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        target.resolveFor(actor).sendKeys(this.key);
    }

    public static PresionarTecla unaTecla(Target target, Keys tecla) {
        return new PresionarTecla(target, tecla);
    }
}
