package co.com.bancolombia.certificacion.infologtransaccional.interactions;

import net.serenitybdd.core.time.InternalSystemClock;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class Wait implements Interaction {

    private final int time;

    protected Wait(int time) {
        this.time = time;
    }

    public static Wait per(int time) {
        return instrumented(Wait.class, time);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        new InternalSystemClock().pauseFor(time);
    }
}
