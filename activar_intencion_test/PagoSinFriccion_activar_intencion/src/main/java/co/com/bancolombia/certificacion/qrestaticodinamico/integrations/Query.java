package co.com.bancolombia.certificacion.qrestaticodinamico.integrations;

public class Query {
    private Query() {
    }

    public static String consultarIntencion(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_intention, intention_status, amount, id_qr, reason FROM schvasqr.intention_pay ");
        stringBuilder.append("WHERE id_intention = '" + id + "'");
        return stringBuilder.toString();
    }

    public static String consultarEstado(String id) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_intention, id_qr, intention_status");
        stringBuilder.append(" FROM schvasqr.intention_pay ");
        stringBuilder.append("WHERE id_intention = '" + id + "'");
        return stringBuilder.toString();
    }
}
