package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaResponsableTecnicoUI {

    public static final Target NOMBRE_RESPONSABLE = Target.the("Campo para ingresar el nombre del responsable técnico para la vinculación del comercio")
            .located(By.id("fullName"));

    public static final Target CORREO_RESPONSABLE = Target.the("Campo para ingresar el correo del responsable técnico para la vinculación del comercio")
            .located(By.id("email"));

    public static final Target CELULAR_RESPONSABLE = Target.the("Campo para ingresar el celular de contacto del responsable técnico para la vinculación del comercio")
            .located(By.id("cellphone"));

    public static final Target APP_QR = Target.the("Campo para ingresar la aplicación la vinculación del comercio")
            .located(By.id("app"));

    public static final Target BTN_CONTINUAR = Target.the("Botón para avanzar al formulario de negociación")
            .located(By.xpath("(//button[contains(.,'Continuar')])[1]"));

    public static final Target CONFIRMAR_AVANZAR = Target.the("Botón para confirmar datos")
            .located(By.xpath("(//button[contains(.,'Continuar')])[2]"));

    private PaginaResponsableTecnicoUI() {

    }
}
