package co.com.bancolombia.certificacion.eliminacionexoneraciones.integrations;

public class ConsultarVinculacion {

    private ConsultarVinculacion(){

    }

    public static String consultarVinculacionVencidas(String fecha) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_vinculation, exoneration, exoneration_start_date,exoneration_end_date, " +
                "exoneration_commission, " +
                "exoneration_commission_start_date, exoneration_commission_end_date");
        stringBuilder.append(" FROM schvasqr.vinculation");
        stringBuilder.append(" WHERE (exoneration is true AND exoneration_end_date  < '"+ fecha + "' ) ");
        stringBuilder.append(" OR (exoneration_commission is true AND exoneration_commission_end_date  < '"+ fecha + "' )");
        stringBuilder.append(" ORDER BY id_vinculation DESC ");
        return stringBuilder.toString();
    }

    public static String consultarVinculacionActualizada(String idVinculacion) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_vinculation, to_plan, exoneration, exoneration_start_date,exoneration_end_date, " +
                "exoneration_commission," +
                "exoneration_commission_start_date, exoneration_commission_end_date");
        stringBuilder.append(" FROM schvasqr.vinculation");
        stringBuilder.append(" WHERE id_vinculation = '"+ idVinculacion +"' ");
        return stringBuilder.toString();
    }

    public static String consultarVinculacionVigente(String fechaActual) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_vinculation, exoneration, exoneration_start_date,exoneration_end_date, " +
                "exoneration_commission, " +
                "exoneration_commission_start_date, exoneration_commission_end_date");
        stringBuilder.append(" FROM schvasqr.vinculation");
        stringBuilder.append(" WHERE (exoneration is true AND exoneration_end_date  >= '"+ fechaActual + "' ) ");
        stringBuilder.append(" OR (exoneration_commission is true AND exoneration_commission_end_date  >= '"+ fechaActual + "' )");
        stringBuilder.append(" ORDER BY id_vinculation DESC ");
        return stringBuilder.toString();
    }

}
