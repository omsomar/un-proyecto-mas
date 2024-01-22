package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaPcipalUI {

    public static final Target ICONO_DESLOGUEAR = Target.the("boton para cerrar sesión")
            .located(By.cssSelector("div.main >div[id='logo']"));

    public static final Target PLAN_BASICO = Target.the("opción para seleccionar plan básico")
            .located(By.xpath("//h2[contains(.,'Básico')]"));

    public static final Target PLAN_AVANZADO = Target.the("opción para seleccionar plan avanzado")
            .located(By.xpath("//h2[contains(.,'Avanzado')]"));

    private PaginaPcipalUI() {

    }

}
