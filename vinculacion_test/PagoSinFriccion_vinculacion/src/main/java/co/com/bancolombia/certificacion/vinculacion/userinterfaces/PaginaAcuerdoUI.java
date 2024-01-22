package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaAcuerdoUI {

    public static final Target TEXTO_TERMINOS = Target.the("Campo con el texto introductorio de la pantalla de acuerdo para aceptar los términos y condiciones")
            .located(By.xpath("(//div/p)[1]"));

    public static final Target CHECK_TYC = Target.the("Check para aceptar los términos y condiciones")
            .located(By.cssSelector("label > span"));

    public static final Target CARGAR_ARCHIVO = Target.the("Botón para cargar el archivo correspondiente a la copia del contrato")
            .located(By.xpath("//span[contains(.,'Adjuntar')]"));

    public static final Target ARCHIVO = Target.the("Campo para subir archivo").located(By.cssSelector("input.file"));

    public static final Target ARCHIVO_SUBIDO = Target.the("Campo para confirmar que el archivo cargado, subió exitosamente ")
            .located(By.xpath("(//div[contains(@class,'card-content')]/div/div[contains(.,'pdf')])[1]"));

    public static final Target AVANZAR = Target.the("Botón Continuar para avanzar a la pantalla final de la vinculación")
            .located(By.xpath("(//button[contains(.,'Continuar')])[1]"));

    public static final Target CONFIRMAR_AVANZAR = Target.the("Botón para confirmar datos")
            .located(By.xpath("(//button[contains(.,'Continuar')])[2]"));

    private PaginaAcuerdoUI() {

    }
}
