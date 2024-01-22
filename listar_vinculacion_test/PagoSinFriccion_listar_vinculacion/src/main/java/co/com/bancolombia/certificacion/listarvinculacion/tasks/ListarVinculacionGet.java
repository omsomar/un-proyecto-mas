package co.com.bancolombia.certificacion.listarvinculacion.tasks;


import co.com.bancolombia.certificacion.listarvinculacion.interactions.Get;
import co.com.bancolombia.certificacion.listarvinculacion.models.DataServicios;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static co.com.bancolombia.certificacion.listarvinculacion.utils.Constantes.*;
import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ListarVinculacionGet extends DataServicios implements Task {

    public ListarVinculacionGet(DataServicios datos) {
        this.servicio = datos.getServicio();
        this.contenidoRequest = datos.getContenidoRequest();
        this.numeroPagina = datos.getNumeroPagina();
        this.tamanoPagina = datos.getTamanoPagina();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        if(TODOS_LOS_CAMPOS.equals(contenidoRequest)){
            actor.attemptsTo(
                    Get.resource(servicio).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .param("paginationKey", numeroPagina)
                                    .param("paginationSize", tamanoPagina)
                                    .param("commerceDocumentType", "CC")
                                    .param("commerceDocumentNumber", "1161971366")
                                    .param("commerceName", "Makrocomputadores")
                                    .param("legalReprDocumentType", "CC")
                                    .param("legalReprDocumentNumber", "1161971366")
                                    .param("startDate", "02/01/2021")
                                    .param("endDate", "01/12/2022")));
        } else if(CAMPOS_OBLIGATORIOS.equals(contenidoRequest) || CAMPOS_INCOMPLETOS.equals(contenidoRequest)){
            actor.attemptsTo(
                    Get.resource(servicio).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .param("paginationKey", numeroPagina)
                                    .param("paginationSize", tamanoPagina)));

        }
    }
    public static ListarVinculacionGet listarVinculacionGet(DataServicios datos) { return instrumented(ListarVinculacionGet.class, datos); }

}
