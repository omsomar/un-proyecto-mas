package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaNegociacionUI {

    public static final Target TIPO_COMISION = Target.the("Campo para seleccionar el tipo de comisión asignado para la vinculación del comercio")
            .located(By.id("commissionType"));

    public static final Target VALOR_PORCENTUAL = Target.the("Campo para ingresar el valor porcentual de la comisión para la vinculación del comercio")
            .located(By.id("percentageValue"));

    public static final Target VALOR_FIJO = Target.the("Campo para ingresar el valor fijo de la comisión para la vinculación del comercio")
            .located(By.id("monetaryValue"));

    public static final Target TIENE_EXONERACION = Target.the("Campo para marcar si aplica exoneración para la vinculación del comercio")
            .located(By.xpath("//label/span[contains(.,'Sí')]"));

    public static final Target TEXTO_COMPL_APLICA_EXONERACION = Target.the("Texto complementario de la etiqueta Aplica exoneración")
            .located(By.xpath("//span[contains(.,'Durante este periodo al cliente no se le hará cobro de comisión')]"));

    public static final Target FECHA_INICIO_EXONERACION = Target.the("Campo Calendario para escoger la fecha de inicio desde la cual aplica la exoneración")
            .located(By.xpath("//input[@id='datepicker-negotiation-i']"));

    public static final Target FECHA_FIN_EXONERACION = Target.the("Campo Calendario para escoger la fecha de fin hasta la cual aplica la exoneración")
            .located(By.xpath("//input[@id='datepicker-negotiation-f']"));

    public static final Target ACEPTA_SALDOS_CONSOLIDADO = Target.the("Campo para checkear si se requiere recibir el saldo consolidado en el extracto")
            .located(By.xpath("(//label/span[not(@class='black-text')])[1]"));

    public static final Target RECHAZA_SALDOS_CONSOLIDADO = Target.the("Campo para checkear si NO se requiere recibir el saldo consolidado en el extracto")
            .located(By.xpath("(//label/span[not(@class='black-text')])[2]"));

    public static final Target ACEPTA_REPORTE_TRX_DIARIO = Target.the("Campo para checkear si se requiere recibir el reporte transaccional diario")
            .located(By.xpath("(//label/span[not(@class='black-text')])[3]"));

    public static final Target RECHAZA_REPORTE_TRX_DIARIO = Target.the("Campo para checkear si NO se requiere recibir el reporte transaccional diario")
            .located(By.xpath("(//label/span[not(@class='black-text')])[4]"));

    /*Campos para definir estructuración del archivo de reporte transaccional diario*/
    public static final Target DOCUMENTO_TITULAR_CTA = Target.the("Campo para checkear si se requiere recibir el reporte transaccional diario estructurado por el número de documento del titular de la cuenta")
            .located(By.xpath("//label/span[contains(.,'Por documento del titular')]"));

    public static final Target DOCUMENTO_Y_CUENTA_TITULAR_CTA = Target.the("Campo para checkear si se requiere recibir el reporte transaccional diario estructurado por el número de documento del titular y número de cuenta")
            .located(By.xpath("//label/span[contains(.,'Por documento y cuenta')]"));

    /*Campos para seleccionar método de entrega del reporte transaccional diario SFTP o DESCARGABLE*/
    public static final Target OPCION_ENTREGA_DESCARGABLE = Target.the("Campo para checkear si se desea seleccionar la opción de entrega Descargable para el reporte trx diario")
            .located(By.xpath("//label/span[contains(.,'Descargable')]"));

    public static final Target OPCION_ENTREGA_SFTP = Target.the("Campo para checkear si se desea seleccionar la opción de entrega SFTP para el reporte trx diario")
            .located(By.xpath("//label/span[contains(.,'SFTP')]"));

    /*Campos para agregar correos destinatarios para la opción Descargable
     * En esta clase solo se incluye los siguientes elementos:
     * 1. el botón agregar correo que abre una modal
     * 2. texto donde se muestran los correos que fueron añadidos
     * 3. el botón para eliminar de forma individual los correos que fueron añadidos
     * En la clase llamada ModalCorreosReporteriaUI se encuentran los localizadores de la modal*/
    public static final Target BTN_AGREGAR_CORREOS = Target.the("Campo para abrir la modal que permite adicionar los correos destinatarios, solo aplica para opción Descargable")
            .located(By.xpath("//span[contains(.,'Agregar correos')]"));

    public static final Target CORREOS_AGREGADOS = Target.the("Campo donde se visualizan los correos que fueron añadidos como destinatarios")
            .located(By.cssSelector("div.card-content>div>div>div>p"));

    public static final Target BTN_ELIMINAR_CORREO = Target.the("Botón que permite eliminar el correo que fue añadido como destinatario para recibir el reporte transaccional diario")
            .located(By.xpath("(//img[@alt='confirmed-data'])[1]"));

    public static final Target GESTOR_NOTIFICACIONES = Target.the("Campo para checkear si se requiere el servicio de notificaciones")
            .located(By.xpath("//div/div/label/span"));

    public static final Target URL_NOTIFICACIONES = Target.the("Campo para ingresar la url para recibir las notificaciones")
            .located(By.id("urlToNotify"));

    public static final Target BTN_CONTINUAR = Target.the("Botón para avanzar al formulario de acuerdo de la vinculación")
            .located(By.xpath("(//button[contains(.,'Continuar')])[1]"));

    public static final Target CONFIRMAR_AVANZAR = Target.the("Botón para confirmar datos")
            .located(By.xpath("(//button[contains(.,'Continuar')])[2]"));

    private PaginaNegociacionUI() {

    }

}
