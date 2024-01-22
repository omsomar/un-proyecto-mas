package co.com.bancolombia.certificacion.vinculacion.tasks;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaPcipalUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class SeleccionarPlan implements Task {

    private String plan;

    public SeleccionarPlan(String plan) {
        this.plan = plan;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if ("Plan Avanzado".equalsIgnoreCase(this.plan)) {
            actor.attemptsTo(
                    WaitUntil.the(PaginaPcipalUI.PLAN_AVANZADO, WebElementStateMatchers.isVisible()).forNoMoreThan(40).seconds(),
                    Click.on(PaginaPcipalUI.PLAN_AVANZADO),
                    WaitUntil.the(PaginaPcipalUI.PLAN_AVANZADO, WebElementStateMatchers.isNotVisible()).forNoMoreThan(40).seconds());
        }

    }

    public static SeleccionarPlan deVinculacion(String plan) {
        return Tasks.instrumented(SeleccionarPlan.class, plan);
    }
}
