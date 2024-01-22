package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.interactions.EscogerFechaCalendario;
import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.CalendarioUI;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.ModalCorreosReporteriaUI;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaNegociacionUI;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.AgregarCorreosErroneoReporte;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.AgregarCorreosReporte;
import co.com.bancolombia.certificacion.vinculacion.utils.planavanzado.IngresarComision;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Scroll;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;

public class RegistrarInfoNegociacion implements Task {

    private String tipoComision;
    private String valorComision;
    private String aplicaExoneracion;
    private String tieneNotificaciones;
    private String saldosConsolidados;
    private String reporteTrxDiario;
    private String estructuradoPor;
    private String opcionEntrega;
    private String cantCorreos;
    private String escenarioError;

    public RegistrarInfoNegociacion(DataVinculacion data) {
        tipoComision = data.getTipoComision();
        valorComision = data.getValorComision();
        aplicaExoneracion = data.getExoneracion();
        tieneNotificaciones = data.getNotificacion();
        saldosConsolidados = data.getSaldosConsolidados();
        reporteTrxDiario = data.getReporteTrxDiario();
        estructuradoPor = data.getEstructuradoPor();
        opcionEntrega = data.getOpcionEntrega();
        cantCorreos = data.getCantCorreos();
        escenarioError = data.getEscenario();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        IngresarComision.con(actor, PaginaNegociacionUI.TIPO_COMISION, PaginaNegociacionUI.VALOR_PORCENTUAL,
                PaginaNegociacionUI.VALOR_FIJO, this.tipoComision, this.valorComision);
        actor.attemptsTo(Ensure.that(PaginaNegociacionUI.TEXTO_COMPL_APLICA_EXONERACION.waitingForNoMoreThan(Duration.ofSeconds(80))).isDisplayed());

        if ("Si".equalsIgnoreCase(this.aplicaExoneracion)) {
            /*Se llaman las variables de sistema para las fechas de inicio y fin de exoneración*/
            String anio = System.getProperty("anio");
            String mesInicio = System.getProperty("mes2");
            String diaInicio = System.getProperty("dia2");
            String mesFin = System.getProperty("mes3");
            String diaFin = System.getProperty("dia3");

            actor.attemptsTo(
                    Click.on(PaginaNegociacionUI.TIENE_EXONERACION),
                    EscogerFechaCalendario.con(PaginaNegociacionUI.FECHA_INICIO_EXONERACION,
                            CalendarioUI.SELECTOR_ANIO, CalendarioUI.ANIO, CalendarioUI.SELECTOR_MES,
                            CalendarioUI.MES_EXONERACION, CalendarioUI.DIA, CalendarioUI.CONFIRMAR_FECHA,
                            anio, mesInicio, diaInicio),
                    EscogerFechaCalendario.con(PaginaNegociacionUI.FECHA_FIN_EXONERACION,
                            CalendarioUI.SELECTOR_ANIO_FIN, CalendarioUI.ANIO, CalendarioUI.SELECTOR_MES_FIN,
                            CalendarioUI.MES_EXONERACION, CalendarioUI.DIA_FIN, CalendarioUI.CONFIRMAR_FECHA_FIN,
                            anio, mesFin, diaFin));
        }

        /* El asesor selecciona si desea recibir saldos consolidados en el extracto*/
        if (saldosConsolidados != null && "Si".equalsIgnoreCase(saldosConsolidados)) {
            actor.attemptsTo(
                    WaitUntil.the(PaginaNegociacionUI.ACEPTA_SALDOS_CONSOLIDADO, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                    Click.on(PaginaNegociacionUI.ACEPTA_SALDOS_CONSOLIDADO));
        } else {
            actor.attemptsTo(
                    WaitUntil.the(PaginaNegociacionUI.RECHAZA_SALDOS_CONSOLIDADO, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                    Click.on(PaginaNegociacionUI.RECHAZA_SALDOS_CONSOLIDADO));
        }

        /* El asesor selecciona si desea recibir reporte transaccional diario*/
        /*En caso de escoger que SI se desea recibir el reporte,
         * se debe indicar la estructura del reporte
         * y también indicar la opción de entrega del correo*/
        if (reporteTrxDiario != null && "Si".equalsIgnoreCase(reporteTrxDiario)) {
            actor.attemptsTo(
                    WaitUntil.the(PaginaNegociacionUI.ACEPTA_REPORTE_TRX_DIARIO, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                    Click.on(PaginaNegociacionUI.ACEPTA_REPORTE_TRX_DIARIO));

            /*Se escoge la estructuración del reporte trx diario*/
            if (estructuradoPor != null && "documento titular".equalsIgnoreCase(estructuradoPor)) {
                actor.attemptsTo(Click.on(PaginaNegociacionUI.DOCUMENTO_TITULAR_CTA));
            } else if (estructuradoPor != null && "documento titular y cuenta".equalsIgnoreCase(estructuradoPor)) {
                actor.attemptsTo(Click.on(PaginaNegociacionUI.DOCUMENTO_Y_CUENTA_TITULAR_CTA));
            }
            /*Se escoge la opción de entrega para recibir el reporte:
             * SFTP: Sterling
             * Descargable: Correo
             *En caso de escoger que la opción de entrega sea DESCARGABLE,
             * se debe indicar la cantidad de correos destinatarios
             * Podrán agregarse hasta máximo 3 correos
             * */
            if (opcionEntrega != null && "SFTP".equalsIgnoreCase(opcionEntrega)) {
                actor.attemptsTo(
                        WaitUntil.the(PaginaNegociacionUI.OPCION_ENTREGA_SFTP, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                        Click.on(PaginaNegociacionUI.OPCION_ENTREGA_SFTP));
            } else if (opcionEntrega != null && "Descargable".equalsIgnoreCase(opcionEntrega)) {
                actor.attemptsTo(
                        WaitUntil.the(PaginaNegociacionUI.OPCION_ENTREGA_DESCARGABLE, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                        Click.on(PaginaNegociacionUI.OPCION_ENTREGA_DESCARGABLE));

                if (this.cantCorreos != null && !"".equalsIgnoreCase(this.cantCorreos)) {
                    actor.attemptsTo(
                            WaitUntil.the(PaginaNegociacionUI.BTN_AGREGAR_CORREOS, WebElementStateMatchers.isEnabled()).forNoMoreThan(60).seconds(),
                            Click.on(PaginaNegociacionUI.BTN_AGREGAR_CORREOS));
                    AgregarCorreosReporte.con(actor, ModalCorreosReporteriaUI.EMAIL, ModalCorreosReporteriaUI.CONFIRMAR_EMAIL,
                            ModalCorreosReporteriaUI.LINK_AGREGAR_CORREO, ModalCorreosReporteriaUI.BTN_AGREGAR_CORREOS, this.cantCorreos);
                } else {
                    AgregarCorreosErroneoReporte.con(actor, ModalCorreosReporteriaUI.EMAIL, ModalCorreosReporteriaUI.CONFIRMAR_EMAIL,
                            ModalCorreosReporteriaUI.LINK_AGREGAR_CORREO, ModalCorreosReporteriaUI.BTN_AGREGAR_CORREOS,
                            PaginaNegociacionUI.CORREOS_AGREGADOS, PaginaNegociacionUI.BTN_ELIMINAR_CORREO, ModalCorreosReporteriaUI.ERROR_AGREGAR_CORREO, this.escenarioError);
                }
            }
        } else {
            actor.attemptsTo(
                    WaitUntil.the(PaginaNegociacionUI.RECHAZA_REPORTE_TRX_DIARIO, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                    Click.on(PaginaNegociacionUI.RECHAZA_REPORTE_TRX_DIARIO));
        }

        if ("Si".equalsIgnoreCase(this.tieneNotificaciones) && this.tieneNotificaciones != null) {
            actor.attemptsTo(
                    WaitUntil.the(PaginaNegociacionUI.GESTOR_NOTIFICACIONES, WebElementStateMatchers.isClickable()).forNoMoreThan(20).seconds(),
                    Click.on(PaginaNegociacionUI.GESTOR_NOTIFICACIONES),
                    Enter.theValue("https://webhook.site/333f2523-2db7-4117-bfc6-227b950450ef")
                            .into(PaginaNegociacionUI.URL_NOTIFICACIONES)
            );
        }

        if (this.escenarioError == null) {
            actor.attemptsTo(
                    WaitUntil.the(PaginaNegociacionUI.BTN_CONTINUAR, WebElementStateMatchers.isVisible()).forNoMoreThan(80).seconds(),
                    Click.on(PaginaNegociacionUI.BTN_CONTINUAR));
            Ensure.that(PaginaNegociacionUI.CONFIRMAR_AVANZAR.waitingForNoMoreThan(Duration.ofSeconds(90)).resolveFor(actor).isClickable()).isTrue();
            actor.attemptsTo(WaitUntil.the(PaginaNegociacionUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isEnabled()).forNoMoreThan(80).seconds(),
                    Scroll.to(PaginaNegociacionUI.CONFIRMAR_AVANZAR),
                    Click.on(PaginaNegociacionUI.CONFIRMAR_AVANZAR)
            );
        }


    }

    public static RegistrarInfoNegociacion con(DataVinculacion data) {
        return Tasks.instrumented(RegistrarInfoNegociacion.class, data);
    }
}
