package co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion;

public class DataListar {

    private String paginationKey;
    private String paginationSize;
    private String commerceAccountType;
    private String commerceAccountNumber;
    private String commerceDocumentType;
    private String commerceDocumentNumber;
    private String startDate;
    private String endDate;

    public String getPaginationKey() {
        return paginationKey;
    }

    public void setPaginationKey(String paginationKey) {
        this.paginationKey = paginationKey;
    }

    public String getPaginationSize() {
        return paginationSize;
    }

    public void setPaginationSize(String paginationSize) {
        this.paginationSize = paginationSize;
    }

    public String getCommerceAccountType() {
        return commerceAccountType;
    }

    public void setCommerceAccountType(String commerceAccountType) {
        this.commerceAccountType = commerceAccountType;
    }

    public String getCommerceAccountNumber() {
        return commerceAccountNumber;
    }

    public void setCommerceAccountNumber(String commerceAccountNumber) {
        this.commerceAccountNumber = commerceAccountNumber;
    }

    public String getCommerceDocumentType() {
        return commerceDocumentType;
    }

    public void setCommerceDocumentType(String commerceDocumentType) {
        this.commerceDocumentType = commerceDocumentType;
    }

    public String getCommerceDocumentNumber() {
        return commerceDocumentNumber;
    }

    public void setCommerceDocumentNumber(String commerceDocumentNumber) {
        this.commerceDocumentNumber = commerceDocumentNumber;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
