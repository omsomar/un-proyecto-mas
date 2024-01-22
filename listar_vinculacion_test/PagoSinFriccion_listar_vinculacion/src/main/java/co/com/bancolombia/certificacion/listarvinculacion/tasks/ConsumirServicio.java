package co.com.bancolombia.certificacion.listarvinculacion.tasks;


import co.com.bancolombia.certificacion.listarvinculacion.integration.Querys;
import co.com.bancolombia.certificacion.listarvinculacion.interactions.Options;
import co.com.bancolombia.certificacion.listarvinculacion.models.DataServicios;
import co.com.bancolombia.certificacion.listarvinculacion.utils.BodyRequest;
import co.com.bancolombia.certificacion.listarvinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.certificacion.listarvinculacion.utils.enums.PeticionModificar;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import java.util.List;
import java.util.Map;

import static co.com.bancolombia.certificacion.listarvinculacion.utils.Constantes.SERVICIO;


public class ConsumirServicio implements Task {

    private DataServicios dataServicios;

    public ConsumirServicio(DataServicios dataServicios) {
        this.dataServicios = dataServicios;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.remember("Valores", respuestaBd(dataServicios.getIdVinculation()));

        BodyRequest bodyRequest = new BodyRequest(dataServicios);
        actor.attemptsTo(
                Options.to(SERVICIO).with(
                        requestSpecification -> requestSpecification.relaxedHTTPSValidation()
                                .contentType(ContentType.JSON).body(PeticionModificar.peticionBankInfo("todosLosCampos")
                                .getValidateRequest(bodyRequest)))
        );
    }
    public static List<Map<String, Object>> respuestaBd(String id){
        List<Map<String, Object>> respuestaBd = Consulta.ejecutar(Querys.consultarInfo(id),
                ConnectionManager.getPostgreSqlConnection());
        return respuestaBd;
    }
    public static ConsumirServicio consumirServicio(DataServicios dataServicios){return new ConsumirServicio(dataServicios);}

}
