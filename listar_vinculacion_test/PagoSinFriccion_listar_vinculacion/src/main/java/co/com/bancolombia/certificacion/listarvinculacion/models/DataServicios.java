package co.com.bancolombia.certificacion.listarvinculacion.models;



public class DataServicios {

    protected String servicio;
    protected String contenidoRequest;
    protected String numeroPagina;
    protected String tamanoPagina;
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
    protected String tipovalidacion;

    public String getTipovalidacion() {
        return tipovalidacion;
    }

    public String getServicio() { return servicio;  }

    public String getContenidoRequest() { return contenidoRequest; }

    public String getNumeroPagina() {
        return numeroPagina;
    }

    public String getTamanoPagina() {
        return tamanoPagina;
    }

    public String getIdVinculation() {
        return idVinculation;
    }

    public String getCommissionType() {
        return commissionType;
    }

    public Double getCommissionValueFixed() {
        return commissionValueFixed;
    }

    public Double getCommissionValuePercentage() {
        return commissionValuePercentage;
    }

    public Integer getCustomStop() {
        return customStop;
    }

    public String getExonerationCommissionStartDate() {
        return exonerationCommissionStartDate;
    }

    public String getExonerationCommissionEndDate() {
        return exonerationCommissionEndDate;
    }

    public String getExonerationCommissionType() {
        return exonerationCommissionType;
    }

    public Double getExonerationCommissionValueFixed() {
        return exonerationCommissionValueFixed;
    }

    public Double getExonerationCommissionValuePercentage() {
        return exonerationCommissionValuePercentage;
    }

    public String getSterlingPath() {
        return sterlingPath;
    }

    public String getChannelQr() {
        return channelQr;
    }
}