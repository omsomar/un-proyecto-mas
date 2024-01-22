package co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.DataListar;
import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;


public class DataRequest {

    private DataRequest() {

    }

    public static DataListar dataListar(FiltrosListar filtros) {
        DataListar data = new DataListar();

        if ("todosLosCampos".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setStartDate(filtros.getFechaInicio());
            data.setEndDate(filtros.getFechaFin());
            data.setCommerceAccountType(filtros.getTipoCuenta());
            data.setCommerceAccountNumber(filtros.getNumCuenta());
            data.setCommerceDocumentType(filtros.getTipoDoc());
            data.setCommerceDocumentNumber(filtros.getNumDoc());
        } else if ("camposObligatorios".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
        } else if ("fechas".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setStartDate(filtros.getFechaInicio());
            data.setEndDate(filtros.getFechaFin());
        } else if ("fechaInicial".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setStartDate(filtros.getFechaInicio());
        } else if ("fechasYtipoDoc".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setStartDate(filtros.getFechaInicio());
            data.setEndDate(filtros.getFechaFin());
            data.setCommerceDocumentType(filtros.getTipoDoc());
            data.setCommerceDocumentNumber(filtros.getNumDoc());
        } else if ("fechasYtipoCuenta".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setStartDate(filtros.getFechaInicio());
            data.setEndDate(filtros.getFechaFin());
            data.setCommerceAccountType(filtros.getTipoCuenta());
            data.setCommerceAccountNumber(filtros.getNumCuenta());
        } else if ("tipoDoc".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setCommerceDocumentType(filtros.getTipoDoc());
        } else if ("tipoCuenta".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setCommerceAccountType(filtros.getTipoCuenta());
        } else if ("tipoNumDoc".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setCommerceDocumentType(filtros.getTipoDoc());
            data.setCommerceDocumentNumber(filtros.getNumDoc());
        } else if ("tipoNumCuenta".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
            data.setCommerceAccountType(filtros.getTipoCuenta());
            data.setCommerceAccountNumber(filtros.getNumCuenta());
        } else if ("paginacion".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey(filtros.getPaginationKey());
            data.setPaginationSize(filtros.getPaginationSize());
            data.setStartDate(filtros.getFechaInicio());
            data.setEndDate(filtros.getFechaFin());
        }
        return data;
    }
}
