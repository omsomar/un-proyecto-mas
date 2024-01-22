package co.com.bancolombia.certificacion.vinculacion.models;

public class DataVinculacion {
    /*campos para iniciar el navegador*/
    private String dispositivo;
    private String navegador;
    /*campos del formulario de contacto*/
    private String modelo;

    /*campos del formulario de negocio*/
    private String actividadNeg;
    private String personasNotificar;
    private String cantCuentas;
    private String tipoDoc;
    private String numDoc;
    private String tipoCta;
    private String numeroCta;
    private String estado;

    /*campos del formulario de rte legal*/
    private String numAccionistas;

    /*Campos para el formulario de Negociacion*/
    private String tipoComision;
    private String valorComision;
    private String exoneracion;
    private String notificacion;
    private String saldosConsolidados;
    private String reporteTrxDiario;
    private String estructuradoPor;
    private String opcionEntrega;
    private String cantCorreos;

    /*Este campo permite identificar el escenario de prueba erróneo
     sobre la pantalla negociación al elegir la recepción del reporte trx diario*/
    private String escenario;

    public String getDispositivo() {
        return dispositivo;
    }

    public String getNavegador() {
        return navegador;
    }

    public String getModelo() {
        return modelo;
    }

    public String getActividadNeg() {
        return actividadNeg;
    }

    public String getPersonasNotificar() {
        return personasNotificar;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public String getTipoCta() {
        return tipoCta;
    }

    public String getNumeroCta() {
        return numeroCta;
    }

    public String getEstado() {
        return estado;
    }

    public String getCantCuentas() {
        return cantCuentas;
    }

    public String getNumAccionistas() {
        return numAccionistas;
    }

    public String getTipoComision() {
        return tipoComision;
    }

    public String getValorComision() {
        return valorComision;
    }

    public String getExoneracion() {
        return exoneracion;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public String getSaldosConsolidados() {
        return saldosConsolidados;
    }

    public String getReporteTrxDiario() {
        return reporteTrxDiario;
    }

    public String getEstructuradoPor() {
        return estructuradoPor;
    }

    public String getOpcionEntrega() {
        return opcionEntrega;
    }

    public String getCantCorreos() {
        return cantCorreos;
    }

    public String getEscenario() {
        return escenario;
    }
}
