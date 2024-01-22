package co.com.bancolombia.certificacion.qrintencioncompra.questions.listarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.models.ResponseList;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Convertir;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import static co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes.NUM_SIN_DATOS_LISTAR;

public class LaPaginacion implements Question<Boolean> {
    public LaPaginacion() {
        //buenas practicas sonar
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        ResponseList pathData = Convertir.respuestaServicioListGson();
        if (!pathData.getData().isEmpty()) {
            int valor;
            if (pathData.getPagination().getTotalElements() % pathData.getData().size() == 0) {
                valor = (pathData.getPagination().getTotalElements() / pathData.getData().size()) - 1;
            } else {
                valor = Math.round(pathData.getPagination().getTotalElements() / pathData.getData().size());
            }
            if (pathData.getPagination().getTotalPages() == valor) {
                return true;
            }

        } else if (pathData.getPagination().getTotalPages() == NUM_SIN_DATOS_LISTAR) {
            return true;
        }

        return false;
    }


    public static LaPaginacion es() {
        return new LaPaginacion();
    }
}
