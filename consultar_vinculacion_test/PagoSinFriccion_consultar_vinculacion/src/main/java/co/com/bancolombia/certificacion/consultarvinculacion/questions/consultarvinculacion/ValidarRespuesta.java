package co.com.bancolombia.certificacion.consultarvinculacion.questions.consultarvinculacion;

import co.com.bancolombia.certificacion.consultarvinculacion.integrations.Query;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.Constantes;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ValidarRespuesta implements Question<Boolean> {
    private String escenario;
    private String vinculacion;

    public ValidarRespuesta(String escenario) {
        this.escenario = escenario;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        vinculacion = actor.recall("idVinculacion");
        int codigoRespuesta = SerenityRest.lastResponse().getStatusCode();
        Map<String, Object> respuestaServicio = SerenityRest.lastResponse().getBody().jsonPath().getMap("$");

        List<Map<String, Object>> respuestaBdPostgres = Consulta
                .ejecutar(Query.consultarVinculacion(vinculacion),
                        ConnectionManager.getPostgreSqlConnection());

        return validarCampos(respuestaServicio, respuestaBdPostgres.get(0)) && codigoRespuesta == 200;
    }

    public Boolean validarCampos(Map<String, Object> servicio,
                                 Map<String, Object> baseDatos) {
        if ("camposObligatorios".equalsIgnoreCase(escenario)) {
            return validarCamposObligatorios(servicio, baseDatos);
        }
        if ("camposObligatoriosPorcentualConExoneracion".equalsIgnoreCase(escenario) ||
                "camposObligatoriosPorcentualSinExoneracion".equalsIgnoreCase(escenario)) {
            return validarCamposObligatorios(servicio, baseDatos) &&
                    validarComisionPorcentual(servicio, baseDatos) &&
                    validarExoneracion(servicio, baseDatos);
        }
        if ("camposObligatoriosFijaSinExoneracion".equalsIgnoreCase(escenario) ||
                "camposObligatoriosFijaConExoneracion".equalsIgnoreCase(escenario)) {
            return validarCamposObligatorios(servicio, baseDatos) &&
                    validarComisionFija(servicio, baseDatos) &&
                    validarExoneracion(servicio, baseDatos);
        }
        if ("todosLosCamposPorcentualSinExoneracion".equalsIgnoreCase(escenario) ||
                "todosLosCamposPorcentualConExoneracion".equalsIgnoreCase(escenario)) {
            return validarCamposObligatorios(servicio, baseDatos) &&
                    validarComisionPorcentual(servicio, baseDatos) &&
                    validarExoneracion(servicio, baseDatos) &&
                    validarDatosTecnico(servicio, baseDatos) &&
                    validarServiciosAdicionales(servicio, baseDatos);
        }
        if ("todosLosCamposFijaSinExoneracion".equalsIgnoreCase(escenario) ||
                "todosLosCamposFijaConExoneracion".equalsIgnoreCase(escenario)) {
            return validarCamposObligatorios(servicio, baseDatos) &&
                    validarComisionFija(servicio, baseDatos) &&
                    validarExoneracion(servicio, baseDatos) &&
                    validarDatosTecnico(servicio, baseDatos) &&
                    validarServiciosAdicionales(servicio, baseDatos);
        }
        if ("camposVinculacionRechazada".equalsIgnoreCase(escenario)) {
            return validarCamposObligatorios(servicio, baseDatos) &&
                    validarComisionFija(servicio, baseDatos) &&
                    validarExoneracion(servicio, baseDatos) &&
                    validarRechazoVinculacion(servicio, baseDatos);

        }
        return false;
    }

    public Boolean validarComisionPorcentual(Map<String, Object> servicio,
                                             Map<String, Object> baseDatos) {
        return servicio.get("commissionType").toString().equalsIgnoreCase(baseDatos.get("commission_type").toString()) &&
                new BigDecimal(servicio.get("commissionValuePercentage").toString()).compareTo(new BigDecimal(baseDatos.get("commission_value_percentage").toString())) == 0;
    }

    public Boolean validarComisionFija(Map<String, Object> servicio,
                                       Map<String, Object> baseDatos) {
        return servicio.get("commissionType").toString().equalsIgnoreCase(baseDatos.get("commission_type").toString()) &&
                (Float.valueOf(servicio.get("commissionValueFixed").toString()).compareTo(Float.valueOf(baseDatos.get("commission_value_fixed").toString()))) == 0;
    }

    public Boolean validarExoneracion(Map<String, Object> servicio,
                                      Map<String, Object> baseDatos) {
        Boolean respuesta = false;
        if ("true".equalsIgnoreCase(baseDatos.get(Constantes.EXONERATION).toString())) {
            respuesta = servicio.get(Constantes.EXONERATION).toString().equalsIgnoreCase(baseDatos.get(Constantes.EXONERATION).toString()) &&
                    servicio.get("exonerationStartDate").toString().equalsIgnoreCase(baseDatos.get("exoneration_start_date").toString()) &&
                    servicio.get("exonerationEndDate").toString().equalsIgnoreCase(baseDatos.get("exoneration_end_date").toString());
        } else {
            respuesta = servicio.get(Constantes.EXONERATION).toString().equalsIgnoreCase(baseDatos.get(Constantes.EXONERATION).toString());
        }
        return respuesta;
    }

    public Boolean validarRechazoVinculacion(Map<String, Object> servicio,
                                             Map<String, Object> baseDatos) {
        return servicio.get("reasonReject").toString().equalsIgnoreCase(baseDatos.get("reason_reject").toString());
    }

    public Boolean validarDatosTecnico(Map<String, Object> servicio,
                                       Map<String, Object> baseDatos) {
        return servicio.get("technicalSupportName").toString().equalsIgnoreCase(baseDatos.get("technical_support_name").toString()) &&
                servicio.get("technicalSupportEmail").toString().equalsIgnoreCase(baseDatos.get("technical_support_email").toString()) &&
                servicio.get("technicalSupportPhoneNumber").toString().equalsIgnoreCase(baseDatos.get("technical_support_phone_number").toString());
    }

    public Boolean validarServiciosAdicionales(Map<String, Object> servicio,
                                               Map<String, Object> baseDatos) {
        return servicio.get("toPlan").toString().equalsIgnoreCase(baseDatos.get("to_plan").toString()) &&
                servicio.get("toModel").toString().equalsIgnoreCase(baseDatos.get("to_model").toString()) &&
                servicio.get("channelQr").toString().equalsIgnoreCase(baseDatos.get("channel_qr").toString()) &&
                servicio.get("sterlingPath").toString().equalsIgnoreCase(baseDatos.get("sterling_path").toString()) &&
                servicio.get("dailyTransactionalReport").toString().equalsIgnoreCase(baseDatos.get("daily_transactional_report").toString()) &&
                servicio.get("accountTransactionalReport").toString().equalsIgnoreCase(baseDatos.get("account_transactional_report").toString()) &&
                servicio.get("directNotification").toString().equalsIgnoreCase(baseDatos.get("direct_notification").toString()) &&
                servicio.get("multicash").toString().equalsIgnoreCase(baseDatos.get("multicash").toString()) &&
                servicio.get("consolidatedDailyAccount").toString().equalsIgnoreCase(baseDatos.get("consolidated_daily_account").toString());
    }

    public Boolean validarCamposObligatorios(Map<String, Object> servicio,
                                             Map<String, Object> baseDatos) {
        Boolean respuestaVisita = false;
        Boolean respuestaComercio = false;
        JsonArray terminosBd = new Gson().fromJson(baseDatos.get("terms_and_conditions").toString(), JsonArray.class);
        String terms = new Gson().toJson(servicio.get("termsAndConditions"));
        JsonArray terminosServ = new Gson().fromJson(terms, JsonArray.class);
        respuestaVisita = servicio.get("creatorVisitDate").toString().equalsIgnoreCase(baseDatos.get("creator_visit_date").toString()) &&
                servicio.get("creatorAdviserId").toString().equalsIgnoreCase(baseDatos.get("creator_adviser_id").toString()) &&
                servicio.get("channelName").toString().equalsIgnoreCase(baseDatos.get("channel_name").toString());
        respuestaComercio =
                servicio.get("idVinculation").toString().equalsIgnoreCase(baseDatos.get("id_vinculation").toString()) && servicio.get("idVinculation").toString().equalsIgnoreCase(this.vinculacion) &&
                        servicio.get("commerceName").toString().equalsIgnoreCase(baseDatos.get("commerce_name").toString()) &&
                        servicio.get("commerceActivityName").toString().equalsIgnoreCase(baseDatos.get("commerce_activity_name").toString()) &&
                        servicio.get("commerceDocumentType").toString().equalsIgnoreCase(baseDatos.get("commerce_document_type").toString()) &&
                        servicio.get("commerceDocumentNumber").toString().equalsIgnoreCase(baseDatos.get("commerce_document_number").toString()) &&
                        servicio.get("legalReprName").toString().equalsIgnoreCase(baseDatos.get("legal_repr_name").toString()) &&
                        servicio.get("legalReprDocumentType").toString().equalsIgnoreCase(baseDatos.get("legal_repr_document_type").toString()) &&
                        servicio.get("legalReprDocumentNumber").toString().equalsIgnoreCase(baseDatos.get("legal_repr_document_number").toString()) &&
                        servicio.get("informationValidation").toString().equalsIgnoreCase(baseDatos.get("information_validation").toString()) &&
                        terminosServ.get(0).getAsJsonObject().get("accept").getAsString().equalsIgnoreCase(terminosBd.get(0).getAsJsonObject().get("accept").getAsString()) &&
                        terminosServ.get(0).getAsJsonObject().get("dateTime").getAsString().equalsIgnoreCase(terminosBd.get(0).getAsJsonObject().get("dateTime").getAsString());
        return respuestaVisita && respuestaComercio;
    }

    public static ValidarRespuesta de(String escenario) {
        return new ValidarRespuesta(escenario);
    }
}
