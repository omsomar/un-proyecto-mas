package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaNegocioUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.AgregarCuentas;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.time.Duration;

@Log4j2
public class RegistrarInfoNegocio implements Task {

    private String actividadNegocio;
    private String tipoDocumento;
    private String numDocumento;
    private String tipoCuenta;
    private String numCuenta;
    private String cantCuentas;


    public RegistrarInfoNegocio(DataVinculacion data) {
        actividadNegocio = data.getActividadNeg();
        tipoDocumento = data.getTipoDoc();
        numDocumento = data.getNumDoc();
        tipoCuenta = data.getTipoCta();
        numCuenta = data.getNumeroCta();
        cantCuentas = data.getCantCuentas();
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(PaginaNegocioUI.RAZON_SOCIAL, WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                Enter.theValue("Distribuidora Zeus Asociados").into(PaginaNegocioUI.RAZON_SOCIAL),
                WaitUntil.the(PaginaNegocioUI.ACTIVIDAD_NEGOCIO, WebElementStateMatchers.containsSelectOption(actividadNegocio)).forNoMoreThan(15).seconds(),
                SelectFromOptions.byValue(actividadNegocio).from(PaginaNegocioUI.ACTIVIDAD_NEGOCIO)
        );
        actor.remember("razonSocial", "Distribuidora Zeus Asociados");
        Ensure.that(PaginaNegocioUI.ACTIVIDAD_NEGOCIO.waitingForNoMoreThan(Duration.ofSeconds(80))).selectedValue();
        actor.attemptsTo(SelectFromOptions.byValue(tipoDocumento).from(PaginaNegocioUI.TIPO_DOCUMENTO_NEGOCIO));
        Ensure.that(PaginaNegocioUI.TIPO_DOCUMENTO_NEGOCIO.waitingForNoMoreThan(Duration.ofSeconds(80))).selectedValue();
        actor.attemptsTo(Enter.theValue(numDocumento).into(PaginaNegocioUI.NUM_DOCUMENTO_NEGOCIO));
        actor.attemptsTo(
                WaitUntil.the(PaginaNegocioUI.CARGAR_ARCHIVO, WebElementStateMatchers.isClickable()).forNoMoreThan(15).seconds(),
                Click.on(PaginaNegocioUI.CARGAR_ARCHIVO));

        PaginaNegocioUI.ARCHIVO.resolveFor(actor).sendKeys(Constantes.RUTA_ARCHIVOS + Constantes.NOMBRE_ARCHIVO_CAMARA);
        actor.attemptsTo(WaitUntil.the(PaginaNegocioUI.ARCHIVO_SUBIDO, WebElementStateMatchers.isVisible()).forNoMoreThan(80).seconds());

        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            log.error(e);
        }
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

        AgregarCuentas.con(actor,
                cantCuentas,
                tipoCuenta,
                numCuenta,
                PaginaNegocioUI.NUMERO_CUENTA,
                PaginaNegocioUI.CONFIRMAR_NUMERO_CUENTA,
                PaginaNegocioUI.TIPO_CUENTA,
                PaginaNegocioUI.AÑADIR_CUENTA,
                PaginaNegocioUI.TIPO_CUENTA_ADICIONAL,
                PaginaNegocioUI.NUM_CUENTA_ADICIONAL,
                PaginaNegocioUI.CONFIRMAR_NUM_CUENTA_ADICIONAL,
                PaginaNegocioUI.LINK_AÑADIR_CUENTA,
                PaginaNegocioUI.BTN_AÑADIR_CUENTA,
                PaginaNegocioUI.LIST_CUENTAS_ADICIONALES);

        actor.attemptsTo(
                Ensure.that(PaginaNegocioUI.AVANZAR_FORM_RTE_LEGAL.waitingForNoMoreThan(Duration.ofSeconds(90))).isEnabled(),
                Click.on(PaginaNegocioUI.AVANZAR_FORM_RTE_LEGAL),
                WaitUntil.the(PaginaNegocioUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isVisible()).forNoMoreThan(80).seconds(),
                Click.on(PaginaNegocioUI.CONFIRMAR_AVANZAR));

    }

    public static RegistrarInfoNegocio con(DataVinculacion data) {
        return Tasks.instrumented(RegistrarInfoNegocio.class, data);
    }
}
