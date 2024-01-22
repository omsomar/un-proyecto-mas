package co.com.bancolombia.certificacion.consultarvinculacion.integrations;

import co.com.bancolombia.certificacion.consultarvinculacion.utils.Constantes;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.Utilidades;

import java.util.List;

public class Query {
    private Query() {
    }

    public static String consultarVinculacion(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM schvasqr.vinculation ");
        stringBuilder.append("WHERE id_vinculation = '" + id + "'");
        return stringBuilder.toString();
    }

    public static String consultarCuentasVinculacion(String vinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ca.id_account, ca.account_type, ca.account_number FROM schvasqr.commerce_account ca ");
        stringBuilder.append("WHERE ca.id_vinculation = '" + vinculacion + "' ");
        return stringBuilder.toString();
    }

    public static String consultarCuentasVinculXCuenta(String vinculacion, String tipoCuenta, String numCuenta) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT ca.id_account, ca.account_type, ca.account_number FROM schvasqr.commerce_account ca ");
        stringBuilder.append("WHERE ca.id_vinculation = '" + vinculacion + "' ");
        stringBuilder.append(" AND ca.account_type = '" + tipoCuenta + "' ");
        stringBuilder.append(" AND ca.account_number = '" + numCuenta + "' ");
        return stringBuilder.toString();
    }

    public static String consultarContactosVinculacion(String vinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT cn.id_contact, cn.contact_email FROM schvasqr.contact_notification cn ");
        stringBuilder.append("WHERE cn.id_vinculation = '" + vinculacion + "' ");
        stringBuilder.append("ORDER BY cn.created_at DESC, cn.contact_email DESC ");
        return stringBuilder.toString();
    }

    public static String consultarEmailsReporteria(String vinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM schvasqr.vinculation_email_report  ");
        stringBuilder.append(" WHERE id_vinculation = '" + vinculacion + "' ");
        stringBuilder.append("ORDER BY created_at ASC");
        return stringBuilder.toString();
    }

    public static String listarVinculaciones(String escenario, List<String> filtros) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT vin.id_vinculation, vin.created_at, vin.updated_at, vin.adviser_username, vin.file_transfer, vin.email_transfer, "+
                        "vin.creator_visit_date, vin.creator_adviser_id, vin.channel_name, vin.commerce_name, vin.commerce_activity_code, vin.commerce_activity_name, " +
                        "vin.commerce_activity_detail, vin.commerce_document_type, vin.commerce_document_number, vin.legal_repr_name, vin.legal_repr_document_type, vin.legal_repr_document_number, vin.information_validation, " +
                        "vin.technical_support_name, vin.technical_support_email, vin.technical_support_phone_number, vin.commission_type, vin.commission_value_fixed, vin.commission_value_percentage, vin.exoneration, vin.exoneration_start_date, vin.exoneration_end_date, " +
                        "vin.daily_transactional_report, vin.account_transactional_report, vin.direct_notification, vin.to_plan, vin.to_model, vin.to_type, vin.multicash, vin.consolidated_daily_account, vin.sterling_path, vin.channel_qr, vin.terms_and_conditions, " +
                        "vin.custom_stop, vin.exoneration_commission_type, vin.exoneration_commission_value_fixed, vin.exoneration_commission_value_percentage, vin.exoneration_commission_start_date, vin.exoneration_commission_end_date, vin.exoneration_commission, vin.vinculation_status, vin.api_name "+
                        " FROM schvasqr.vinculation vin ");
        stringBuilder.append("LEFT JOIN schvasqr.commerce_account ca ");
        stringBuilder.append("ON vin.id_vinculation = ca.id_vinculation ");
        stringBuilder.append("LEFT JOIN schvasqr.contact_notification cn ");
        stringBuilder.append("ON vin.id_vinculation = cn.id_vinculation ");
        stringBuilder.append("LEFT JOIN schvasqr.control_list cl ");
        stringBuilder.append("ON vin.id_vinculation = cl.id_vinculation ");
        stringBuilder.append("LEFT JOIN schvasqr.partner pr ");
        stringBuilder.append("ON vin.id_vinculation = pr.id_vinculation ");

        switch (escenario) {
            case "fechas":
                stringBuilder.append("WHERE " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(filtros.get(10)) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(filtros.get(11)) + " 23:59:59'::timestamp ");
                break;
            case "fechaInicio":
                stringBuilder.append("WHERE " + Constantes.FECHA_FILTRO_RANGO  + " BETWEEN '" + Utilidades.cambiarFormatoFecha(filtros.get(10)) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp ");
                break;
            case "representanteLegal":
                stringBuilder.append("WHERE vin.legal_repr_document_type = '" + filtros.get(4) + "' ");
                stringBuilder.append("AND  vin.legal_repr_document_number = '" + filtros.get(5) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp ");
                break;
            case "documentoComercio":
                stringBuilder.append("WHERE vin.commerce_document_type = '" + filtros.get(2) + "' ");
                stringBuilder.append("AND vin.commerce_document_number = '" + filtros.get(3) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp ");
                break;
            case "comercio":
                stringBuilder.append("WHERE vin.commerce_name = '" + filtros.get(6) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp ");
                break;
            case "numCuenta":
                stringBuilder.append("WHERE ca.account_type = '" + filtros.get(7) + "' ");
                stringBuilder.append("AND ca.account_number = '" + filtros.get(8) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp ");
                break;
            case "sterlingPath":
                stringBuilder.append("WHERE vin.sterling_path = '" + filtros.get(9) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp  ");
                break;
            case "algunosCamposVinculacion":
                stringBuilder.append("WHERE vin.commerce_document_type = '" + filtros.get(2) + "' ");
                stringBuilder.append("AND vin.commerce_document_number = '" + filtros.get(3) + "' ");
                stringBuilder.append("AND vin.legal_repr_document_type = '" + filtros.get(4) + "' ");
                stringBuilder.append("AND  vin.legal_repr_document_number = '" + filtros.get(5) + "' ");
                stringBuilder.append("AND vin.commerce_name = '" + filtros.get(6) + "' ");
                stringBuilder.append("AND ca.account_type = '" + filtros.get(7) + "' ");
                stringBuilder.append("AND ca.account_number = '" + filtros.get(8) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp ");
                break;
            case "algunosCamposSuscripcion":
                stringBuilder.append("WHERE vin.commerce_document_type = '" + filtros.get(2) + "' ");
                stringBuilder.append("AND vin.commerce_document_number = '" + filtros.get(3) + "' ");
                stringBuilder.append("AND vin.commerce_name = '" + filtros.get(6) + "' ");
                stringBuilder.append("AND ca.account_type = '" + filtros.get(7) + "' ");
                stringBuilder.append("AND ca.account_number = '" + filtros.get(8) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(filtros.get(10)) + " 00:00:00.000000'::timestamp  AND '" + Utilidades.cambiarFormatoFecha(filtros.get(11)) + " 23:59:59'::timestamp ");
                break;
            case "camposObligatorios":
                stringBuilder.append("WHERE " + Constantes.FECHA_FILTRO_RANGO  + " BETWEEN '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFechaAnnioAtras()) + " 00:00:00.000000'::timestamp AND '" + Utilidades.cambiarFormatoFecha(Utilidades.generarFecha()) + " 23:59:59'::timestamp  ");
                break;
            case "todosLosCampos":
                stringBuilder.append("WHERE vin.commerce_document_type = '" + filtros.get(2) + "' ");
                stringBuilder.append("AND vin.commerce_document_number = '" + filtros.get(3) + "' ");
                stringBuilder.append("AND vin.legal_repr_document_type = '" + filtros.get(4) + "' ");
                stringBuilder.append("AND  vin.legal_repr_document_number = '" + filtros.get(5) + "' ");
                stringBuilder.append("AND vin.commerce_name = '" + filtros.get(6) + "' ");
                stringBuilder.append("AND ca.account_type = '" + filtros.get(7) + "' ");
                stringBuilder.append("AND ca.account_number = '" + filtros.get(8) + "' ");
                stringBuilder.append("AND vin.sterling_path = '" + filtros.get(9) + "' ");
                stringBuilder.append("AND " + Constantes.FECHA_FILTRO_RANGO + " BETWEEN '" + Utilidades.cambiarFormatoFecha(filtros.get(10)) + " 00:00:00.000000'::timestamp  AND '" + Utilidades.cambiarFormatoFecha(filtros.get(11)) + " 23:59:59'::timestamp ");
                break;

            default:
                    break;
        }
        stringBuilder.append(" GROUP BY vin.id_vinculation ");
        stringBuilder.append(" ORDER BY vin.id_vinculation ASC");
        stringBuilder.append(" LIMIT " + filtros.get(1));
        System.out.println("se imprime consulta bd: "+ stringBuilder);
        return stringBuilder.toString();
    }
}
