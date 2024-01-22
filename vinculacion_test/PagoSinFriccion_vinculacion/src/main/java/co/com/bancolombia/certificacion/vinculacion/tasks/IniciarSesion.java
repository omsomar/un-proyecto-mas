package co.com.bancolombia.certificacion.vinculacion.tasks;

import co.com.bancolombia.certificacion.vinculacion.interactions.PresionarTecla;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.LoginUI;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaPcipalUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

public class IniciarSesion implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        String usuario = System.getProperty("UABC");
        String clave = System.getProperty("PABC").trim();

        actor.attemptsTo(
                WaitUntil.the(LoginUI.CORREO, WebElementStateMatchers.isVisible()).forNoMoreThan(40).seconds(),
                Enter.theValue(usuario).into(LoginUI.CORREO),
                PresionarTecla.unaTecla(LoginUI.CORREO, Keys.ENTER),
                WaitUntil.the(LoginUI.CLAVE, WebElementStateMatchers.isVisible()).forNoMoreThan(40).seconds()
        );
        actor.attemptsTo(
                Enter.theValue(clave).into(LoginUI.CLAVE),
                PresionarTecla.unaTecla(LoginUI.CLAVE, Keys.ENTER),
                WaitUntil.the(PaginaPcipalUI.ICONO_DESLOGUEAR, WebElementStateMatchers.isVisible()).forNoMoreThan(180).seconds()
        );
    }

    public static IniciarSesion con() {
        return Tasks.instrumented(IniciarSesion.class);
    }
}
