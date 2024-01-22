package co.com.bancolombia.certificacion.consultarvinculacion.tasks.consultarvinculacion;

import co.com.bancolombia.certificacion.consultarvinculacion.interactions.Get;
import co.com.bancolombia.certificacion.consultarvinculacion.models.consultarvinculacion.FiltrosVinculacion;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;


public class ConsumirMetodo implements Task {

    private String idVinculacion;
    private String escenario;

    public ConsumirMetodo(FiltrosVinculacion vinculacion) {
        this.idVinculacion = vinculacion.getIdVinculacion();
        this.escenario = vinculacion.getEscenario();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if ("recursoErroneo".equalsIgnoreCase(escenario)) {
            actor.attemptsTo(
                    Get.to("/get_vinculation").with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam("idVinculation", idVinculacion)
                    )
            );
        } else if ("sinId".equalsIgnoreCase(escenario)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam("", idVinculacion)
                    )
            );
        } else {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam("idVinculation", idVinculacion)
                    )
            );
        }
        actor.remember("idVinculacion", idVinculacion);
    }

    public static ConsumirMetodo pos(FiltrosVinculacion escenario) {
        return Tasks.instrumented(ConsumirMetodo.class, escenario);
    }
}
