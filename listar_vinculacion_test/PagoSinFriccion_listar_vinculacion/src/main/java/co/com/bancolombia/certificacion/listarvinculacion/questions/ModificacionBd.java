package co.com.bancolombia.certificacion.listarvinculacion.questions;

import co.com.bancolombia.certificacion.listarvinculacion.integration.Querys;
import co.com.bancolombia.certificacion.listarvinculacion.interactions.Wait;
import co.com.bancolombia.certificacion.listarvinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModificacionBd implements Question<Boolean> {

    private String escenario;
    private Boolean validacionFinal = false;
    public ModificacionBd(String escenario) {
        this.escenario = escenario;
    }

    @Override
    public Boolean answeredBy(Actor actor) {


        List<Map<String, Object>> consultaPrevia = actor.recall("Valores");


        List<String> valores = new ArrayList<>();
        actor.attemptsTo(Wait.per(2000));
        String respuestaServicio = SerenityRest.lastResponse().getBody()
                .jsonPath().getString("idVinculation");
        List<Map<String, Object>> respuestaBd = Consulta.ejecutar(Querys.consultarInfo(respuestaServicio),
                ConnectionManager.getPostgreSqlConnection());

        if ("primerescenario".equalsIgnoreCase(escenario)){
            valores.add("exoneration_commission_type");
            valores.add("exoneration_commission_value_fixed");
            valores.add("exoneration_commission_value_percentage");
            validacionCampos(consultaPrevia, respuestaBd, valores);

        }else if ("segundoescenario".equalsIgnoreCase(escenario)){
            valores.add("commission_type");
            valores.add("commission_value_fixed");
            valores.add("commission_value_percentage");
            validacionCampos(consultaPrevia, respuestaBd, valores);

        }else if ("tercerescenario".equalsIgnoreCase(escenario)){
            if (((consultaPrevia.get(0).get("channel_qr") == null ||
                    consultaPrevia.get(0).get("channel_qr") != null) &&
                    respuestaBd.get(0).get("channel_qr") != null) &&
                    !consultaPrevia.get(0).get("channel_qr").toString()
                            .equalsIgnoreCase(respuestaBd.get(0).get("channel_qr").toString()) ){
                validacionFinal = true;
            }
        }else{
            validacionFinal = true;
        }
        return validacionFinal;
    }

    public void validacionCampos(List<Map<String, Object>> consultaPrevia,
                                           List<Map<String, Object>> respuestaBd, List<String> valor ){
        if (((consultaPrevia.get(0).get(valor.get(0)) == null ||
                consultaPrevia.get(0).get(valor.get(0)) != null) &&
                respuestaBd.get(0).get(valor.get(0)) != null) &&
                !consultaPrevia.get(0).get(valor.get(0)).toString()
                        .equalsIgnoreCase(respuestaBd.get(0).get(valor.get(0)).toString())
        ){
            if (((consultaPrevia.get(0).get(valor.get(1)) == null ||
                    consultaPrevia.get(0).get(valor.get(1)) != null) &&
                    respuestaBd.get(0).get(valor.get(1)) != null) &&
                    !consultaPrevia.get(0).get(valor.get(1)).toString()
                            .equalsIgnoreCase(respuestaBd.get(0).get(valor.get(1)).toString())){
                validacionFinal = true;
            }else if (((consultaPrevia.get(0).get(valor.get(2)) == null ||
                    consultaPrevia.get(0).get(valor.get(2)) != null) &&
                    respuestaBd.get(0).get(valor.get(2)) != null) &&
                    !consultaPrevia.get(0).get(valor.get(2)).toString()
                            .equalsIgnoreCase(respuestaBd.get(0).get(valor.get(2)).toString()) ){
                validacionFinal = true;
            }
        }
    }

    public static ModificacionBd modificacionBd(String escenario){
        return new ModificacionBd(escenario);
    }
}
