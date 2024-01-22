package co.com.bancolombia.certificacion.vinculacion.integrations;

public class Query {
    private Query() {
    }

    public static String consultarVinculacion(String fechaVisita, String modelo) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_vinculation, creator_visit_date, creator_adviser_id, channel_name," +
                "commerce_name, commerce_activity_name, commerce_document_type, commerce_document_number, legal_repr_name," +
                "legal_repr_document_type, legal_repr_document_number, technical_support_name, technical_support_email, technical_support_phone_number," +
                "api_name, commission_type,commission_value_fixed, commission_value_percentage, exoneration, exoneration_start_date, exoneration_end_date," +
                "daily_transactional_report, account_transactional_report, consolidated_daily_account, direct_notification, url_notification," +
                "vinculation_status, terms_and_conditions, created_at, updated_at , to_model, to_plan, file_transfer, email_transfer, channel_qr FROM schvasqr.vinculation");
        stringBuilder.append(" WHERE creator_visit_date ='" + fechaVisita + "' ");
        stringBuilder.append(" AND to_plan ='Plan Avanzado'");
        if("QR Integrado".equalsIgnoreCase(modelo)){
            stringBuilder.append(" AND to_model ='Modelo QR Integrado'");
        }else if("QR Estático".equalsIgnoreCase(modelo)){
            stringBuilder.append(" AND to_model ='Modelo QR Estatico'");
        } else if("QR Mixto".equalsIgnoreCase(modelo)){
            stringBuilder.append(" AND to_model ='Modelo QR Mixto'");
        }
        stringBuilder.append(" ORDER BY created_at DESC LIMIT 1");
        return stringBuilder.toString();
    }

    public static String consultarEmailReporteriaVinculacion(String idVinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT* FROM schvasqr.vinculation_email_report");
        stringBuilder.append(" WHERE id_vinculation ='" + idVinculacion + "' ");
        stringBuilder.append(" ORDER BY created_at ASC");
        return stringBuilder.toString();
    }

    public static String consultarAccionistasVinculacion(String idVinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT* FROM schvasqr.partner");
        stringBuilder.append(" WHERE id_vinculation ='" + idVinculacion + "' ");
        stringBuilder.append(" ORDER BY created_at ASC");
        return stringBuilder.toString();
    }

    public static String consultarCuentasVinculacion(String idVinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT* FROM schvasqr.commerce_account");
        stringBuilder.append(" WHERE id_vinculation ='" + idVinculacion + "' ");
        stringBuilder.append(" ORDER BY created_at ASC");
        return stringBuilder.toString();
    }

    public static String consultarContactosNotificacion(String idVinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT* FROM schvasqr.contact_notification");
        stringBuilder.append(" WHERE id_vinculation ='" + idVinculacion + "' ");
        stringBuilder.append(" ORDER BY created_at ASC");
        return stringBuilder.toString();
    }
}
