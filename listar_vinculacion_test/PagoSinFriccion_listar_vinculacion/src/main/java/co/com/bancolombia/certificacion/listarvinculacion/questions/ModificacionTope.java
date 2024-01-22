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

public class ModificacionTope implements Question<Boolean> {

    private Integer tope;

    public ModificacionTope(Integer tope) {
        this.tope = tope;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        List<Map<String, Object>> consultaPrevia = actor.recall("Valores");
        actor.attemptsTo(Wait.per(2000));
        String respuestaServicio = SerenityRest.lastResponse().getBody()
                .jsonPath().getString("idVinculation");
        List<Map<String, Object>> respuestaBd = Consulta.ejecutar(Querys.consultarInfo(respuestaServicio),
                ConnectionManager.getPostgreSqlConnection());
        return validarTope(consultaPrevia, respuestaBd);
    }

    public boolean validarTope(List<Map<String, Object>> consultaPrevia, List<Map<String, Object>> respuestaBd) {
        BigDecimal customStop1 = (BigDecimal) consultaPrevia.get(0).get("custom_stop");
        BigDecimal customStop2 = (BigDecimal) respuestaBd.get(0).get("custom_stop");
        boolean a = !(customStop1.toString().equals(customStop2.toString()));
        boolean b = (customStop2.toString().equals(tope.toString()+".00"));
        return a && b;
    }

    public static ModificacionTope modificacionTope(Integer tope) {
        return new ModificacionTope(tope);
    }
}
