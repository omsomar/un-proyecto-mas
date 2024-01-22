package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.ValidarEstadoVinculacionUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.time.Duration;


public class ValidarInfoVinculacion implements Task {

    private String estadoVinculacion;

    public ValidarInfoVinculacion(String estado) {
        this.estadoVinculacion = estado;
    }

    public static ValidarInfoVinculacion con(String estadoVinculacion) {
        return Tasks.instrumented(ValidarInfoVinculacion.class, estadoVinculacion);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        switch (this.estadoVinculacion) {
            case "exitosa":
                actor.attemptsTo(
                        Ensure.that(ValidarEstadoVinculacionUI.TITULO_RTA_EXITOSA.waitingForNoMoreThan(Duration.ofSeconds(80))).text().isEqualTo("La validación de la información ha sido exitosa"),
                        Ensure.that(ValidarEstadoVinculacionUI.DESCRIPCION_RTA_EXITOSA.waitingForNoMoreThan(Duration.ofSeconds(50))).text().isEqualTo("El servicio estará asociado a la(s) cuenta(s):")
                );
                break;
            case "exitosaConRevision":
                actor.attemptsTo(
                        Ensure.that(ValidarEstadoVinculacionUI.TITULO_RTA_EXITOSA.waitingForNoMoreThan(Duration.ofSeconds(80))).text().isEqualTo("La validación de la información ha sido exitosa"),
                        Ensure.that(ValidarEstadoVinculacionUI.DESCRIPCION_RTA_EXITOSA.waitingForNoMoreThan(Duration.ofSeconds(50))).text().isEqualTo("El servicio estará asociado a la(s) cuenta(s):"),
                        Ensure.that(ValidarEstadoVinculacionUI.DESCRIPCION_RTA_REVISION.waitingForNoMoreThan(Duration.ofSeconds(50))).text().contains("Revisar la(s) siguiente(s) cuentas que incumplen los requisitos")
                );
                break;
            case "pendiente":
                actor.attemptsTo(
                        Ensure.that(ValidarEstadoVinculacionUI.TITULO_RTA_PENDIENTE.waitingForNoMoreThan(Duration.ofSeconds(80))).text().containsIgnoringCase("solicitud pendiente"),
                        Ensure.that(ValidarEstadoVinculacionUI.DESCRIPCION_RTA_PENDIENTE.waitingForNoMoreThan(Duration.ofSeconds(80))).text().containsIgnoringCase("Continuar si gestionó el/los casos(s) mencionados, de lo contrario, elija Nueva solicitud")
                );
                break;

            default:
                break;
        }
        actor.attemptsTo(
                WaitUntil.the(ValidarEstadoVinculacionUI.BTN_CONTINUAR, WebElementStateMatchers.isVisible()).forNoMoreThan(80).seconds(),
                Click.on(ValidarEstadoVinculacionUI.BTN_CONTINUAR));

    }
}
