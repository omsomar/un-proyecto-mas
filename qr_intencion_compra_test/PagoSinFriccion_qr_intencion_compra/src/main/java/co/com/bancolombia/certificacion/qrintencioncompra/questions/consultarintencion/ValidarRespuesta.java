package co.com.bancolombia.certificacion.qrintencioncompra.questions.consultarintencion;

import co.com.bancolombia.certificacion.qrintencioncompra.integrations.Query;
import co.com.bancolombia.certificacion.qrintencioncompra.interactions.Wait;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Constantes;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.Convertir;
import co.com.bancolombia.certificacion.qrintencioncompra.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidarRespuesta implements Question<Boolean> {

    private String escenario;

    public ValidarRespuesta(String escenario) {
        this.escenario = escenario;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean respuestaValidacion = false;
        String qrId = actor.recall("qr");
        String respuesta = SerenityRest.lastResponse().getBody().jsonPath().getMap("").toString();
        Map<String, String> dataRespuesta = Convertir.serviceResponse(respuesta);

        actor.attemptsTo(Wait.per(2000));
        List<Map<String, Object>> respuestaBdPostgres = Consulta.ejecutar(Query.consultarIntencionPorQr(qrId),
                ConnectionManager.getPostgreSqlConnection());

        if (Constantes.CAMPOS_OBLIGATORIOS.equalsIgnoreCase(this.escenario)) {
            respuestaValidacion = dataRespuesta.get(Constantes.CAMPO_INTENCION).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_INTENCION).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_CANTIDAD).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_CANTIDAD).toString());
        } else if (Constantes.TODOS_LOS_CAMPOS.equalsIgnoreCase(this.escenario)) {
            respuestaValidacion = dataRespuesta.get(Constantes.CAMPO_INTENCION).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_INTENCION).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_CANTIDAD).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_CANTIDAD).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_VENTA_COMERCIO).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_VENTA_COMERCIO).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_NUM_FIDELIDAD).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_NUM_FIDELIDAD).toString());
        } else if (Constantes.SIN_NUM_FIDELIDAD.equalsIgnoreCase(this.escenario)) {
            respuestaValidacion = dataRespuesta.get(Constantes.CAMPO_INTENCION).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_INTENCION).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_CANTIDAD).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_CANTIDAD).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_VENTA_COMERCIO).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_VENTA_COMERCIO).toString());
        } else {
            respuestaValidacion = dataRespuesta.get(Constantes.CAMPO_INTENCION).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_INTENCION).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_CANTIDAD).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_CANTIDAD).toString()) &&
                    dataRespuesta.get(Constantes.CAMPO_NUM_FIDELIDAD).equalsIgnoreCase(respuestaBdPostgres.get(0).get(Constantes.CAMPO_BD_NUM_FIDELIDAD).toString());
        }

        return respuestaValidacion;
    }

    public static ValidarRespuesta consultarIntenPorQr(String escenario) {
        return new ValidarRespuesta(escenario);
    }
}
