package co.com.bancolombia.certificacion.listarvinculacion.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abiities.CallAnApi.as;

public class Options extends RestInteraction {

    private final String resource;

    public Options(String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a PUT on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().patch(as(actor).resolve(resource)).then().log().all();
    }

    public static Options to(String resource) {
        return instrumented(Options.class, resource);
    }

}
