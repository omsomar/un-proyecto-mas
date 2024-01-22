package co.com.bancolombia.certificacion.qrestaticodinamico.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.interactions.RestInteraction;
import net.thucydides.core.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.rest.abiities.CallAnApi.as;

public class Patch extends RestInteraction {

    private final String resource;

    public Patch(String resource) {
        this.resource = resource;
    }

    @Step("{0} executes a PUT on the resource #resource")
    @Override
    public <T extends Actor> void performAs(T actor) {
        rest().log().all().put(as(actor).resolve(resource)).then().log().all();
    }

    public static Patch to(String resource) {
        return instrumented(Patch.class, resource);
    }

}
