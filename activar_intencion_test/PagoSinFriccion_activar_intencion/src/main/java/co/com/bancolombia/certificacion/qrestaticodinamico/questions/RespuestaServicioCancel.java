package co.com.bancolombia.certificacion.qrestaticodinamico.questions;

import co.com.bancolombia.certificacion.qrestaticodinamico.integrations.Query;
import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Wait;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades.Convertir;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades.GeneradorArchivosJSON;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades.ListarIntenciones;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Log4j2
public class RespuestaServicioCancel implements Question<Boolean> {

    private String ultimoEscenario;


    public RespuestaServicioCancel(DataServices data) {
        this.ultimoEscenario = data.getUltimoEscenario();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        boolean resultado = false;

        /*Se hace validación para identificar si la respuesta del servicio es incorrecta, se elimine el archivo
         * y en las próximas ejecuciones se genere un archivo nuevo con intenciones pendientes*/
        int estadoServicio = SerenityRest.lastResponse().getStatusCode();

        if (estadoServicio != 202) {
            GeneradorArchivosJSON.deleteJsonFile(Constantes.NOMBRE_ARCHIVO_JSON);
        } else {
            /*Se hace validación en caso de que el servicio sea correcta, se debe obtener la información correspondiente
             * al detalle de la intención (estado,id intencion,qr id) para hacer la comparación con la información almacenada en BD
             * */
            String respuestaPeticion = SerenityRest.lastResponse().getBody()
                    .jsonPath().getMap("data").get("intentionDetail").toString();

            Map<String, String> pathData = Convertir.respuestaServicio(respuestaPeticion);
            String idIntencion = actor.recall("idIntencionCancelar");

            actor.attemptsTo(Wait.per(2000));
            List<Map<String, Object>> respuestaBdPostgres = Consulta.ejecutar(Query.consultarEstado(idIntencion),
                    ConnectionManager.getPostgreSqlConnection());

            if (respuestaBdPostgres.isEmpty()) {
                return resultado;
            } else {
                List<Map<String, Object>> listIntenciones = new ArrayList<>();
                try {
                    listIntenciones = GeneradorArchivosJSON.readFileToListOfMap(Constantes.NOMBRE_ARCHIVO_JSON);
                } catch (IOException e) {
                    log.error("error en la lectura del archivo json", e);
                }
                List<String> intencionesQr = ListarIntenciones.getListByCondition(listIntenciones, "idQr");

                resultado = pathData.get("state").equalsIgnoreCase(Constantes.ESTADO_INTENCION_CANCELADA) &&
                        pathData.get("state").equals(respuestaBdPostgres.get(0).get("intention_status").toString()) &&
                        pathData.get("qrId").equalsIgnoreCase(respuestaBdPostgres.get(0).get("id_qr").toString()) &&
                        intencionesQr.contains(pathData.get("qrId"));
            }

            if ("Si".equalsIgnoreCase(this.ultimoEscenario)) {

                GeneradorArchivosJSON.deleteJsonFile(Constantes.NOMBRE_ARCHIVO_JSON);
            }
        }
        return resultado;
    }

    public static RespuestaServicioCancel respuestaServicioCancel(DataServices data) {
        return new RespuestaServicioCancel(data);
    }
}
