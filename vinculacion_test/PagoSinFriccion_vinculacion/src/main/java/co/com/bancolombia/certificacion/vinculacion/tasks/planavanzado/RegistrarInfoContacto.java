package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.interactions.EscogerFechaCalendario;
import co.com.bancolombia.certificacion.vinculacion.interactions.Wait;
import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.CalendarioUI;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaContactoUI;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.AgregarPersonasNotificar;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.ManejarFecha;
import lombok.SneakyThrows;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.Date;
import java.util.List;

public class RegistrarInfoContacto implements Task {

    private String modelo;
    private String personasNotificar;

    public RegistrarInfoContacto(DataVinculacion data) {
        modelo = data.getModelo();
        personasNotificar = data.getPersonasNotificar();
    }

    @SneakyThrows
    @Override
    public <T extends Actor> void performAs(T actor) {
        /*Se llaman las variables de sistema para la fecha del calendario*/
        String anio = System.getProperty("anio");
        String mes = System.getProperty("mes1");
        String dia = System.getProperty("dia1");

        int codigoAsesor = 0;
        switch (this.modelo) {
            case "QR Estático":
                actor.attemptsTo(
                        WaitUntil.the(PaginaContactoUI.MODELO_QR_ESTATICO, WebElementStateMatchers.isVisible()).forNoMoreThan(20).seconds(),
                        Click.on(PaginaContactoUI.MODELO_QR_ESTATICO),
                        Enter.theValue(String.valueOf(codigoAsesor)).into(PaginaContactoUI.CODIGO_ASESOR)
                );
                break;
            case "QR Integrado":
                codigoAsesor = 1;
                actor.attemptsTo(
                        WaitUntil.the(PaginaContactoUI.MODELO_QR_INTEGRADO, WebElementStateMatchers.isPresent()).forNoMoreThan(20).seconds(),
                        Click.on(PaginaContactoUI.MODELO_QR_INTEGRADO),
                        Enter.theValue(String.valueOf(codigoAsesor)).into(PaginaContactoUI.CODIGO_ASESOR)
                );
                break;
            case "QR Mixto":
                codigoAsesor = 100;
                actor.attemptsTo(
                        WaitUntil.the(PaginaContactoUI.MODELO_QR_MIXTO, WebElementStateMatchers.isVisible()).forNoMoreThan(20).seconds(),
                        Click.on(PaginaContactoUI.MODELO_QR_MIXTO),
                        Enter.theValue(String.valueOf(codigoAsesor)).into(PaginaContactoUI.CODIGO_ASESOR)
                );
                break;
            default:
                break;
        }
        actor.attemptsTo(
                EscogerFechaCalendario.con(PaginaContactoUI.CALENDARIO,
                        CalendarioUI.SELECTOR_ANIO, CalendarioUI.ANIO,
                        CalendarioUI.SELECTOR_MES, CalendarioUI.MES,
                        CalendarioUI.DIA, CalendarioUI.CONFIRMAR_FECHA,
                        anio, mes, dia
                ));

        List<WebElementFacade> listaChecks = PaginaContactoUI.CHECKS_CONTACTO.resolveAllFor(actor);
        int i = 3;
        while (i < listaChecks.size()) {
            actor.attemptsTo(Click.on(listaChecks.get(i)));
            i++;
        }

        AgregarPersonasNotificar.con(actor, this.personasNotificar, PaginaContactoUI.NOMBRE_COMPLETO,
                PaginaContactoUI.CELULAR_CONTACTO, PaginaContactoUI.EMAIL_CONTACTO,
                PaginaContactoUI.EMAIL_SECUNDARIO_1, PaginaContactoUI.EMAIL_SECUNDARIO_2,
                PaginaContactoUI.EMAIL_SECUNDARIO_3, PaginaContactoUI.EMAIL_SECUNDARIO_4,
                PaginaContactoUI.BTN_AGREGAR_OTRA_PERSONA, PaginaContactoUI.ENLACE_AÑADIR_MODAL,
                PaginaContactoUI.BTN_AÑADIR_MODAL);

        actor.attemptsTo(
                Wait.per(3000),
                WaitUntil.the(PaginaContactoUI.AVANZAR_FORM_NEGOCIO, WebElementStateMatchers.isEnabled()).forNoMoreThan(80).seconds(),
                Click.on(PaginaContactoUI.AVANZAR_FORM_NEGOCIO),
                WaitUntil.the(PaginaContactoUI.VISUALIZACION_FECHA_VISITA, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds()
        );
        String fecha = PaginaContactoUI.VISUALIZACION_FECHA_VISITA.resolveFor(actor).getText();
        Date fechaConvertida = ManejarFecha.cambiarFormatoAFecha(fecha);

        actor.remember("fechaVisita", ManejarFecha.cambiarFechaAString(fechaConvertida));
        actor.remember("codigoAsesor", codigoAsesor);

        actor.attemptsTo(WaitUntil.the(PaginaContactoUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isEnabled()).forNoMoreThan(80).seconds(),
                Click.on(PaginaContactoUI.CONFIRMAR_AVANZAR));
    }

    public static RegistrarInfoContacto vinculacion(DataVinculacion data) {
        return Tasks.instrumented(RegistrarInfoContacto.class, data);
    }
}
