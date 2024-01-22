package co.com.bancolombia.certificacion.qrestaticodinamico.models.postintencion;

public class IntentionDetail {

    private String qrId;
    private String reference;
    private String amount;

    public String getQrId() {
        return qrId;
    }

    public void setQrId(String qrId) {
        this.qrId = qrId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}
