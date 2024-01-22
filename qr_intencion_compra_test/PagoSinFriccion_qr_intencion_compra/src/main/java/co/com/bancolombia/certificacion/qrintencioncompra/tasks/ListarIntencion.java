package co.com.bancolombia.certificacion.qrintencioncompra.tasks;

import co.com.bancolombia.certificacion.qrintencioncompra.interactions.Get;
import co.com.bancolombia.certificacion.qrintencioncompra.models.FiltrosIntencion;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

import java.util.HashMap;
import java.util.Map;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.PAGINATION_KEY;
import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.SERVICIO_LISTAR;

public class ListarIntencion implements Task {
    private FiltrosIntencion filtros;

    public ListarIntencion(FiltrosIntencion filtros) {
        this.filtros = filtros;
    }

    public ListarIntencion() {
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Get.resource(SERVICIO_LISTAR)
                .with(requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .contentType(ContentType.JSON)
                        .params(enviarParametros())
                ));
    }

    public Map<String, String> enviarParametros() {
        Map<String, String> parametros = new HashMap<>();
        parametros.put("paginationKey", PAGINATION_KEY);
        parametros.put("paginationSize", filtros.getPaginationSize());

        if (!"".equals((this.filtros.getIdQR()))) {
            parametros.put("qrId", this.filtros.getIdQR());
        }
        if (!"".equals(this.filtros.getTipoCuenta())) {
            parametros.put("typeAccount", this.filtros.getTipoCuenta());
        }
        if (!"".equals(this.filtros.getNumCuenta())) {
            parametros.put("numberAccount", this.filtros.getNumCuenta());
        }
        if (!"".equals(this.filtros.getTipoDoc())) {
            parametros.put("documentType", this.filtros.getTipoDoc());
        }
        if (!"".equals(this.filtros.getNumDoc())) {
            parametros.put("documentNumber", this.filtros.getNumDoc());
        }
        if (!"".equals(this.filtros.getIdVentaComercio())) {
            parametros.put("commerceInvoiceId", this.filtros.getIdVentaComercio());
        }
        return parametros;
    }

    public static ListarIntencion con(FiltrosIntencion filtros) {
        return Tasks.instrumented(ListarIntencion.class, filtros);
    }
}
