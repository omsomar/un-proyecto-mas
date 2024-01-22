package co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.DataListar;
import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;


public class DataRequestFallida {

    private DataRequestFallida() {

    }

    public static DataListar dataListar(FiltrosListar filtros) {
        DataListar data = new DataListar();

        if ("camposIncompletos".equalsIgnoreCase(filtros.getEscenario())) {

            if ("paginationKey".equalsIgnoreCase(filtros.getCampoIncorrecto())) {
                data.setPaginationSize(filtros.getPaginationSize());
            } else if ("paginationSize".equalsIgnoreCase(filtros.getCampoIncorrecto())) {
                data.setPaginationKey("0");
            }
        } else if ("camposIncorrectos".equalsIgnoreCase(filtros.getEscenario())) {
            switch (filtros.getCampoIncorrecto()) {
                case "fechaInicioMayorFechaFin":
                case "fechanSinResultados":
                    data.setPaginationKey("0");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setStartDate(filtros.getFechaInicio());
                    data.setEndDate(filtros.getFechaFin());
                    break;
                case "tipoCuentaInvalido":
                    data.setPaginationKey("0");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setCommerceAccountType(filtros.getTipoCuenta());
                    break;
                case "tipoDocInvalido":
                    data.setPaginationKey("0");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setCommerceDocumentType(filtros.getTipoDoc());
                    break;
                case "tipoDocSinResultados":
                    data.setPaginationKey("0");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setCommerceDocumentType(filtros.getTipoDoc());
                    data.setStartDate(filtros.getFechaInicio());
                    data.setEndDate(filtros.getFechaFin());
                    break;
                case "formatoFechaInvalido":
                case "paginationSize":
                    data.setPaginationKey("0");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setCommerceDocumentType(filtros.getTipoDoc());
                    data.setCommerceDocumentNumber(filtros.getNumDoc());
                    data.setStartDate(filtros.getFechaInicio());
                    data.setEndDate(filtros.getFechaFin());
                    break;
                case "paginationKey":
                    data.setPaginationKey("ssd");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setCommerceDocumentType(filtros.getTipoDoc());
                    data.setCommerceDocumentNumber(filtros.getNumDoc());
                    data.setStartDate(filtros.getFechaInicio());
                    data.setEndDate(filtros.getFechaFin());
                    break;
                case "paginationKeyInexistente":
                    data.setPaginationKey("2000");
                    data.setPaginationSize(filtros.getPaginationSize());
                    data.setCommerceDocumentType(filtros.getTipoDoc());
                    data.setCommerceDocumentNumber(filtros.getNumDoc());
                    data.setStartDate(filtros.getFechaInicio());
                    data.setEndDate(filtros.getFechaFin());
                    break;
                default:
                    break;

            }
        } else if ("camposVacios".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("");
            data.setPaginationSize("");
        } else if ("longitudMaxima".equalsIgnoreCase(filtros.getEscenario())) {
            if ("numCuenta".equalsIgnoreCase(filtros.getCampoIncorrecto())) {
                data.setPaginationKey("0");
                data.setPaginationSize(filtros.getPaginationSize());
                data.setCommerceAccountType(filtros.getTipoCuenta());
                data.setCommerceAccountNumber(filtros.getNumCuenta());
            } else if ("numDoc".equalsIgnoreCase(filtros.getCampoIncorrecto())) {
                data.setPaginationKey("0");
                data.setPaginationSize(filtros.getPaginationSize());
                data.setCommerceDocumentType(filtros.getTipoDoc());
                data.setCommerceDocumentNumber(filtros.getNumDoc());
            }
        } else if ("recursoErroneo".equalsIgnoreCase(filtros.getEscenario())) {
            data.setPaginationKey("0");
            data.setPaginationSize(filtros.getPaginationSize());
        }
        return data;
    }
}
