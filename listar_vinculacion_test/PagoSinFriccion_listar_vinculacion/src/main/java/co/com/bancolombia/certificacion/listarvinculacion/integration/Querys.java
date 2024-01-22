package co.com.bancolombia.certificacion.listarvinculacion.integration;

public class Querys {
    private Querys() {
    }

    public static String consultarInfo(String id){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_vinculation, commission_type, commission_value_fixed, commission_value_percentage,");
        stringBuilder.append(" exoneration_commission, custom_stop, exoneration_commission_start_date,");
        stringBuilder.append(" exoneration_commission_end_date, channel_qr,");
        stringBuilder.append(" exoneration_commission_type,exoneration_commission_value_fixed,");
        stringBuilder.append(" exoneration_commission_value_percentage FROM schvasqr.vinculation");
        stringBuilder.append(" WHERE id_vinculation = '").append(id).append("'");
        return stringBuilder.toString();
    }

    public static String consultarCamposCreator(String id){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT creator_visit_date, creator_adviser_id");
        stringBuilder.append(" FROM schvasqr.vinculation");
        stringBuilder.append(" WHERE id_vinculation = '").append(id).append("'");
        return stringBuilder.toString();
    }
}
