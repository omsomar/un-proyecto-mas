package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class VinculacionExitosaUI {

    public static final Target TITULO = Target.the("Campo de texto que indica que la vinculación se realizó exitosamente")
            .located(By.xpath("//h1[contains(.,'Vinculación exitosa')]"));

    public static final Target DESCRIPCION = Target.the("Campo de descripción sobre la vinculación exitosa")
            .located(By.xpath("(//div/p)[1]"));

    public static final Target BTN_NUEVA_SOLICITUD = Target.the("Botón para iniciar un nuevo proceso de vinculación")
            .located(By.xpath("//button[contains (.,'solicitud')]"));

    private VinculacionExitosaUI() {
    }
}
