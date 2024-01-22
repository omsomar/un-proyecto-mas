package co.com.bancolombia.certificacion.consultarvinculacion.questions.listarvinculacion;

import co.com.bancolombia.certificacion.consultarvinculacion.integrations.Query;
import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.FiltrosConsultaBd;
import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.FiltrosConsultaServicio;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.ListarInfoNotificaciones;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidarInformacion implements Question<Boolean> {

    private String escenario;
    private List<String> parametros;

    public ValidarInformacion(String escenario) {
        this.escenario = escenario;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        /*  Se llaman las variables de sesión guardadas al hacer la petición del servicio Listar vinculaciones */
        String paginationKey = actor.recall("paginationKey");
        String paginationSize = actor.recall("paginationSize");
        String commerceDocumentType = actor.recall("commerceDocumentType");
        String commerceDocumentNumber = actor.recall("commerceDocumentNumber");
        String legalReprDocumentType = actor.recall("legalReprDocumentType");
        String legalReprDocumentNumber = actor.recall("legalReprDocumentNumber");
        String commerceName = actor.recall("commerceName");
        String typeAccount = actor.recall("typeAccount");
        String numberAccount = actor.recall("numberAccount");
        String sterlingPath = actor.recall("sterlingPath");
        String startDate = actor.recall("startDate");
        String endDate = actor.recall("endDate");

        parametros = new ArrayList<>();
        parametros.add(0, paginationKey);
        parametros.add(1, paginationSize);
        parametros.add(2, commerceDocumentType);
        parametros.add(3, commerceDocumentNumber);
        parametros.add(4, legalReprDocumentType);
        parametros.add(5, legalReprDocumentNumber);
        parametros.add(6, commerceName);
        parametros.add(7, typeAccount);
        parametros.add(8, numberAccount);
        parametros.add(9, sterlingPath);
        parametros.add(10, startDate);
        parametros.add(11, endDate);

        FiltrosConsultaServicio filtrosServ = new FiltrosConsultaServicio();
        FiltrosConsultaBd filtrosBd = new FiltrosConsultaBd();

        /*  Se obtiene la información de respuesta del servicio */
        List<Map<String, Object>> listaServicio = SerenityRest.lastResponse().getBody().jsonPath().getList("data");

        /* Se realiza la consulta a la bd con los filtros realizados en la petición -- esta consulta solo tiene info de la tabla principal vinculation */
        List<Map<String, Object>> respuestaBdPostgres = Consulta
                .ejecutar(Query.listarVinculaciones(escenario, parametros),
                        ConnectionManager.getPostgreSqlConnection());

        return ListarInfoNotificaciones.validarInfoServicioYBaseDatos(escenario, parametros.get(7), parametros.get(8), listaServicio, respuestaBdPostgres, filtrosServ, filtrosBd);
    }

    public static ValidarInformacion de(String escenario) {
        return new ValidarInformacion(escenario);
    }
}
