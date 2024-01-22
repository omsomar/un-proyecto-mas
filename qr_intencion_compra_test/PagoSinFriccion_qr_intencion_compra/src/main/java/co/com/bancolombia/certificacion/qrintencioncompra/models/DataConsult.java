package co.com.bancolombia.certificacion.qrintencioncompra.models;


public class DataConsult {
    private String idQr;
    private String descripcion;
    private String valorIntencion;
    private String estado;
    private String razonCancelacion;
    private String fechaHoraIntencion;
    private String ultimaFechaActualizacion;
    private String idVentaComercio;
    private String numFidelidad;

    public String getValorIntencion() {
        return valorIntencion;
    }

    public String getIdQr() {
        return idQr;
    }

    public String getEstado() {
        return estado;
    }

    public String getFechaHoraIntencion() {
        return fechaHoraIntencion;
    }

    public String getUltimaFechaActualizacion() {
        return ultimaFechaActualizacion;
    }

}
