package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginUI {

    public static final Target CORREO = Target.the("correo login")
            .located(By.name("loginfmt"));
    public static final Target CLAVE = Target.the("clave login")
            .located(By.name("passwd"));

    private LoginUI() {

    }

}
