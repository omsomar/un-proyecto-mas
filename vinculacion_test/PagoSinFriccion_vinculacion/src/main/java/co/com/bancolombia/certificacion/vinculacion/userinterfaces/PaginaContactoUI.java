package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaContactoUI {
    /**
     * Estos localizadores aplican cuando se escoge el plan de vinculación AVANZADO
     */

    public static final Target MODELO_QR_INTEGRADO = Target.the("opción para escoger el modelo de vinculación QR Integrado")
            .located(By.xpath("//span[contains(.,'Integrado')]"));

    public static final Target MODELO_QR_ESTATICO = Target.the("opción para escoger el modelo de vinculación QR Estatico")
            .located(By.xpath("//span[contains(.,'Estático')]"));

    public static final Target MODELO_QR_MIXTO = Target.the("opción para escoger el modelo de vinculación QR Mixto")
            .located(By.xpath("//span[contains(.,'Mixto')]"));

    public static final Target CALENDARIO = Target.the("campo con el calendario para escoger la fecha de realización de la vinculación")
            .located(By.xpath("//input[@id='datepicker-contact']"));

    public static final Target CODIGO_ASESOR = Target.the("campo para indicar el código del asesor")
            .located(By.id("adviserCode"));

    public static final Target CHECKS_CONTACTO = Target.the("Todos los checkbox de vinculación")
            .located(By.xpath("//label[.//span]"));

    public static final Target NOMBRE_COMPLETO = Target.the("Nombre completo de contacto")
            .located(By.id("fullName"));

    public static final Target CELULAR_CONTACTO = Target.the("Celular de contacto")
            .located(By.id("cellphone"));

    public static final Target EMAIL_CONTACTO = Target.the("Email de contacto")
            .located(By.id("email"));

    public static final Target AVANZAR_FORM_NEGOCIO = Target.the("Botón Continuar para avanzar al formulario de negocio")
            .located(By.xpath("(//button[contains(.,'Continuar')])[1]"));

    public static final Target CONFIRMAR_AVANZAR = Target.the("Botón para confirmar datos")
            .located(By.xpath("(//button[contains(.,'Continuar')])[2]"));

    /* Localizadores para agregar más personas como contacto*/
    public static final Target BTN_AGREGAR_OTRA_PERSONA = Target.the("Boton que permite adicionar otra persona de contacto")
            .located(By.xpath("//span[contains(.,'notificar')]"));

    public static final Target BTN_AÑADIR_MODAL = Target.the("Boton que permite confirmar el/los emails de contacto adicionales que se hayan ingresado en dicha modal")
            .located(By.xpath("//button[contains(.,'Añadir')]"));

    public static final Target ENLACE_AÑADIR_MODAL = Target.the("Enlace que permite adicionar otro email de contacto")
            .located(By.xpath("//span[contains(.,'Añadir')]"));

    public static final Target EMAIL_SECUNDARIO_1 = Target.the("Email de contacto adicional 1")
            .located(By.id("email0"));

    public static final Target EMAIL_SECUNDARIO_2 = Target.the("Email de contacto adicional 2")
            .located(By.id("email1"));

    public static final Target EMAIL_SECUNDARIO_3 = Target.the("Email de contacto adicional 3")
            .located(By.id("email2"));

    public static final Target EMAIL_SECUNDARIO_4 = Target.the("Email de contacto adicional 4")
            .located(By.id("email3"));

    public static final Target VISUALIZACION_FECHA_VISITA = Target.the("Campo donde se visualiza la fecha de visita seleccionada")
            .located(By.xpath("//app-summary-element[2]/div[2]/div/p"));

    private PaginaContactoUI() {

    }
}
