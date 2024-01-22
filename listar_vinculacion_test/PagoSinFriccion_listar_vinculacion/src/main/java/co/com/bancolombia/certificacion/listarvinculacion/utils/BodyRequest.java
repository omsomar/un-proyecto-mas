package co.com.bancolombia.certificacion.listarvinculacion.utils;

import co.com.bancolombia.certificacion.listarvinculacion.models.Data;
import co.com.bancolombia.certificacion.listarvinculacion.models.DataServicios;

public class BodyRequest {

    protected DataServicios dataServicios;

    public BodyRequest(DataServicios dataServicios) {
        this.dataServicios = dataServicios;
    }

    public Data data(){

        Data data = new Data();
        data.setIdVinculation(dataServicios.getIdVinculation());
        if ("F".equalsIgnoreCase(dataServicios.getCommissionType()) &&
                dataServicios.getCommissionValueFixed() != null){
            data.setCommissionType(dataServicios.getCommissionType());
            data.setCommissionValueFixed(dataServicios.getCommissionValueFixed() + Utlidades.generarNumeroAl());
        }else if ("P".equalsIgnoreCase(dataServicios.getCommissionType()) &&
                dataServicios.getCommissionValuePercentage() != null){
            data.setCommissionType(dataServicios.getCommissionType());
            data.setCommissionValuePercentage(dataServicios.getCommissionValuePercentage() + Utlidades.generarNumeroAl());
        }else{
            data.setCommissionType(dataServicios.getCommissionType());
            data.setCommissionValueFixed(dataServicios.getCommissionValueFixed());
            data.setCommissionValuePercentage(dataServicios.getCommissionValuePercentage());
        }
        data.setCustomStop(dataServicios.getCustomStop());
        if ("vacia".equalsIgnoreCase(dataServicios.getExonerationCommissionStartDate())){
            data.setExonerationCommissionStartDate("");
        }else if ("llena".equalsIgnoreCase(dataServicios.getExonerationCommissionStartDate())){
            data.setExonerationCommissionStartDate(Utlidades.obtenerFecha("").get(0));
        }else{
            data.setExonerationCommissionStartDate(dataServicios.getExonerationCommissionStartDate());
        }
        if ("vacia".equalsIgnoreCase(dataServicios.getExonerationCommissionEndDate())){
            data.setExonerationCommissionEndDate("");
        }else if ("llena".equalsIgnoreCase(dataServicios.getExonerationCommissionEndDate())){
            data.setExonerationCommissionEndDate(Utlidades.obtenerFecha(System.getProperty("FEXP")).get(1));
        }else{
            data.setExonerationCommissionEndDate(dataServicios.getExonerationCommissionEndDate());
        }
        if ("F".equalsIgnoreCase(dataServicios.getExonerationCommissionType()) &&
                dataServicios.getExonerationCommissionValueFixed() != null){
            data.setExonerationCommissionType(dataServicios.getExonerationCommissionType());
            data.setExonerationCommissionValueFixed(
                    dataServicios.getExonerationCommissionValueFixed() + Utlidades.generarNumeroAl());
        }else if ("P".equalsIgnoreCase(dataServicios.getExonerationCommissionType()) &&
                dataServicios.getExonerationCommissionValuePercentage() != null)
        {
            data.setExonerationCommissionType(dataServicios.getExonerationCommissionType());
            data.setExonerationCommissionValuePercentage(
                    dataServicios.getExonerationCommissionValuePercentage() + Utlidades.generarNumeroAl());
        }else{
            data.setExonerationCommissionType(dataServicios.getExonerationCommissionType());
            data.setExonerationCommissionValuePercentage(dataServicios.getExonerationCommissionValuePercentage());
            data.setExonerationCommissionValueFixed(dataServicios.getExonerationCommissionValueFixed());
        }
        data.setSterlingPath(dataServicios.getSterlingPath());
        data.setChannelQr("llena".equalsIgnoreCase(dataServicios.getChannelQr())? Utlidades.generadorId():"");
        return data;
    }

    public Data todosLosCampos(){
        return data();
    }
}
