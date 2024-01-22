package co.com.bancolombia.certificacion.qrestaticodinamico.questions;

import co.com.bancolombia.certificacion.qrestaticodinamico.integrations.Query;
import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Wait;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import java.util.List;
import java.util.Map;

public class ValidarRespuesta implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        String primerIntencion = actor.recall("primeraIntencion");
        String segundaIntencion = actor.recall("segundaIntencion");

        actor.attemptsTo(Wait.per(2000));
        List<Map<String, Object>> respuestaPrimerIntencion = Consulta
                .ejecutar(Query.consultarIntencion(primerIntencion),
                        ConnectionManager.getPostgreSqlConnection());

        actor.attemptsTo(Wait.per(2000));
        List<Map<String, Object>> respuestaSegundaIntencion = Consulta
                .ejecutar(Query.consultarIntencion(segundaIntencion),
                        ConnectionManager.getPostgreSqlConnection());

        boolean respuesta = false;
        if (primerIntencion.isEmpty() || segundaIntencion.isEmpty()) {
            return respuesta;
        }
        if ("Cancelado".equalsIgnoreCase(respuestaPrimerIntencion.get(0).get("intention_status").toString())
                && "Pendiente".equalsIgnoreCase(respuestaSegundaIntencion.get(0).get("intention_status").toString())) {
            respuesta = true;
        }
        return respuesta;
    }

    public static ValidarRespuesta validarIntencionesConsecutivas() {
        return new ValidarRespuesta();
    }
}
