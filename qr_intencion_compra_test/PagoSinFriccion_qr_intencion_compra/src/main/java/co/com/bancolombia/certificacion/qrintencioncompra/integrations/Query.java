package co.com.bancolombia.certificacion.qrintencioncompra.integrations;

public class Query {
    private Query() {
    }

    public static String consultarIntencionPorQr(String qrId) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT id_intention, intention_status, amount, id_qr,commerce_invoice_id, customer_loyalty_id FROM schvasqr.intention_pay ");
        stringBuilder.append("WHERE id_qr = '" + qrId + "'" + " and intention_status = '" + "PENDIENTE" + "'" + " order by created_at DESC");
        return stringBuilder.toString();
    }

}
