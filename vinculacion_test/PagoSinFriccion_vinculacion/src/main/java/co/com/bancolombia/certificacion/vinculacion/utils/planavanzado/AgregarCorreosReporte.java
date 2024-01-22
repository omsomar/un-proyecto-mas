package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.ModalCorreosReporteriaUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.Keys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarCorreosReporte {

    private static List<Map<String, String>> infoCorreos;

    private AgregarCorreosReporte() {

    }

    public static void con(Actor actor, Target correo, Target confirmacionCorreo,
                           Target linkAgregarCorreo, Target btnAgregarCorreo, String cantCorreos) {

        infoCorreos = new ArrayList<>();
        int cantidad = Integer.parseInt(cantCorreos);
        switch (cantidad) {
            case 1:
                escribirCorreo(actor, Constantes.EMAIL_REPORT_1, correo, confirmacionCorreo);
                guardarCorreos(actor, linkAgregarCorreo, btnAgregarCorreo);
                adicionarCorreoAMap(Constantes.EMAIL_REPORT_1);
                break;
            case 2:
                escribirCorreo(actor, Constantes.EMAIL_REPORT_1, correo, confirmacionCorreo);
                confirmarAgregarCorreo(actor, linkAgregarCorreo);
                adicionarCorreoAMap(Constantes.EMAIL_REPORT_1);
                escribirCorreo(actor, Constantes.EMAIL_REPORT_2, correo, confirmacionCorreo);
                guardarCorreos(actor, linkAgregarCorreo, btnAgregarCorreo);
                adicionarCorreoAMap(Constantes.EMAIL_REPORT_2);
                break;
            case 3:
                escribirCorreo(actor, Constantes.EMAIL_REPORT_1, correo, confirmacionCorreo);
                confirmarAgregarCorreo(actor, linkAgregarCorreo);
                adicionarCorreoAMap(Constantes.EMAIL_REPORT_1);
                escribirCorreo(actor, Constantes.EMAIL_REPORT_2, correo, confirmacionCorreo);
                confirmarAgregarCorreo(actor, linkAgregarCorreo);
                adicionarCorreoAMap(Constantes.EMAIL_REPORT_2);
                escribirCorreo(actor, Constantes.EMAIL_REPORT_3, correo, confirmacionCorreo);
                guardarCorreos(actor, linkAgregarCorreo, btnAgregarCorreo);
                adicionarCorreoAMap(Constantes.EMAIL_REPORT_3);
                break;

            default:
                Hit.the(Keys.ESCAPE);
                actor.attemptsTo(WaitUntil.the(ModalCorreosReporteriaUI.BTN_AGREGAR_CORREOS, WebElementStateMatchers.isNotEnabled()).forNoMoreThan(80).seconds());
                break;
        }
        actor.remember("listaCorreos", infoCorreos);

    }

    public static void escribirCorreo(Actor actor, String email, Target correo, Target confirmarCorreo) {
        actor.attemptsTo(
                Enter.theValue(email).into(correo),
                Enter.theValue(email).into(confirmarCorreo)
        );
    }

    public static void guardarCorreos(Actor actor, Target linkAgregarCorreo, Target btnAgregarCorreo) {
        actor.attemptsTo(WaitUntil.the(linkAgregarCorreo, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                Click.on(linkAgregarCorreo),
                WaitUntil.the(btnAgregarCorreo, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                Click.on(btnAgregarCorreo));
    }

    public static void confirmarAgregarCorreo(Actor actor, Target linkAgregarCorreo) {
        actor.attemptsTo(
                WaitUntil.the(linkAgregarCorreo, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                Click.on(linkAgregarCorreo));
    }

    public static List<Map<String, String>> adicionarCorreoAMap(String email) {
        Map<String, String> correoReport = new HashMap<>();
        correoReport.put("email", email);
        infoCorreos.add(correoReport);
        return infoCorreos;
    }

}
