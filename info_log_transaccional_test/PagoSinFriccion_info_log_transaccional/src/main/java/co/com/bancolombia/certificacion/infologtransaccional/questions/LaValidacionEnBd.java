package co.com.bancolombia.certificacion.infologtransaccional.questions;

import co.com.bancolombia.certificacion.infologtransaccional.exceptions.ListaContenidaEnQueryEsNulaExceptions;
import co.com.bancolombia.certificacion.infologtransaccional.integrations.Query;
import co.com.bancolombia.certificacion.infologtransaccional.models.getinfo.DataFeature;
import co.com.bancolombia.certificacion.infologtransaccional.utils.Convertir;
import co.com.bancolombia.conexion.basedatos.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class LaValidacionEnBd extends DataFeature implements Question<Boolean> {

    public static LaValidacionEnBd deGetInfoLog() {
        return new LaValidacionEnBd();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        String respServicio = SerenityRest.lastResponse().getBody().jsonPath().getList("data").get(0).toString();
        Map<String, String> pathData = Convertir.respuestaServicioList(respServicio).get(0);

        List<Map<String, Object>> respuestaBdPostgres = Consulta.ejecutar(Query.obtenerCamposGetInfoLog(pathData.get("trackingNumber"))
                , ConnectionManager.getPostgreSqlConnection());

        if (respuestaBdPostgres.isEmpty()) {
            throw new ListaContenidaEnQueryEsNulaExceptions();
        } else {
            return (pathData.get("transactionStatus").equals(respuestaBdPostgres.get(0).get("status").toString()));
        }
    }
}