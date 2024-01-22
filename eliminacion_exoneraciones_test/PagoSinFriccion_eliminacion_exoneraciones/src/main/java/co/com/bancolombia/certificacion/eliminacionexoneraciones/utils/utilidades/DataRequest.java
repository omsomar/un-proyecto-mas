package co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.CamposEliminarExoneracion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.DataEliminarExoneracion;

public class DataRequest {

    private DataRequest() {

    }

    public static DataEliminarExoneracion dataListar(CamposEliminarExoneracion filtros) {
        DataEliminarExoneracion data = new DataEliminarExoneracion();

        if("conFecha".equalsIgnoreCase(filtros.getEscenario())){
            data.setDate(filtros.getFecha());
        }
        return data;
    }

    public static DataEliminarExoneracion dataListarFallido(CamposEliminarExoneracion filtros){
        DataEliminarExoneracion data = new DataEliminarExoneracion();
        data.setDate(filtros.getFecha());
        return data;
    }

}
