package co.com.bancolombia.certificacion.qrintencioncompra.models;

import org.jetbrains.annotations.NotNull;

public class DataList implements Comparable<DataList> {
    private String qrId;
    private String amount;
    private String intentionStatus;
    private String commerceInvoiceId;
    private String customerLoyaltyId;
    private String createdAt;


    public DataList(String qrId, String amount, String intentionStatus, String commerceInvoiceId, String customerLoyaltyId, String createdAt) {
        this.qrId = qrId;
        this.amount = amount;
        this.intentionStatus = intentionStatus;
        this.commerceInvoiceId = commerceInvoiceId;
        this.customerLoyaltyId = customerLoyaltyId;
        this.createdAt = createdAt;
    }

    public String getQrId() {
        return qrId;
    }

    public String getAmount() {
        return amount;
    }

    public String getIntentionStatus() {
        return intentionStatus;
    }

    public String getCommerceInvoiceId() {
        return commerceInvoiceId;
    }

    public String getCustomerLoyaltyId() {
        return customerLoyaltyId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public int compareTo(@NotNull DataList o) {
        return Double.compare(Double.parseDouble(this.amount), Double.parseDouble(o.getAmount()));
    }
}
