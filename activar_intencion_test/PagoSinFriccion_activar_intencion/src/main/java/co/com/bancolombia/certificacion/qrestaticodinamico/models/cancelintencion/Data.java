package co.com.bancolombia.certificacion.qrestaticodinamico.models.cancelintencion;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Data {
    private String qrIntentionId;
    private String reason;

    public void setQrIntentionId(String qrIntentionId) {
        this.qrIntentionId = qrIntentionId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getQrIntentionId() {
        return qrIntentionId;
    }

    public String getReason() {
        return reason;
    }
}
