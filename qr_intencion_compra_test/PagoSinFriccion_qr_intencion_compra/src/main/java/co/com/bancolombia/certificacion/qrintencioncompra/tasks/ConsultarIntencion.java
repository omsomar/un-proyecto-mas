package co.com.bancolombia.certificacion.qrintencioncompra.tasks;

import co.com.bancolombia.certificacion.qrintencioncompra.interactions.Get;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.HashMap;
import java.util.Map;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.SERVICIO_CONSULTAR;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ConsultarIntencion implements Task {
    private final String idIntencion;

    public ConsultarIntencion(String idIntencion) {
        this.idIntencion = idIntencion;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        Map<String, String> header = new HashMap<>();
        header.put("x-ibm-client-secret", System.getProperty("client-secret"));
        header.put("X-IBM-Client-Id", System.getProperty("client-id"));
        header.put("message-id", System.getProperty("message-id"));
        actor.attemptsTo(
                Get.resource(SERVICIO_CONSULTAR + idIntencion).with(
                        requestSpecification -> requestSpecification
                                .relaxedHTTPSValidation()
                                .headers(header)
                ));
    }

    public static ConsultarIntencion deCompra(String dataFeature) {
        return instrumented(ConsultarIntencion.class, dataFeature);
    }

}
