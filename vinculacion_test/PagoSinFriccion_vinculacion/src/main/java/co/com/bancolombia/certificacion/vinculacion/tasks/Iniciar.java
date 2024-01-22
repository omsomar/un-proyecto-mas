package co.com.bancolombia.certificacion.vinculacion.tasks;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Open;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Iniciar implements Task {

    private String navegador;

    public Iniciar(DataVinculacion data) {
        this.navegador = data.getNavegador();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        if ("chrome".equalsIgnoreCase(navegador)) {
            System.setProperty("webdriver.chrome.driver", Constantes.RUTA_WEBDRIVER + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito", "--ignore-certificate-errors", "--start-maximized");
            ChromeDriver chrome = new ChromeDriver(options);
            actor.can(BrowseTheWeb.with(chrome));
            actor.wasAbleTo(Open.url((Constantes.URL)))
            ;
        }

    }


    public static Iniciar actividad(DataVinculacion data) {
        return Tasks.instrumented(Iniciar.class, data);

    }
}
