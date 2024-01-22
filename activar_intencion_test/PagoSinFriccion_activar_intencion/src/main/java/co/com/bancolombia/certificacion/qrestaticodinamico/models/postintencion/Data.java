package co.com.bancolombia.certificacion.qrestaticodinamico.models.postintencion;

public class Data {

    private IntentionDetail intentionDetail;
    private Invoice invoice;
    private Customer customer;

    public IntentionDetail getIntentionDetail() {
        return intentionDetail;
    }

    public void setIntentionDetail(IntentionDetail intentionDetail) {
        this.intentionDetail = intentionDetail;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
