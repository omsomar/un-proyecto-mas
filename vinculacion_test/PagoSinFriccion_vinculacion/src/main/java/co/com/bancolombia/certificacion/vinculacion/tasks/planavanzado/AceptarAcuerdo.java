package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaAcuerdoUI;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaNegociacionUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

@Log4j2
public class AceptarAcuerdo implements Task {

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        String mensajeTyC = "Declaraciones del funcionario:\n" +
                "\n" +
                "Acepto haber realizado una gestión responsable y una correcta validación de la información y documentación entregada por cliente, física y de forma verbal; incluyendo el reconocimiento y la debida autenticación del cliente, las validaciones de riesgos, cumplimiento, y demás requisitos exigidos por Bancolombia.\n" +
                "\n" +
                "Además, manifiesto que todos los datos y toda la documentación aquí consignados es cierta y que la información que adjunto es veraz y verificable en virtud de la información solicitada por Bancolombia y aportada por el cliente titular de la solicitud";
        actor.attemptsTo(
                WaitUntil.the(PaginaNegociacionUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isNotVisible()).forNoMoreThan(60).seconds(),
                Ensure.that(PaginaAcuerdoUI.TEXTO_TERMINOS.waitingForNoMoreThan(Duration.ofSeconds(30))).text().isEqualTo(mensajeTyC),
                WaitUntil.the(PaginaAcuerdoUI.CHECK_TYC, WebElementStateMatchers.isClickable()).forNoMoreThan(30).seconds(),
                Click.on(PaginaAcuerdoUI.CHECK_TYC),
                Click.on(PaginaAcuerdoUI.CARGAR_ARCHIVO)
        );

        PaginaAcuerdoUI.ARCHIVO.resolveFor(actor).sendKeys(Constantes.RUTA_ARCHIVOS + Constantes.NOMBRE_ARCHIVO_ACUERDO);
        actor.attemptsTo(WaitUntil.the(PaginaAcuerdoUI.ARCHIVO_SUBIDO, WebElementStateMatchers.isVisible()).forNoMoreThan(80).seconds());
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            log.error(e);
        }
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

        actor.attemptsTo(
                WaitUntil.the(PaginaAcuerdoUI.AVANZAR, WebElementStateMatchers.isVisible()).forNoMoreThan(40).seconds(),
                Click.on(PaginaAcuerdoUI.AVANZAR),
                WaitUntil.the(PaginaAcuerdoUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isVisible()).forNoMoreThan(40).seconds(),
                Click.on(PaginaAcuerdoUI.CONFIRMAR_AVANZAR)
        );
    }

    public static AceptarAcuerdo con() {
        return Tasks.instrumented(AceptarAcuerdo.class);
    }
}
