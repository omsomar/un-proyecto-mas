package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ModalCorreosReporteriaUI {

    public static final Target EMAIL = Target.the("Campo para ingresar el correo destinatario que va a recibir el reporte trx diario descargable")
            .located(By.id("email"));

    public static final Target CONFIRMAR_EMAIL = Target.the("Campo para confirmar el correo destinatario que va a recibir el reporte trx diario descargable")
            .located(By.id("emailConfirmation"));

    public static final Target LINK_AGREGAR_CORREO = Target.the("Enlace para agregar el correo ingresado como destinatario para recibir el reporte trx diario descargable")
            .located(By.xpath("//span[contains(.,'+ Añadir otra dirección')]"));

    public static final Target BTN_AGREGAR_CORREOS = Target.the("Botón para agregar los correos ingresados como destinatarios para recibir el reporte trx diario descargable")
            .located(By.xpath("//button[contains(.,'Añadir')]"));

    public static final Target ERROR_AGREGAR_CORREO = Target.the("Campo donde se visualiza el error al completar la información del formulario")
            .located(By.cssSelector("div.error-message"));

    private ModalCorreosReporteriaUI() {

    }
}
