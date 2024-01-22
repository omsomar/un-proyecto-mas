package co.com.bancolombia.certificacion.listarvinculacion.models;

public class Data{

    protected String idVinculation;
    protected String commissionType;
    protected Double commissionValueFixed;
    protected Double commissionValuePercentage;
    protected Integer customStop;
    protected String exonerationCommissionStartDate;
    protected String exonerationCommissionEndDate;
    protected String exonerationCommissionType;
    protected Double exonerationCommissionValueFixed;
    protected Double exonerationCommissionValuePercentage;
    protected String sterlingPath;
    protected String channelQr;


    public void setIdVinculation(String idVinculation) {
        this.idVinculation = idVinculation;
    }

    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType;
    }

    public void setCommissionValueFixed(Double commissionValueFixed) {
        this.commissionValueFixed = commissionValueFixed;
    }

    public void setCommissionValuePercentage(Double commissionValuePercentage) {
        this.commissionValuePercentage = commissionValuePercentage;
    }

    public void setCustomStop(Integer customStop) {
        this.customStop = customStop;
    }

    public void setExonerationCommissionStartDate(String exonerationCommissionStartDate) {
        this.exonerationCommissionStartDate = exonerationCommissionStartDate;
    }

    public void setExonerationCommissionEndDate(String exonerationCommissionEndDate) {
        this.exonerationCommissionEndDate = exonerationCommissionEndDate;
    }

    public void setExonerationCommissionType(String exonerationCommissionType) {
        this.exonerationCommissionType = exonerationCommissionType;
    }

    public void setExonerationCommissionValueFixed(Double exonerationCommissionValueFixed) {
        this.exonerationCommissionValueFixed = exonerationCommissionValueFixed;
    }

    public void setExonerationCommissionValuePercentage(Double exonerationCommissionValuePercentage) {
        this.exonerationCommissionValuePercentage = exonerationCommissionValuePercentage;
    }

    public void setSterlingPath(String sterlingPath) {
        this.sterlingPath = sterlingPath;
    }

    public void setChannelQr(String channelQr) {
        this.channelQr = channelQr;
    }
}
