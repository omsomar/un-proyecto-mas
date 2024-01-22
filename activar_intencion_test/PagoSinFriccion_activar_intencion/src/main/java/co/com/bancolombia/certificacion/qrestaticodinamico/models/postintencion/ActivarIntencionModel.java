package co.com.bancolombia.certificacion.qrestaticodinamico.models.postintencion;

import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades.GeneradorRandom;

public class ActivarIntencionModel {

    private String qrId;
    private String reference;
    private String amount;
    private String commerceInvoiceId;
    private String customerLoyaltyId;

    public void todoLosCampos(String valorIntencion) {
        this.qrId = GeneradorRandom.getUUID();
        this.reference = Constantes.REFERENCIA_INTENCION;
        this.amount = valorIntencion;
        this.commerceInvoiceId = GeneradorRandom.randomTrackingNumberGenerator();
        this.customerLoyaltyId = "Ptoscolombia -121233";
    }

    public void todosLosCamposDecimales(String valorIntencion) {
        this.qrId = GeneradorRandom.getUUID();
        this.reference = Constantes.REFERENCIA_INTENCION;
        this.amount = valorIntencion;
        this.commerceInvoiceId = GeneradorRandom.randomTrackingNumberGenerator();
        this.customerLoyaltyId = "23754f06j Lorem Ipsu";
    }

    public void camposObligatorios(String valorIntencion) {
        this.qrId = GeneradorRandom.getUUID();
        this.amount = valorIntencion;
    }

    public void camposIncompletos(String parametro) {
        if (parametro.equalsIgnoreCase(Constantes.WITHOUT_QR_ID)) {
            this.qrId = "";
            this.amount = "200000";
        } else if (parametro.equalsIgnoreCase(Constantes.WITHOUT_AMOUNT)) {
            this.qrId = "8e43d809-53fd-4495-b44d-91df3963dc11";
            this.amount = "";
        }
    }

    public void camposIncorrectos(String campoIncorrecto) {
        if ("amountDecimales".equals(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "13400.567";
            this.commerceInvoiceId = "aqui el commerceid";
            this.customerLoyaltyId = "0001";
        } else if ("amount".equals(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "valor";
            this.commerceInvoiceId = "num fidelidad 123";
            this.customerLoyaltyId = "0001";
        } else if ("qrId".equals(campoIncorrecto)) {
            this.qrId = "*l-.j4545df21-5eb07-b781-37abb3b";
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "13444";
            this.commerceInvoiceId = "al9087-6";
            this.customerLoyaltyId = "0001";
        } else if ("idVentaComercio".equals(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "16700";
            this.commerceInvoiceId = "_#%aqui el commerce'id";
            this.customerLoyaltyId = "0001";
        } else if ("idVentaComercioCE".equals(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "15700";
            this.commerceInvoiceId = "&aqui el <commerceid/>";
            this.customerLoyaltyId = "0001";
        } else if ("numFidelidad".equals(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "12700.78";
            this.commerceInvoiceId = "aqui el commerceid";
            this.customerLoyaltyId = "_&%puntos0001";
        }
    }

    public void longitudMaxima(String campoIncorrecto) {
        if ("qrId".equalsIgnoreCase(campoIncorrecto)) {
            this.qrId = "117c4e9d-f4e1-406b-bc76-ea5272fee6ead345-6868fg-78t";
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "1200";
            this.commerceInvoiceId = "commerceid";
            this.customerLoyaltyId = "0001";
        } else if ("numFidelidad".equalsIgnoreCase(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "1200";
            this.commerceInvoiceId = "1234556";
            this.customerLoyaltyId = "Lorem Ipsum is simpls";
        } else if ("amount".equalsIgnoreCase(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "12000000007.23";
            this.commerceInvoiceId = "pv5266";
            this.customerLoyaltyId = "0001";
        } else if ("referencia".equalsIgnoreCase(campoIncorrecto)) {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type an";
            this.amount = "1200.98";
            this.commerceInvoiceId = "pv5287";
            this.customerLoyaltyId = "0001";
        } else {
            this.qrId = Constantes.ID_INTENCION_CORRECTA;
            this.reference = Constantes.REFERENCIA_INTENCION;
            this.amount = "160";
            this.commerceInvoiceId = "Lorem Ipsum is simply dummy text of the Lorem Ipsum";
            this.customerLoyaltyId = "0001";
        }

    }

    public void camposVacios() {
        this.qrId = "";
        this.reference = "";
        this.amount = "";
        this.commerceInvoiceId = "";
        this.customerLoyaltyId = "";
    }

    public void intencionesConsecutivas(String valorIntencion) {
        this.qrId = "da8c9058-a09f-457d-8091-6181bfbc73ee";
        this.amount = valorIntencion;
    }

    public String getQrId() {
        return qrId;
    }

    public String getReference() {
        return reference;
    }

    public String getAmount() {
        return amount;
    }

    public String getCommerceInvoiceId() {
        return commerceInvoiceId;
    }

    public String getCustomerLoyaltyId() {
        return customerLoyaltyId;
    }
}
