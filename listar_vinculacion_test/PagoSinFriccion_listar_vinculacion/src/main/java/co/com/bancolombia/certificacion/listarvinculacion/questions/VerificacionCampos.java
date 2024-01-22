package co.com.bancolombia.certificacion.listarvinculacion.questions;

import co.com.bancolombia.certificacion.listarvinculacion.integration.Querys;
import co.com.bancolombia.certificacion.listarvinculacion.interactions.Wait;
import co.com.bancolombia.certificacion.listarvinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class VerificacionCampos implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {

        actor.attemptsTo(Wait.per(2000));
        String respuestaServicio = SerenityRest.lastResponse().getBody()
                .jsonPath().getString("idVinculation");
        List<Map<String, Object>> respuestaBd = Consulta.ejecutar(Querys.consultarCamposCreator(respuestaServicio),
                ConnectionManager.getPostgreSqlConnection());
        return validarCampos(respuestaBd);
    }

    public boolean validarCampos(List<Map<String, Object>> respuestaBd) {
        boolean a = respuestaBd.get(0).get("creator_visit_date") == null;
        boolean b = respuestaBd.get(0).get("creator_adviser_id") == null;
        return a&&b;
    }

    public static VerificacionCampos verificacionCampos(){
        return new VerificacionCampos();
    }
}
