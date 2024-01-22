package co.com.bancolombia.certificacion.vinculacion.tasks.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaResponsableTecnicoUI;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.waits.WaitUntil;

public class RegistrarInfoRespTecnico implements Task {

    private String modelo;

    public RegistrarInfoRespTecnico(DataVinculacion data) {
        this.modelo = data.getModelo();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(PaginaResponsableTecnicoUI.NOMBRE_RESPONSABLE, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                Enter.theValue(Constantes.NOMBRE_RESPONSABLE).into(PaginaResponsableTecnicoUI.NOMBRE_RESPONSABLE),
                Enter.theValue(Constantes.CORREO_RESPONSABLE).into(PaginaResponsableTecnicoUI.CORREO_RESPONSABLE),
                Enter.theValue(Constantes.CELULAR_RESPONSABLE).into(PaginaResponsableTecnicoUI.CELULAR_RESPONSABLE)
        );
        if ("QR Mixto".equalsIgnoreCase(this.modelo) ||
            "QR Integrado".equalsIgnoreCase(this.modelo)) {
            actor.attemptsTo(Enter.theValue(Constantes.APP).into(PaginaResponsableTecnicoUI.APP_QR));
            actor.remember("app", Constantes.APP);
        }

        actor.remember("nombreRespTecnico", Constantes.NOMBRE_RESPONSABLE);
        actor.remember("correoRespTecnico", Constantes.CORREO_RESPONSABLE);
        actor.remember("celularRespTecnico", Constantes.CELULAR_RESPONSABLE);

        actor.attemptsTo(
                WaitUntil.the(PaginaResponsableTecnicoUI.BTN_CONTINUAR, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                Click.on(PaginaResponsableTecnicoUI.BTN_CONTINUAR),
                WaitUntil.the(PaginaResponsableTecnicoUI.CONFIRMAR_AVANZAR, WebElementStateMatchers.isVisible()).forNoMoreThan(30).seconds(),
                Click.on(PaginaResponsableTecnicoUI.CONFIRMAR_AVANZAR)
        );
    }

    public static RegistrarInfoRespTecnico con(DataVinculacion data) {
        return Tasks.instrumented(RegistrarInfoRespTecnico.class, data);
    }
}
