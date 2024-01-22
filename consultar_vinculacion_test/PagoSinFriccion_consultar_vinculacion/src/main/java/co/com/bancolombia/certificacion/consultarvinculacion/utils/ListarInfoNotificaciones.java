package co.com.bancolombia.certificacion.consultarvinculacion.utils;

import co.com.bancolombia.certificacion.consultarvinculacion.integrations.Query;
import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.FiltrosConsultaBd;
import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.FiltrosConsultaServicio;
import co.com.bancolombia.certificacion.consultarvinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarInfoNotificaciones {

    private ListarInfoNotificaciones() {

    }

    public static Boolean validarInfoServicioYBaseDatos(String escenario,
                                                        String tipoCuenta,
                                                        String numCuenta,
                                                        List<Map<String, Object>> listaServicio,
                                                        List<Map<String, Object>> listaBd,
                                                        FiltrosConsultaServicio filtrosServicios,
                                                        FiltrosConsultaBd filtrosBd) {
        List<Boolean> respuestaGlobal = new ArrayList<>();
        Boolean respuesta = false;
        Boolean respuestaPlanAvanzado = true;
        Boolean respuestaPlanIntermedio= true;
        Boolean respuestaComision = true;
        Boolean respuestaRespTecnico = true;
        Boolean repuestaReprLegal = true;
        Boolean respuestaTyC = true;
        Boolean respuestaInfoValidation = true;
        Boolean respuestaExoneracion = true;
        Boolean respuestaExoneracionPersonal = true;
        Boolean respuestaChannelYSterling = true;


        Boolean respuestaReporteriaTrx = true;
        Boolean respuestaSaldosConsolidados = true;
        Boolean respuestaServNotificaciones = true;

        String terminosCondServ;
        JsonArray tcServicio;
        JsonArray tcBd;

        /* variables de la revisión de cuentas y contactos*/
        String idVinculacion;
        Boolean validarCuentas = true;
        Boolean validarContactos = true;
        Boolean validarEmailsReporteria = true;

        List<Map<String, Object>> listaCuentas;
        List<Map<String, Object>> listaContactos;
        List<Map<String, Object>> listaEmails;

        JsonArray contactosArray;
        String contactos;

        JsonArray cuentasArray;
        String cuentas;

        JsonArray emailsArray;
        String emails;

        System.out.println("CASO DE PRUEBA"+ escenario);
        for (int i = 0; i < listaServicio.size(); i++) {

            System.out.println("ID VINCULACION BD: "+ listaBd.get(i).get(filtrosBd.getIdVinculation()).toString());
            System.out.println("ID VINCULACION serv: "+ listaServicio.get(i).get(filtrosServicios.getIdVinculation()).toString());
            System.out.println("CANAL DE VINCULACION: "+ listaBd.get(i).get(filtrosBd.getChannelName()).toString());
            String dateCreateBd = Utilidades.cambiarFormato(listaBd.get(i).get(filtrosBd.getCreatedAt()).toString());
            String dateUpdateBd = Utilidades.cambiarFormato(listaBd.get(i).get(filtrosBd.getUpdatedAt()).toString());
            respuesta = listaServicio.get(i).get(filtrosServicios.getIdVinculation()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getIdVinculation()).toString()) &&
                    listaServicio.get(i).get(filtrosServicios.getCreatedAt()).toString().equalsIgnoreCase(dateCreateBd) &&
                    listaServicio.get(i).get(filtrosServicios.getUpdatedAt()).toString().equalsIgnoreCase(dateUpdateBd) &&
                    listaServicio.get(i).get(filtrosServicios.getChannelName()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getChannelName()).toString()) &&
                    listaServicio.get(i).get(filtrosServicios.getCommerceDocumentType()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommerceDocumentType()).toString()) &&
                    listaServicio.get(i).get(filtrosServicios.getCommerceDocumentNumber()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommerceDocumentNumber()).toString()) &&
                    listaServicio.get(i).get(filtrosServicios.getVinculationStatus()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getVinculationStatus()).toString());

            if("Plan Intermedio".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getToPlan()).toString()) &&
                    listaBd.get(i).get(filtrosBd.getCommerceName()) != null){
                 respuestaPlanIntermedio= listaServicio.get(i).get(filtrosServicios.getCommerceName()).toString().
                        equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommerceName()).toString());
            }

            if("Plan Avanzado".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getToPlan()).toString())){

                respuestaPlanAvanzado = listaServicio.get(i).get(filtrosServicios.getCreatorVisitDate()).toString().
                        equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCreatorVisitDate()).toString()) &&
                        listaServicio.get(i).get(filtrosServicios.getCreatorAdviserId()).toString().
                                equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCreatorAdviserId()).toString()) &&
                        listaServicio.get(i).get(filtrosServicios.getCommerceActivityName()).toString().
                                equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommerceActivityName()).toString()) &&
                        listaServicio.get(i).get(filtrosServicios.getCommerceName()).toString().
                                equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommerceName()).toString()) &&
                        listaServicio.get(i).get(filtrosServicios.getToModel()).toString().
                                equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getToModel()).toString());

                if (listaBd.get(i).get(filtrosBd.getLegalReprDocumentType()) != null &&
                        listaBd.get(i).get(filtrosBd.getLegalReprDocumentNumber()) != null &&
                        listaBd.get(i).get(filtrosBd.getLegalReprName()) != null) {

                    repuestaReprLegal = listaServicio.get(i).get(filtrosServicios.getLegalReprName()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getLegalReprName()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getLegalReprDocumentType()).toString().
                                    equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getLegalReprDocumentType()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getLegalReprDocumentNumber()).toString().
                                    equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getLegalReprDocumentNumber()).toString());
                }
                if (listaBd.get(i).get(filtrosBd.getTechnicalSupportName()) != null) {
                    respuestaRespTecnico = listaServicio.get(i).get(filtrosServicios.getTechnicalSupportName()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getTechnicalSupportName()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getTechnicalSupportEmail()).toString().
                                    equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getTechnicalSupportEmail()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getTechnicalSupportPhoneNumber()).toString().
                                    equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getTechnicalSupportPhoneNumber()).toString());
                }
                if (listaBd.get(i).get(filtrosBd.getInformationValidation()) != null) {
                    if( listaBd.get(i).get(filtrosBd.getApiName()) != null){

                        respuestaInfoValidation = listaServicio.get(i).get(filtrosServicios.getApiName()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getApiName()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getInformationValidation()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getInformationValidation()).toString());
                    } else{
                        respuestaInfoValidation = listaServicio.get(i).get(filtrosServicios.getInformationValidation()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getInformationValidation()).toString());
                    }

                }
                if (listaBd.get(i).get(filtrosBd.getTermsAndConditions()) != null) {
                    /* se obtiene los terminos y condiciones del servicio*/
                    terminosCondServ = new Gson().toJson(listaServicio.get(i).get(filtrosServicios.getTermsAndConditions()));
                    tcServicio = new Gson().fromJson(terminosCondServ, JsonArray.class);
                    /* se obtiene los terminos y condiciones de la bd*/
                    String terminosyC = listaBd.get(i).get(filtrosBd.getTermsAndConditions()).toString();
                    tcBd = new Gson().fromJson(terminosyC, JsonArray.class);
                    respuestaTyC = tcServicio.getAsJsonArray().get(0).getAsJsonObject().get(filtrosServicios.getDateTime()).getAsString().
                            equalsIgnoreCase(tcBd.getAsJsonArray().get(0).getAsJsonObject().get(filtrosBd.getDateTime()).getAsString());
                }

                if(listaBd.get(i).get(filtrosBd.getChannelQr()) != null &&
                        listaBd.get(i).get(filtrosBd.getSterling()) != null){
                    respuestaChannelYSterling = listaBd.get(i).get(filtrosBd.getChannelQr()).toString().equalsIgnoreCase(listaServicio.get(i).get(filtrosServicios.getChannelQr()).toString())&&
                            listaBd.get(i).get(filtrosBd.getSterling()).toString().equalsIgnoreCase(listaServicio.get(i).get(filtrosServicios.getSterling()).toString());
                }

                if (listaBd.get(i).get(filtrosBd.getDailyTransactionalReport()) != null &&
                        listaBd.get(i).get(filtrosBd.getAccountTransactionalReport()) != null &&
                        listaBd.get(i).get(filtrosBd.getDirectNotification()) != null &&
                        listaBd.get(i).get(filtrosBd.getConsolidatedDailyAccount())!= null) {

                    respuestaSaldosConsolidados = listaServicio.get(i).get(filtrosServicios.getConsolidatedDailyAccount()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getConsolidatedDailyAccount()).toString());

                    respuestaServNotificaciones = listaServicio.get(i).get(filtrosServicios.getDirectNotification()).toString().
                                    equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getDirectNotification()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getMulticash()).toString().
                                    equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getMulticash()).toString());

                    if("true".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getDailyTransactionalReport()).toString()) &&
                      "true".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getEmailTransfer()).toString())) {

                        respuestaReporteriaTrx = listaServicio.get(i).get(filtrosServicios.getDailyTransactionalReport()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getDailyTransactionalReport()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getAccountTransactionalReport()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getAccountTransactionalReport()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getEmailTransfer()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getEmailTransfer()).toString());

                        /*verificar que los correos destinatarios que se visualizan en el listado de vinculaciones coincida con los guardados en base de datos*/
                        if(listaServicio.get(i).containsKey(filtrosServicios.getEmailsReporteria())){

                            idVinculacion = listaServicio.get(i).get(filtrosServicios.getIdVinculation()).toString();
                            listaEmails = Consulta
                                    .ejecutar(Query.consultarEmailsReporteria(idVinculacion),
                                            ConnectionManager.getPostgreSqlConnection());

                            Map<String,String> infoEmailBd = new HashMap<>();
                            Map<String,String> infoEmailServ = new HashMap<>();
                            emails = new Gson().toJson(listaServicio.get(i).get(filtrosServicios.getEmailsReporteria()));
                            emailsArray = new Gson().fromJson(emails, JsonArray.class);
                            if(listaEmails.size() > 0){
                                for (int j=0; j <listaEmails.size();j++){
                                    infoEmailServ.put(emailsArray.getAsJsonArray().get(j).getAsJsonObject().get(filtrosServicios.getIdEmailReport()).getAsString(),
                                            emailsArray.getAsJsonArray().get(j).getAsJsonObject().get(filtrosServicios.getEmail()).getAsString());
                                    infoEmailBd.put(listaEmails.get(j).get(filtrosBd.getIdEmailReport()).toString(),listaEmails.get(j).get(filtrosBd.getEmailReport()).toString());

                                }
                                validarEmailsReporteria = infoEmailServ.values().containsAll(infoEmailBd.values()) &&
                                        infoEmailServ.keySet().containsAll(infoEmailBd.keySet());
                            }
                        }
                    } else if("true".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getDailyTransactionalReport()).toString()) &&
                            "true".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getFileTransfer()).toString())){

                        respuestaReporteriaTrx = listaServicio.get(i).get(filtrosServicios.getDailyTransactionalReport()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getDailyTransactionalReport()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getAccountTransactionalReport()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getAccountTransactionalReport()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getFileTransfer()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getFileTransfer()).toString());

                    } else{
                        respuestaReporteriaTrx = listaServicio.get(i).get(filtrosServicios.getDailyTransactionalReport()).toString().
                                equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getDailyTransactionalReport()).toString());
                    }
                }
            }

            if (listaBd.get(i).get(filtrosBd.getCommissionType()) != null) {
                if ("P".equalsIgnoreCase(listaServicio.get(i).get(filtrosServicios.getCommissionType()).toString()) &&
                        listaBd.get(i).get(filtrosBd.getCommissionValuePercentage()) != null) {

                    respuestaComision = listaServicio.get(i).get(filtrosServicios.getCommissionType()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommissionType()).toString()) &&
                            Float.valueOf(listaServicio.get(i).get(filtrosServicios.getCommissionValuePercentage()).toString()).
                                    compareTo(Float.valueOf(listaBd.get(i).get(filtrosBd.getCommissionValuePercentage()).toString())) == 0;

                } else if ("F".equalsIgnoreCase(listaServicio.get(i).get(filtrosServicios.getCommissionType()).toString()) &&
                        listaBd.get(i).get(filtrosBd.getCommissionValueFixed()) != null) {

                    respuestaComision = listaServicio.get(i).get(filtrosServicios.getCommissionType()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommissionType()).toString()) &&
                            Float.valueOf(listaServicio.get(i).get(filtrosServicios.getCommissionValueFixed()).toString()). //fechaInicio
                                    compareTo(Float.valueOf(listaBd.get(i).get(filtrosBd.getCommissionValueFixed()).toString())) == 0;

                } else{
                    respuestaComision = listaServicio.get(i).get(filtrosServicios.getCommissionType()).toString().
                            equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCommissionType()).toString());
                }
            }
            /*validar si la vinculación tiene exoneración basica*/
            if (listaBd.get(i).get(filtrosBd.getExoneration()) != null) {
                if ("true".equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExoneration()).toString()) &&
                        listaServicio.get(i).get(filtrosServicios.getExonerationStartDate()) != null) {

                    respuestaExoneracion = listaServicio.get(i).get(filtrosServicios.getExoneration()).toString()
                            .equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExoneration()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getExonerationStartDate()).toString()
                                    .equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationStartDate()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getExonerationEndDate()).toString()
                                    .equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationEndDate()).toString());
                } else {
                    respuestaExoneracion = listaServicio.get(i).get(filtrosServicios.getExoneration()).toString()
                            .equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExoneration()).toString());
                }
            }
            /*validar si la vinculación tiene exoneración personalizada*/
            if(listaBd.get(i).get(filtrosBd.getExonerationCommissionType()) != null &&
                    listaBd.get(i).get(filtrosBd.getExonerationCommission()) != null){
                if ("P".equalsIgnoreCase(listaServicio.get(i).get(filtrosServicios.getExonerationCommissionType()).toString()) &&
                        listaBd.get(i).get(filtrosBd.getExonerationCommissionValuePercentage()) != null) {

                    if(listaBd.get(i).get(filtrosBd.getCustomStop()) != null){
                        respuestaExoneracionPersonal = listaServicio.get(i).get(filtrosServicios.getCustomStop()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getCustomStop()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommissionType()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommissionType()).toString()) &&
                                Float.valueOf(listaServicio.get(i).get(filtrosServicios.getExonerationCommissionValuePercentage()).toString()).
                                        compareTo(Float.valueOf(listaBd.get(i).get(filtrosBd.getExonerationCommissionValuePercentage()).toString())) == 0 &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommission()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommission()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommissionStartDate()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommissionStartDate()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommissionEndDate()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationComissionEndDate()).toString());
                    } else{
                        respuestaExoneracionPersonal = listaServicio.get(i).get(filtrosServicios.getExonerationCommissionType()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommissionType()).toString()) &&
                                Float.valueOf(listaServicio.get(i).get(filtrosServicios.getExonerationCommissionValuePercentage()).toString()).
                                        compareTo(Float.valueOf(listaBd.get(i).get(filtrosBd.getExonerationCommissionValuePercentage()).toString())) == 0 &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommission()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommission()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommissionStartDate()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommissionStartDate()).toString()) &&
                                listaServicio.get(i).get(filtrosServicios.getExonerationCommissionEndDate()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationComissionEndDate()).toString());
                    }
                } else if ("F".equalsIgnoreCase(listaServicio.get(i).get(filtrosServicios.getExonerationCommissionType()).toString()) &&
                        listaBd.get(i).get(filtrosBd.getExonerationCommissionValueFixed()) != null) {

                    respuestaExoneracionPersonal = listaServicio.get(i).get(filtrosServicios.getExonerationCommissionType()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommissionType()).toString()) &&
                            Float.valueOf(listaServicio.get(i).get(filtrosServicios.getExonerationCommissionValueFixed()).toString()).
                                    compareTo(Float.valueOf(listaBd.get(i).get(filtrosBd.getExonerationCommissionValueFixed()).toString())) == 0 &&
                            listaServicio.get(i).get(filtrosServicios.getExonerationCommission()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommission()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getExonerationCommissionStartDate()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationCommissionStartDate()).toString()) &&
                            listaServicio.get(i).get(filtrosServicios.getExonerationCommissionEndDate()).toString().equalsIgnoreCase(listaBd.get(i).get(filtrosBd.getExonerationComissionEndDate()).toString());;

                }
            }

            /* se valida los contactos de notificación definidos para la vinculación,
            * recordar que en una vinculacion a plan avanzado pueden haber varios contactos de notificacion
            * mientras que en una vinculacion a plan intermedio (subscription) se puede tener según el comportamiento actual del servicio un solo contacto
            * pero los contactos de notificación de ambos tipos de planes se guardan en la misma tabla.*/
            if (listaServicio.get(i).containsKey(filtrosServicios.getNotification())) {
                idVinculacion = listaServicio.get(i).get(filtrosServicios.getIdVinculation()).toString();
                listaContactos = Consulta
                        .ejecutar(Query.consultarContactosVinculacion(idVinculacion),
                                ConnectionManager.getPostgreSqlConnection());

                contactos = new Gson().toJson(listaServicio.get(i).get(filtrosServicios.getNotification()));
                contactosArray = new Gson().fromJson(contactos, JsonArray.class);

                if(listaContactos.size() > 0){
                    validarContactos = obtenerInfoServicio(contactosArray, filtrosServicios).containsAll(obtenerInfoBd(listaContactos, filtrosBd));
                }
            }

            /* se valida las cuentas asociadas a la vinculación*/
            if (listaServicio.get(i).containsKey(filtrosServicios.getAccounts())) {
                idVinculacion = listaServicio.get(i).get(filtrosServicios.getIdVinculation()).toString();
                if ("numCuenta".equalsIgnoreCase(escenario) || "todosLosCampos".equalsIgnoreCase(escenario)
                        || "algunosCamposVinculacion".equalsIgnoreCase(escenario) || "algunosCamposSuscripcion".equalsIgnoreCase(escenario)) {
                    listaCuentas = Consulta
                            .ejecutar(Query.consultarCuentasVinculXCuenta(idVinculacion, tipoCuenta, numCuenta),
                                    ConnectionManager.getPostgreSqlConnection());
                } else {
                    listaCuentas = Consulta
                            .ejecutar(Query.consultarCuentasVinculacion(idVinculacion),
                                    ConnectionManager.getPostgreSqlConnection());
                }
                cuentas = new Gson().toJson(listaServicio.get(i).get(filtrosServicios.getAccounts()));
                cuentasArray = new Gson().fromJson(cuentas, JsonArray.class);

                Map<String,String> infoCuentaBd = new HashMap<>();
                Map<String,String> infoCuentaServ = new HashMap<>();

                System.out.println("cantidad cuentas bd: "+ listaCuentas.size());
                System.out.println("cantidad cuentas servicio: "+ cuentasArray.size());
                if(listaCuentas.size() >0){
                    for (int j = 0; j < listaCuentas.size(); j++) {
                        infoCuentaBd.put(listaCuentas.get(j).get(filtrosBd.getAccountNumber()).toString(), listaCuentas.get(j).get(filtrosBd.getAccountType()).toString());
                        infoCuentaServ.put(cuentasArray.getAsJsonArray().get(j).getAsJsonObject().get(filtrosServicios.getAccountNumber()).getAsString(),cuentasArray.getAsJsonArray().get(j).getAsJsonObject().get(filtrosServicios.getAccountType()).getAsString());
                    }
                    validarCuentas =infoCuentaBd.keySet().equals(infoCuentaServ.keySet());
                }
            }

            respuestaGlobal.add(respuesta && respuestaPlanAvanzado && respuestaPlanIntermedio && respuestaInfoValidation && respuestaComision
                    && respuestaRespTecnico  && repuestaReprLegal && respuestaTyC  && respuestaChannelYSterling && respuestaReporteriaTrx && respuestaSaldosConsolidados
                    && respuestaServNotificaciones && respuestaExoneracion && respuestaExoneracionPersonal && validarEmailsReporteria && validarCuentas && validarContactos);

            System.out.println(" SE IMPRIME VALIDACIÓN DE VINCULACION: SERVICIO " + i);
        }
        if (respuestaGlobal.contains(false)) {
            return false;
        }
        return true;

    }


    public static List<Map<String, String>> obtenerInfoServicio(JsonArray lista,
                                                                FiltrosConsultaServicio servicio) {
        List<Map<String, String>> resultado = new ArrayList<>();
        Map<String, String> map;

        for (int i = 0; i < lista.getAsJsonArray().size(); i++) {
            map = new HashMap<>();
            map.put("idContacto", lista.getAsJsonArray().get(i).getAsJsonObject().get(servicio.getIdContacto()).getAsString());
            map.put("emailContacto", lista.getAsJsonArray().get(i).getAsJsonObject().get(servicio.getContactEmail()).getAsString());
            resultado.add(map);
        }
        return resultado;
    }

    public static List<Map<String, String>> obtenerInfoBd(List<Map<String, Object>> lista,
                                                          FiltrosConsultaBd bd) {
        List<Map<String, String>> resultado = new ArrayList<>();
        Map<String, String> map;
        for (int i = 0; i < lista.size(); i++) {
            map = new HashMap<>();
            map.put("idContacto", lista.get(i).get(bd.getIdContact()).toString());
            map.put("emailContacto", lista.get(i).get(bd.getContactEmail()).toString());
            resultado.add(map);
        }
        return resultado;
    }
}
