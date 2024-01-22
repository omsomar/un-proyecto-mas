package co.com.bancolombia.certificacion.qrintencioncompra.models;

public class FiltrosIntencion {

    private String idQr;
    private String tipoDoc;
    private String numDoc;
    private String tipoCuenta;
    private String numCuenta;
    private String idVentaComercio;
    private String paginationSize;

    protected String escenario;
    protected String paginationKey;


    public String getPaginationSize() {
        return paginationSize;
    }

    public String getIdQR() {
        return idQr;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public String getIdVentaComercio() {
        return idVentaComercio;
    }

    public String getEscenario() {
        return escenario;
    }

    public String getPaginationKey() {
        return paginationKey;
    }
}
