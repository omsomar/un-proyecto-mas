package co.com.bancolombia.certificacion.consultarvinculacion.tasks.listarvinculacion;

import co.com.bancolombia.certificacion.consultarvinculacion.interactions.Get;
import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.DataServicios;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

public class ListarVinculacionGet extends DataServicios implements Task {

    private String contenidoRequest;
    private String numeroPagina;
    private String tamanoPagina;
    private String tipoDocComercio;
    private String numDocComercio;
    private String tipoDocRepr;
    private String numDocRepr;
    private String nombreComercio;
    private String tipoCuenta;
    private String numCuenta;
    private String sterlingPath;
    private String fechaInicio;
    private String fechaFin;

    public ListarVinculacionGet(DataServicios datos) {

        this.contenidoRequest = datos.getContenidoRequest();
        this.numeroPagina = datos.getNumeroPagina();
        this.tamanoPagina = datos.getTamanoPagina();
        this.tipoDocComercio = datos.getTipoDocComercio();
        this.numDocComercio = datos.getNumDocComercio();
        this.tipoDocRepr = datos.getTipoDocRepr();
        this.numDocRepr = datos.getNumDocRepr();
        this.nombreComercio = datos.getNombreComercio();
        this.tipoCuenta = datos.getTipoCuenta();
        this.numCuenta = datos.getNumCuenta();
        this.sterlingPath = datos.getSterlingPath();
        this.fechaInicio = datos.getFechaInicio();
        this.fechaFin = datos.getFechaFin();

    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        if (Constantes.TODOS_LOS_CAMPOS.equals(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, this.numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, this.tamanoPagina)
                                    .queryParam("commerceDocumentType", this.tipoDocComercio)
                                    .queryParam("commerceDocumentNumber", this.numDocComercio)
                                    .queryParam("legalReprDocumentType", this.tipoDocRepr)
                                    .queryParam("legalReprDocumentNumber", this.numDocRepr)
                                    .queryParam("commerceName", this.nombreComercio)
                                    .queryParam("typeAccount", this.tipoCuenta)
                                    .queryParam("numberAccount", this.numCuenta)
                                    .queryParam("sterlingPath", this.sterlingPath)
                                    .queryParam("startDate", this.fechaInicio)
                                    .queryParam("endDate", this.fechaFin)));
        } else if ("fechas".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)
                                    .queryParam("startDate", this.fechaInicio)
                                    .queryParam("endDate", this.fechaFin)
                    ));
        } else if (Constantes.CAMPOS_OBLIGATORIOS.equals(contenidoRequest) || Constantes.CAMPOS_INCOMPLETOS.equals(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)));
        } else if ("fechaInicio".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)
                                    .queryParam("startDate", this.fechaInicio)
                    ));
        } else if ("documentoComercio".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)
                                    .queryParam("commerceDocumentType", this.tipoDocComercio)
                                    .queryParam("commerceDocumentNumber", this.numDocComercio)

                    ));
        } else if ("comercio".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)
                                    .queryParam("commerceName", this.nombreComercio)

                    ));
        } else if ("numCuenta".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)
                                    .queryParam("typeAccount", this.tipoCuenta)
                                    .queryParam("numberAccount", this.numCuenta)
                    ));
        } else if ("representanteLegal".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, this.numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, this.tamanoPagina)
                                    .queryParam("legalReprDocumentType", this.tipoDocRepr)
                                    .queryParam("legalReprDocumentNumber", this.numDocRepr)
                    ));
        } else if ("sterlingPath".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, tamanoPagina)
                                    .queryParam("sterlingPath", this.sterlingPath)
                    ));
        } else if ("algunosCamposVinculacion".equalsIgnoreCase(contenidoRequest)) {
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, this.numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, this.tamanoPagina)
                                    .queryParam("commerceDocumentType", this.tipoDocComercio)
                                    .queryParam("commerceDocumentNumber", this.numDocComercio)
                                    .queryParam("legalReprDocumentType", this.tipoDocRepr)
                                    .queryParam("legalReprDocumentNumber", this.numDocRepr)
                                    .queryParam("commerceName", this.nombreComercio)
                                    .queryParam("typeAccount", this.tipoCuenta)
                                    .queryParam("numberAccount", this.numCuenta)
                    ));
        } else if("algunosCamposSuscripcion".equalsIgnoreCase(contenidoRequest)){
            actor.attemptsTo(
                    Get.to(Constantes.RECURSO_LISTAR).with(
                            requestSpecification -> requestSpecification
                                    .relaxedHTTPSValidation()
                                    .queryParam(Constantes.PAGINATION_KEY, this.numeroPagina)
                                    .queryParam(Constantes.PAGINATION_SIZE, this.tamanoPagina)
                                    .queryParam("commerceDocumentType", this.tipoDocComercio)
                                    .queryParam("commerceDocumentNumber", this.numDocComercio)
                                    .queryParam("commerceName", this.nombreComercio)
                                    .queryParam("typeAccount", this.tipoCuenta)
                                    .queryParam("numberAccount", this.numCuenta)
                                    .queryParam("startDate", this.fechaInicio)
                                    .queryParam("endDate", this.fechaFin)
                    ));
        }
        actor.remember("paginationKey", this.numeroPagina);
        actor.remember("paginationSize", this.tamanoPagina);
        actor.remember("commerceDocumentType", this.tipoDocComercio);
        actor.remember("commerceDocumentNumber", this.numDocComercio);
        actor.remember("legalReprDocumentType", this.tipoDocRepr);
        actor.remember("legalReprDocumentNumber", this.numDocRepr);
        actor.remember("commerceName", this.nombreComercio);
        actor.remember("typeAccount", this.tipoCuenta);
        actor.remember("numberAccount", this.numCuenta);
        actor.remember("sterlingPath", this.sterlingPath);
        actor.remember("startDate", this.fechaInicio);
        actor.remember("endDate", this.fechaFin);
    }
}
