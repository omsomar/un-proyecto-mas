package co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion;

public class FiltrosListar {

    private String escenario;
    private String campoIncorrecto;
    private String paginationSize;
    private String paginationKey;
    private String tipoDoc;
    private String numDoc;
    private String tipoCuenta;
    private String numCuenta;
    private String fechaInicio;
    private String fechaFin;

    public String getEscenario() {
        return escenario;
    }

    public String getCampoIncorrecto() {
        return campoIncorrecto;
    }

    public String getPaginationSize() {
        return paginationSize;
    }

    public String getPaginationKey() {
        return paginationKey;
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

    public String getFechaInicio() {
        return fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }
}
