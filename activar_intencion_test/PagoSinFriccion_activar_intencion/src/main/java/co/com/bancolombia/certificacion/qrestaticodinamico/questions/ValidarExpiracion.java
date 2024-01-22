package co.com.bancolombia.certificacion.qrestaticodinamico.questions;

import co.com.bancolombia.certificacion.qrestaticodinamico.integrations.Query;
import co.com.bancolombia.certificacion.qrestaticodinamico.interactions.Wait;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes.Constantes;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidarExpiracion implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {
        String idIntencion = SerenityRest.lastResponse().getBody()
                .jsonPath().getMap("data").get("qrIntentionId").toString();

        List<Map<String, Object>> primerConsulta = Consulta.ejecutar(Query.consultarIntencion(idIntencion),
                ConnectionManager.getPostgreSqlConnection());

        actor.attemptsTo(Wait.per(600000));
        List<Map<String, Object>> segundaConsulta = Consulta.ejecutar(Query.consultarIntencion(idIntencion),
                ConnectionManager.getPostgreSqlConnection());

        boolean resultado = false;
        if (!primerConsulta.get(0).get(Constantes.CAMPO_ESTADO_INTENCION_BD).toString().equals(
                segundaConsulta.get(0).get(Constantes.CAMPO_ESTADO_INTENCION_BD).toString())) {

            resultado = Constantes.ESTADO_INTENCION_CREADA.equalsIgnoreCase(primerConsulta.get(0).get(Constantes.CAMPO_ESTADO_INTENCION_BD).toString()) &&
                    Constantes.ESTADO_INTENCION_EXPIRADA.equalsIgnoreCase(segundaConsulta.get(0).get(Constantes.CAMPO_ESTADO_INTENCION_BD).toString()) &&
                    Constantes.RAZON_EXPIRACION.equalsIgnoreCase(segundaConsulta.get(0).get("reason").toString());
        }
        return resultado;
    }

    public static ValidarExpiracion validarIntencionExpirada() {
        return new ValidarExpiracion();
    }
}
