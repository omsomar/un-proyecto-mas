package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaRteLegalUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.AgregarSocios;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.awt.*;
import java.awt.event.KeyEvent;

@Log4j2
public class RegistrarInfoRteLegal implements Task {

    private String tipoDocumento;
    private String numDocumento;
    private String numAccionistas;

    public RegistrarInfoRteLegal(DataVinculacion data) {
        this.tipoDocumento = data.getTipoDoc();
        this.numDocumento = data.getNumDoc();
        this.numAccionistas = data.getNumAccionistas();
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PaginaRteLegalUI.NOMBRE_REPRESENTANTE, WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                Enter.theValue(Constantes.NOMBRE_RTE_LEGAL).into(PaginaRteLegalUI.NOMBRE_REPRESENTANTE),
                SelectFromOptions.byValue(this.tipoDocumento).from(PaginaRteLegalUI.TIPO_DOCUMENTO_RTE_LEGAL),
                Enter.theValue(this.numDocumento).into(PaginaRteLegalUI.NUM_DOCUMENTO_RTE_LEGAL),
                WaitUntil.the(PaginaRteLegalUI.CARGAR_ARCHIVO, WebElementStateMatchers.isClickable()).forNoMoreThan(20).seconds(),
                Click.on(PaginaRteLegalUI.CARGAR_ARCHIVO)
        );
        actor.remember("nombreRteLegal", Constantes.NOMBRE_RTE_LEGAL);
        PaginaRteLegalUI.ARCHIVO.resolveFor(actor).sendKeys(Constantes.RUTA_ARCHIVOS + Constantes.NOMBRE_ARCHIVO_DOCUMENTO_RTE);
        actor.attemptsTo(WaitUntil.the(PaginaRteLegalUI.ARCHIVO_SUBIDO, WebElementStateMatchers.isVisible()).forNoMoreThan(80).seconds());
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            log.error(e);
        }
        robot.keyPress(KeyEvent.VK_ESCAPE);
        robot.keyRelease(KeyEvent.VK_ESCAPE);

        if (numAccionistas != null) {
            AgregarSocios.paraComercio(actor, this.numAccionistas, PaginaRteLegalUI.TIENE_SOCIOS, PaginaRteLegalUI.AGREGAR_SOCIO,
                    PaginaRteLegalUI.NOMBRE_SOCIO, PaginaRteLegalUI.TIPO_DOC_SOCIO, PaginaRteLegalUI.NUM_DOC_SOCIO,
                    PaginaRteLegalUI.LINK_AGREGAR_SOCIO, PaginaRteLegalUI.BTN_AGREGAR_SOCIO);
        }

        actor.attemptsTo(
                WaitUntil.the(PaginaRteLegalUI.AVANZAR, WebElementStateMatchers.isVisible()).forNoMoreThan(50).seconds(),
                Click.on(PaginaRteLegalUI.AVANZAR),
                WaitUntil.the(PaginaRteLegalUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isVisible()).forNoMoreThan(50).seconds(),
                Click.on(PaginaRteLegalUI.CONFIRMAR_AVANZAR)
        );
    }

    public static RegistrarInfoRteLegal con(DataVinculacion data) {
        return Tasks.instrumented(RegistrarInfoRteLegal.class, data);
    }
}
