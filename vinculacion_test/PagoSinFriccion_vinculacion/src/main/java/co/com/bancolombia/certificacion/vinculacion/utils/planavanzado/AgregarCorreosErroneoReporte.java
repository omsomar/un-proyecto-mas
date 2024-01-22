package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.interactions.PresionarTecla;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaNegociacionUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.util.List;

public class AgregarCorreosErroneoReporte {

    private AgregarCorreosErroneoReporte() {

    }

    public static void con(Actor actor, Target correo, Target confirmacionCorreo,
                           Target linkAgregarCorreo, Target btnAgregarCorreo,
                           Target correosAgregados, Target btnEliminarCorreo, Target txtError, String escenarioError) {

        actor.attemptsTo(
                WaitUntil.the(PaginaNegociacionUI.BTN_AGREGAR_CORREOS, WebElementStateMatchers.isEnabled()).forNoMoreThan(60).seconds(),
                Click.on(PaginaNegociacionUI.BTN_AGREGAR_CORREOS));

        switch (escenarioError) {
            case "sinCorreoDestinatario":
                actor.attemptsTo(Click.on(btnAgregarCorreo));
                break;
            case "correosNoCoinciden":
                actor.attemptsTo(Enter.theValue("pasteleria@gmail.com").into(correo), Enter.theValue("pastelesZeus.10@gmail.com").into(confirmacionCorreo));
                Ensure.that(txtError.waitingForNoMoreThan(Duration.ofSeconds(80))).text().containsIgnoringCase("Los correos no coinciden");
                actor.attemptsTo(PresionarTecla.unaTecla(confirmacionCorreo, Keys.ESCAPE));
                break;
            case "correoSinPunto":
                escribirCorreo(actor, "asesor.comercial@hotmailcom", correo, confirmacionCorreo);
                verificarError(actor, txtError, confirmacionCorreo);
                break;
            case "correoSinArroba":
                escribirCorreo(actor, "asesor.comercialhotmail.com", correo, confirmacionCorreo);
                verificarError(actor, txtError, confirmacionCorreo);
                break;
            case "correoConEspacio":
                escribirCorreo(actor, "asesor comercial@hotmail.com", correo, confirmacionCorreo);
                verificarError(actor, txtError, confirmacionCorreo);
                break;
            case "eliminarCorreos":
                escribirCorreo(actor, "asesor.comercial@hotmail.com", correo, confirmacionCorreo);
                eliminarCorreo(actor, "asesor.comercial@hotmail.com", linkAgregarCorreo, btnAgregarCorreo, correosAgregados, btnEliminarCorreo);
                break;
            default:
                break;
        }

    }

    public static void escribirCorreo(Actor actor, String email, Target correo, Target confirmarCorreo) {
        actor.attemptsTo(Enter.theValue(email).into(correo),
                Enter.theValue(email).into(confirmarCorreo));
    }

    public static void eliminarCorreo(Actor actor, String correoCoincide, Target linkAgregarCorreo, Target btnAgregarCorreo, Target correosAgregados, Target btnEliminarCorreo) {
        actor.attemptsTo(
                WaitUntil.the(linkAgregarCorreo.waitingForNoMoreThan(Duration.ofSeconds(80)), WebElementStateMatchers.isClickable()),
                Click.on(linkAgregarCorreo),
                WaitUntil.the(btnAgregarCorreo.waitingForNoMoreThan(Duration.ofSeconds(80)), WebElementStateMatchers.isClickable()),
                Click.on(btnAgregarCorreo),
                Ensure.that(correosAgregados.waitingForNoMoreThan(Duration.ofSeconds(80))).text().contains(correoCoincide)
        );
        actor.attemptsTo(Click.on(btnEliminarCorreo),
                WaitUntil.the(correosAgregados, WebElementStateMatchers.isNotPresent()).forNoMoreThan(80).seconds()
        );
    }

    public static void verificarError(Actor actor, Target txtError, Target confirmacionCorreo) {
        List<WebElementFacade> errores = txtError.resolveAllFor(actor);
        for (WebElementFacade errorModal : errores) {
            Ensure.that(errorModal.waitUntilEnabled().getText().contains(Constantes.MENSAJE_ERROR_CORREOS));
        }
        actor.attemptsTo(PresionarTecla.unaTecla(confirmacionCorreo, Keys.ESCAPE));
    }
}
