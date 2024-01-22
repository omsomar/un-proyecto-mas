package co.com.bancolombia.certificacion.infologtransaccional.models.getinfo;

public class DataFeature {

    protected String servicio;
    protected String escenario;
    protected String peticion;
    protected String canal;
    protected String numRastreo;
    protected String tipoCtaDestino;
    protected String numCtaDestino;
    protected String tipoDocDestino;
    protected String numDocDestino;
    protected String pointOfSale;
    protected String cashRegister;
    protected String cashRegisterSerial;
    protected String seller;
    protected String estado;
    protected String numeroPagina;
    protected String tamanoPagina;

    public String getServicio() { return servicio; }
    public String getEscenario() {
        return escenario;
    }
    public String getPeticion() { return peticion; }
    public String getCanal() { return canal; }
    public String getNumRastreo() { return numRastreo; }
    public String getTipoCtaDestino() { return tipoCtaDestino; }
    public String getNumCtaDestino() { return numCtaDestino; }
    public String getTipoDocDestino() { return tipoDocDestino; }
    public String getNumDocDestino() { return numDocDestino; }
    public String getPointOfSale() { return pointOfSale; }
    public String getCashRegister() { return cashRegister; }
    public String getCashRegisterSerial() { return cashRegisterSerial; }
    public String getSeller() { return seller; }
    public String getEstado() { return estado; }
    public String getNumeroPagina() { return numeroPagina; }
    public String getTamanoPagina() { return tamanoPagina; }
}