package co.com.bancolombia.certificacion.qrestaticodinamico.questions;


import co.com.bancolombia.certificacion.qrestaticodinamico.integrations.Query;
import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Wait;
import co.com.bancolombia.certificacion.qrestaticodinamico.models.DataServices;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.utilidades.GeneradorArchivosJSON;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class RespuestaDelServicio implements Question<Boolean> {

    protected String cantidad;
    protected String escenario;


    public RespuestaDelServicio(DataServices data) {
        this.escenario = data.getEscenario();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        cantidad = actor.recall("cantidadIntencion");
        return respuestaServicio(actor);
    }

    public Boolean respuestaServicio(Actor actor) {
        Map<String, Object> respuestaServicio = SerenityRest.lastResponse().getBody()
                .jsonPath().getMap("data");

        actor.attemptsTo(Wait.per(2000));
        List<Map<String, Object>> respuestaBdPostgres = Consulta
                .ejecutar(Query.consultarIntencion(respuestaServicio.get(Constantes.CAMPO_INTENCION).toString()),
                        ConnectionManager.getPostgreSqlConnection());

        Map<String, Object> infoIntencion = new HashMap<>();
        infoIntencion.put("idIntencion", respuestaServicio.get(Constantes.CAMPO_INTENCION).toString());
        infoIntencion.put("idQr", respuestaBdPostgres.get(0).get("id_qr").toString());
        List<Map<String, Object>> intenciones = new ArrayList<>();
        intenciones.add(infoIntencion);

        try {
            GeneradorArchivosJSON.convertirMapaAArchivo(intenciones, "intenciones.json");
        } catch (IOException e) {
            log.error("Error escribiendo el archivo Json", e);
        }

        boolean respuesta = false;
        if (respuestaBdPostgres == null || respuestaBdPostgres.isEmpty())
            return respuesta;

        if (respuestaServicio.get(Constantes.CAMPO_INTENCION).toString().equalsIgnoreCase(respuestaBdPostgres.get(0).get("id_intention").toString())
                && respuestaBdPostgres.get(0).get("intention_status").toString().equalsIgnoreCase(Constantes.ESTADO_INTENCION_CREADA)) {

            if ("todosLosCamposDecimales".equalsIgnoreCase(escenario)) {
                respuesta = cantidad.equals(respuestaBdPostgres.get(0).get("amount").toString());
            } else {
                double cant = Double.parseDouble(respuestaBdPostgres.get(0).get("amount").toString());
                int cantIntention = (int) cant;
                respuesta = cantidad.equals(String.valueOf(cantIntention));
            }
        }
        return respuesta;

    }

    public static RespuestaDelServicio respuestaDelServicio(DataServices data) {
        return new RespuestaDelServicio(data);
    }
}
