package co.com.bancolombia.certificacion.qrintencioncompra.tasks;

import co.com.bancolombia.certificacion.qrintencioncompra.interactions.Get;
import co.com.bancolombia.certificacion.qrintencioncompra.models.FiltrosIntencion;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes;
import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.SERVICIO_LISTAR;

public class ListarIntenPorPagina implements Task {
    private String idQr;
    private String paginationKey;
    private String idVentaComercio;

    public ListarIntenPorPagina(FiltrosIntencion filtros) {
        idQr = filtros.getIdQR();
        paginationKey = filtros.getPaginationKey();
        idVentaComercio = filtros.getIdVentaComercio();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<String> intencionesPrimerPagina = obtenerIntenciones();
        actor.remember("resultadosPrimerPagina", intencionesPrimerPagina);
        actor.attemptsTo(Get.resource(SERVICIO_LISTAR)
                .with(requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .contentType(ContentType.JSON)
                        .queryParam("paginationKey", paginationKey)
                        .queryParam("paginationSize", Constantes.PAGINATION_SIZE_PAGINACION)
                        .queryParam("qrId", idQr)
                        .queryParam("commerceInvoiceId", idVentaComercio)
                ));
        List<String> intencionesSegundaPagina = obtenerIntenciones();
        actor.remember("resultadosSegundaPagina", intencionesSegundaPagina);
    }

    public List<String> obtenerIntenciones() {
        List<String> listIntenciones = new ArrayList<>();
        List<Map<String, String>> intenciones = SerenityRest.lastResponse().getBody().jsonPath().getList("data");
        for (Map<String, String> iterator : intenciones) {
            listIntenciones.add(iterator.get("createdAt"));
        }
        return listIntenciones;
    }

    public static ListarIntenPorPagina con(FiltrosIntencion filtros) {
        return Tasks.instrumented(ListarIntenPorPagina.class, filtros);
    }
}
