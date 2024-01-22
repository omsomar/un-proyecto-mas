package co.com.bancolombia.certificacion.infologtransaccional.integrations;

import co.com.bancolombia.certificacion.infologtransaccional.utils.Utilidades;

public class Query {
    private Query() {
    }

    public static String
    consulNotificacionTodosLosCampos(String tipoDoc, String numDoc, String tipoCuenta, String numCuenta
            , String fechaInicio, String fechaFin, String escenario, String paginationSize) {
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT trx.transaction_date, trx.tracking_number, trx.qr_id, trx.info_trx, noti.response_code,");
        builder.append(" trx.destination_document_type, trx.destination_document_number, trx.destination_account_type, trx.destination_account_number");
        builder.append(" FROM schtrxqr.log_transaction trx ");
        builder.append("INNER JOIN schtrxqr.log_notification noti ");
        builder.append("ON noti.tracking_number = trx.tracking_number ");

        switch (escenario) {
            case "todosLosCampos":
                builder.append(" WHERE trx.destination_document_type = '").append(tipoDoc).append("'");
                builder.append(" AND trx.destination_document_number = '").append(numDoc).append("'");
                builder.append(" AND trx.destination_account_type = '").append(tipoCuenta).append("'");
                builder.append(" AND trx.destination_account_number = '").append(numCuenta).append("'");
                builder.append(" AND trx.transaction_date BETWEEN '").append(fechaInicio).append("'")
                        .append(" AND '").append(fechaFin).append("'");
                break;
            case "camposObligatorios":
                builder.append(" WHERE trx.transaction_date BETWEEN '").append(Utilidades.generarFechaAnnioAtras())
                        .append("'").append(" AND '").append(Utilidades.generarFecha()).append("'");
                break;
            case "fechas":
                builder.append(" WHERE trx.transaction_date BETWEEN '").append(fechaInicio).append("'")
                        .append(" AND '").append(fechaFin).append("'");
                break;
            case "fechaInicial":
                builder.append(" WHERE trx.transaction_date BETWEEN '").append(fechaInicio).append("'")
                        .append(" AND '").append(Utilidades.generarFecha()).append("'");

                break;
            case "fechasYtipoDoc":
                builder.append(" WHERE trx.transaction_date BETWEEN '").append(fechaInicio).append("'")
                        .append(" AND '").append(fechaFin).append("'");
                builder.append(" AND trx.destination_document_type = '").append(tipoDoc).append("'");
                builder.append(" AND trx.destination_document_number = '").append(numDoc).append("'");
                break;
            case "fechasYtipoCuenta":
                builder.append(" WHERE trx.transaction_date BETWEEN '").append(fechaInicio).append("'")
                        .append(" AND '").append(fechaFin).append("'");
                builder.append(" AND trx.destination_account_type = '").append(tipoCuenta).append("'");
                builder.append(" AND trx.destination_account_number = '").append(numCuenta).append("'");
                break;
            case "tipoDoc":
                builder.append(" WHERE trx.destination_document_type = '").append(tipoDoc).append("'");
                builder.append(" AND trx.transaction_date BETWEEN '").append(Utilidades.generarFechaAnnioAtras())
                        .append("'").append(" AND '").append(Utilidades.generarFecha()).append("'");
                break;
            case "tipoCuenta":
                builder.append(" WHERE trx.destination_account_type = '").append(tipoCuenta).append("'");
                builder.append(" AND trx.transaction_date BETWEEN '").append(Utilidades.generarFechaAnnioAtras())
                        .append("'").append(" AND '").append(Utilidades.generarFecha()).append("'");
                break;
            case "tipoNumDoc":
                builder.append(" WHERE trx.destination_document_type = '").append(tipoDoc).append("'");
                builder.append(" AND trx.destination_document_number = '").append(numDoc).append("'");
                builder.append(" AND trx.transaction_date BETWEEN '").append(Utilidades.generarFechaAnnioAtras())
                        .append("'").append(" AND '").append(Utilidades.generarFecha()).append("'");
                break;
            case "tipoNumCuenta":
                builder.append(" WHERE trx.destination_account_type = '").append(tipoCuenta).append("'");
                builder.append(" AND trx.destination_account_number = '").append(numCuenta).append("'");
                builder.append(" AND trx.transaction_date BETWEEN '").append(Utilidades.generarFechaAnnioAtras())
                        .append("'").append(" AND '").append(Utilidades.generarFecha()).append("'");
                break;
            default:
                break;

        }
        builder.append(" ORDER BY trx.transaction_date_time DESC, trx.id_log_trx_qr DESC");
        builder.append(" LIMIT ").append(paginationSize);
        return builder.toString();
    }

    public static String obtenerCamposGetInfoLog(String trackingNumber) {

        StringBuilder consulta = new StringBuilder();
        consulta.append("select * from schtrxqr.log_transaction ");
        consulta.append("where tracking_number = ");
        consulta.append("'").append(trackingNumber).append("'");
        return consulta.toString();
    }
}
