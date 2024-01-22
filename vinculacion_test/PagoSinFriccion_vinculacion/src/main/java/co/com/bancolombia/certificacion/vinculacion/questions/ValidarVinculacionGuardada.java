package co.com.bancolombia.certificacion.vinculacion.questions;

import co.com.bancolombia.certificacion.vinculacion.integrations.Query;
import co.com.bancolombia.certificacion.vinculacion.interactions.Wait;
import co.com.bancolombia.certificacion.vinculacion.models.DataVinculacion;
import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import co.com.bancolombia.certificacion.vinculacion.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidarVinculacionGuardada implements Question {

    Map<String, String> infoVinculacion;
    List<Map<String, String>> infoSocios;
    List<Map<String, String>> infoCuentas;
    List<Map<String, String>> infoContactos;
    List<Map<String, String>> listaCorreosReport;

    public ValidarVinculacionGuardada(DataVinculacion data) {
        infoVinculacion = new HashMap<>();
        infoVinculacion.put(Constantes.MODELO, data.getModelo());
        infoVinculacion.put("actividad", data.getActividadNeg());
        infoVinculacion.put("personasNotificacion", data.getPersonasNotificar());
        infoVinculacion.put("tipoDocumento", data.getTipoDoc());
        infoVinculacion.put("numDocumento", data.getNumDoc());
        infoVinculacion.put("cantCuentas", data.getCantCuentas());
        infoVinculacion.put("tipoCuenta", data.getTipoCta());
        infoVinculacion.put("numCuenta", data.getNumeroCta());
        infoVinculacion.put("numAccionistas", data.getNumAccionistas());
        infoVinculacion.put(Constantes.TIPO_COMISION, data.getTipoComision());
        infoVinculacion.put(Constantes.VALOR_COMISION, data.getValorComision());
        infoVinculacion.put(Constantes.TIENE_EXONERACION, data.getExoneracion());
        infoVinculacion.put("tieneNotificacion", data.getNotificacion());
        infoVinculacion.put("tieneSaldosConsolidados", data.getSaldosConsolidados());
        infoVinculacion.put("tieneReporteTrxDiario", data.getReporteTrxDiario());
        infoVinculacion.put("estructuradoPor", data.getEstructuradoPor());
        infoVinculacion.put("opcionEntrega", data.getOpcionEntrega());
        infoVinculacion.put("cantCorreos", data.getCantCorreos());
        infoVinculacion.put("channel", "Portal Interno QR");

    }

    @Override
    public Object answeredBy(Actor actor) {

        infoVinculacion.put("codigoAsesor", actor.recall("codigoAsesor").toString());
        infoVinculacion.put("razonSocial", actor.recall("razonSocial"));
        infoVinculacion.put("nombreRteLegal", actor.recall("nombreRteLegal"));
        infoVinculacion.put("nombreRespTecnico", actor.recall("nombreRespTecnico"));
        infoVinculacion.put("correoRespTecnico", actor.recall("correoRespTecnico"));
        infoVinculacion.put("celularRespTecnico", actor.recall("celularRespTecnico"));
        infoVinculacion.put("app", actor.recall("app"));

        actor.attemptsTo(Wait.per(7000));
        String fechaDeVisita = actor.recall("fechaVisita");
        List<Map<String, Object>> respuestaBdPostgres = Consulta
                .ejecutar(Query.consultarVinculacion(fechaDeVisita, infoVinculacion.get(Constantes.MODELO)),
                        ConnectionManager.getPostgreSqlConnection());

        boolean infoContacto = false;
        boolean infoNegocio = false;
        boolean infoRteLegal = false;
        boolean infoRespTecnico = false;
        boolean infoComision = false;
        boolean infoSaldosConsolidados = false;
        boolean infoReportEstructura = true;
        boolean infoReportEntrega = true;
        boolean notificacionesYEstado = false;
        boolean infoPlan = false;

        /*se verifican los objetos que pueden crearse en el transcurso de una vinculación,
         * por ejemplo: pueden agregar socios, cuentas, contactos de notificación
         * En el caso de que se seleccione recibir reporteria transaccional diaria con opción Descargable se debe validar los emails destinatarios que reciben el reporte trx
         */
        List<Boolean> valSocios = new ArrayList<>();
        List<Boolean> valCuentas = new ArrayList<>();
        List<Boolean> valContactos = new ArrayList<>();
        List<Boolean> validarCorreosReporteria = new ArrayList<>();

        for (Map<String, Object> iterator : respuestaBdPostgres) {

            infoContacto = iterator.get("creator_adviser_id").equals(infoVinculacion.get("codigoAsesor")) &&
                    iterator.get("channel_name").equals(infoVinculacion.get("channel"));

            infoNegocio = iterator.get("commerce_name").equals(infoVinculacion.get("razonSocial")) &&
                    iterator.get("commerce_activity_name").equals(infoVinculacion.get("actividad")) &&
                    iterator.get("commerce_document_type").equals(infoVinculacion.get("tipoDocumento")) &&
                    iterator.get("commerce_document_number").equals(infoVinculacion.get("numDocumento"));

            infoRteLegal = iterator.get("legal_repr_name").equals(infoVinculacion.get("nombreRteLegal")) &&
                    iterator.get("legal_repr_document_type").equals(infoVinculacion.get("tipoDocumento")) &&
                    iterator.get("legal_repr_document_number").equals(infoVinculacion.get("numDocumento"));

            if ("QR Integrado".equalsIgnoreCase(infoVinculacion.get(Constantes.MODELO)) ||
                "QR Mixto".equalsIgnoreCase(infoVinculacion.get(Constantes.MODELO))) {

                infoRespTecnico = iterator.get("technical_support_name").equals(infoVinculacion.get("nombreRespTecnico")) &&
                        iterator.get("technical_support_email").equals(infoVinculacion.get("correoRespTecnico")) &&
                        iterator.get("technical_support_phone_number").equals(infoVinculacion.get("celularRespTecnico")) &&
                        iterator.get("api_name").equals(infoVinculacion.get("app"));

            } else {
                infoRespTecnico = iterator.get("technical_support_name").equals(infoVinculacion.get("nombreRespTecnico")) &&
                        iterator.get("technical_support_email").equals(infoVinculacion.get("correoRespTecnico")) &&
                        iterator.get("technical_support_phone_number").equals(infoVinculacion.get("celularRespTecnico"));
            }

            if ("F".equalsIgnoreCase(infoVinculacion.get(Constantes.TIPO_COMISION))) {

                if ("Si".equalsIgnoreCase(infoVinculacion.get(Constantes.TIENE_EXONERACION))) {

                    infoComision = iterator.get(Constantes.COMMISSION_TYPE).equals(infoVinculacion.get(Constantes.TIPO_COMISION)) &&
                            Float.valueOf(iterator.get("commission_value_fixed").toString()).compareTo(
                                    Float.valueOf(infoVinculacion.get(Constantes.VALOR_COMISION).toString())) == 0 &&
                            "true".equals(iterator.get(Constantes.EXONERATION_BD).toString());
                } else {
                    infoComision = iterator.get(Constantes.COMMISSION_TYPE).equals(infoVinculacion.get(Constantes.TIPO_COMISION)) &&
                            Float.valueOf(iterator.get("commission_value_fixed").toString()).compareTo(
                                    Float.valueOf(infoVinculacion.get(Constantes.VALOR_COMISION).toString())) == 0 &&
                            "false".equals(iterator.get(Constantes.EXONERATION_BD).toString());
                }

            } else if ("P".equalsIgnoreCase(infoVinculacion.get(Constantes.TIPO_COMISION))) {
                if ("Si".equalsIgnoreCase(infoVinculacion.get(Constantes.TIENE_EXONERACION))) {
                    infoComision = iterator.get(Constantes.COMMISSION_TYPE).equals(infoVinculacion.get(Constantes.TIPO_COMISION)) &&
                            iterator.get("commission_value_percentage").toString().equals(infoVinculacion.get(Constantes.VALOR_COMISION)) &&
                            "true".equals(iterator.get(Constantes.EXONERATION_BD).toString());
                } else {
                    infoComision = iterator.get(Constantes.COMMISSION_TYPE).equals(infoVinculacion.get(Constantes.TIPO_COMISION)) &&
                            iterator.get("commission_value_percentage").toString().equalsIgnoreCase(infoVinculacion.get(Constantes.VALOR_COMISION)) &&
                            "false".equalsIgnoreCase(iterator.get(Constantes.EXONERATION_BD).toString());
                }

            } else if ("S".equalsIgnoreCase(infoVinculacion.get(Constantes.TIPO_COMISION))) {

                if ("Si".equalsIgnoreCase(infoVinculacion.get(Constantes.TIENE_EXONERACION))) {
                    infoComision = iterator.get(Constantes.COMMISSION_TYPE).equals(infoVinculacion.get(Constantes.TIPO_COMISION)) &&
                            "true".equals(iterator.get(Constantes.EXONERATION_BD).toString());
                } else {
                    infoComision = iterator.get(Constantes.COMMISSION_TYPE).equals(infoVinculacion.get(Constantes.TIPO_COMISION)) &&
                            "false".equals(iterator.get(Constantes.EXONERATION_BD).toString());
                }
            }

            System.out.println("se imprime por aqui form comision"+ infoComision);

            if ("Si".equalsIgnoreCase(infoVinculacion.get("tieneSaldosConsolidados"))) {
                infoSaldosConsolidados = "true".equals(iterator.get("consolidated_daily_account").toString());
            } else {
                infoSaldosConsolidados = "false".equals(iterator.get("consolidated_daily_account").toString());
            }

            System.out.println("se imprime por aqui form saldos consolidados"+ infoSaldosConsolidados);

            if ("Si".equalsIgnoreCase(infoVinculacion.get("tieneReporteTrxDiario"))) {
                if ("documento titular".equalsIgnoreCase(infoVinculacion.get("estructuradoPor"))) {

                    infoReportEstructura = "true".equals(iterator.get("daily_transactional_report").toString()) &&
                            "false".equals(iterator.get("account_transactional_report").toString());

                } else if ("documento titular y cuenta".equalsIgnoreCase(infoVinculacion.get("estructuradoPor"))) {

                    infoReportEstructura = "true".equals(iterator.get("daily_transactional_report").toString()) &&
                            "true".equals(iterator.get("account_transactional_report").toString());
                }
                System.out.println("se imprime por aqui form report estructura"+ infoReportEstructura);

                if ("SFTP".equalsIgnoreCase(infoVinculacion.get("opcionEntrega"))) {
                    infoReportEntrega = "true".equals(iterator.get("file_transfer").toString()) &&
                            "false".equals(iterator.get("email_transfer").toString());

                } else if ("Descargable".equalsIgnoreCase(infoVinculacion.get("opcionEntrega"))) {
                    infoReportEntrega = "false".equals(iterator.get("file_transfer").toString()) &&
                            "true".equals(iterator.get("email_transfer").toString());

                    List<Map<String, Object>> listaCorreosBd = Consulta
                            .ejecutar(Query.consultarEmailReporteriaVinculacion(iterator.get("id_vinculation").toString()),
                                    ConnectionManager.getPostgreSqlConnection());

                    listaCorreosReport = actor.recall("listaCorreos");

                    if (!infoVinculacion.get("cantCorreos").equals("") && infoVinculacion.get("cantCorreos") != null) {

                        for (int i = 0; i < listaCorreosBd.size(); i++) {
                            validarCorreosReporteria.add(listaCorreosBd.get(i).get("email").equals(listaCorreosReport.get(i).get("email")));
                        }
                    }
                }
            } else {
                infoReportEstructura = "false".equals(iterator.get("daily_transactional_report").toString());
            }

            if ("Si".equalsIgnoreCase(infoVinculacion.get("tieneNotificacion"))) {

                notificacionesYEstado = "true".equals(iterator.get("direct_notification").toString()) &&
                        !iterator.get("url_notification").toString().isEmpty() &&
                        "FINALIZADO".equalsIgnoreCase(iterator.get("vinculation_status").toString()) &&
                        !iterator.get("terms_and_conditions").toString().isEmpty() &&
                        !iterator.get("created_at").toString().isEmpty() &&
                        !iterator.get("updated_at").toString().isEmpty() &&
                        !iterator.get("channel_qr").toString().isEmpty();

            } else {
                notificacionesYEstado = "false".equals(iterator.get("direct_notification").toString()) &&
                        iterator.get("url_notification") == null &&
                        "FINALIZADO".equalsIgnoreCase(iterator.get("vinculation_status").toString()) &&
                        !iterator.get("terms_and_conditions").toString().isEmpty() &&
                        !iterator.get("created_at").toString().isEmpty() &&
                        !iterator.get("updated_at").toString().isEmpty() &&
                        !iterator.get("channel_qr").toString().isEmpty();
            }

            System.out.println("se imprime por aqui form notificacion y estado"+ notificacionesYEstado);

            if ("QR Estático".equalsIgnoreCase(infoVinculacion.get(Constantes.MODELO))) {
                infoPlan = "Plan Avanzado".equalsIgnoreCase(iterator.get(Constantes.TO_PLAN).toString()) &&
                        "Modelo QR Estatico".equalsIgnoreCase(iterator.get(Constantes.TO_MODEL).toString());
            } else if ("QR Integrado".equalsIgnoreCase(infoVinculacion.get(Constantes.MODELO))) {
                infoPlan = "Plan Avanzado".equalsIgnoreCase(iterator.get(Constantes.TO_PLAN).toString()) &&
                        "Modelo QR Integrado".equalsIgnoreCase(iterator.get(Constantes.TO_MODEL).toString());
            } else if ("QR Mixto".equalsIgnoreCase(infoVinculacion.get(Constantes.MODELO))) {
                infoPlan = "Plan Avanzado".equalsIgnoreCase(iterator.get(Constantes.TO_PLAN).toString()) &&
                        "Modelo QR Mixto".equalsIgnoreCase(iterator.get(Constantes.TO_MODEL).toString());
            }

            if (!"".equalsIgnoreCase(infoVinculacion.get("numAccionistas")) || infoVinculacion.get("numAccionistas") != null) {
                valSocios = validarSocios(actor, iterator.get("id_vinculation").toString());
            }

            if (!"".equalsIgnoreCase(infoVinculacion.get("cantCuentas")) || infoVinculacion.get("cantCuentas") != null) {
                valCuentas = validarCuentas(actor, iterator.get("id_vinculation").toString());
            }

            if (!"".equalsIgnoreCase(infoVinculacion.get("personasNotificacion")) || infoVinculacion.get("cantCuentas") != null) {
                valContactos = validarContactos(actor, iterator.get("id_vinculation").toString());
            }
        }

        return infoContacto && infoNegocio && infoRteLegal &&
                infoRespTecnico && infoComision && infoSaldosConsolidados &&
                infoReportEstructura && infoReportEntrega && !validarCorreosReporteria.contains(false) && notificacionesYEstado && infoPlan &&
                !valSocios.contains(false) && !valCuentas.contains(false) && !valContactos.contains(false);
    }


    public void agregarCamposMapa(Actor actor) {
        infoVinculacion.put("codigoAsesor", actor.recall("codigoAsesor").toString());
        infoVinculacion.put("razonSocial", actor.recall("razonSocial"));
        infoVinculacion.put("nombreRteLegal", actor.recall("nombreRteLegal"));
        infoVinculacion.put("nombreRespTecnico", actor.recall("nombreRespTecnico"));
        infoVinculacion.put("correoRespTecnico", actor.recall("correoRespTecnico"));
        infoVinculacion.put("celularRespTecnico", actor.recall("celularRespTecnico"));
        infoVinculacion.put("app", actor.recall("app"));
    }

    public List<Boolean> validarSocios(Actor actor, String idVinculacion) {

        List<Boolean> validarSocios = new ArrayList<>();

        List<Map<String, Object>> listaAccionistasBd = Consulta
                .ejecutar(Query.consultarAccionistasVinculacion(idVinculacion),
                        ConnectionManager.getPostgreSqlConnection());

        infoSocios = actor.recall("listaSocios");
        for (int i = 0; i < listaAccionistasBd.size(); i++) {

            validarSocios.add(listaAccionistasBd.get(i).get("partner_name").equals(infoSocios.get(i).get("nombre")) &&
                    listaAccionistasBd.get(i).get("partner_document_type").equals(infoSocios.get(i).get("tipoDoc")) &&
                    listaAccionistasBd.get(i).get("partner_document_number").toString().equals(infoSocios.get(i).get("numDoc")));
        }
        return validarSocios;
    }

    public List<Boolean> validarCuentas(Actor actor, String idVinculacion) {

        List<Boolean> validarCuentas = new ArrayList<>();
        List<Map<String, Object>> listaCuentasBd = Consulta
                .ejecutar(Query.consultarCuentasVinculacion(idVinculacion),
                        ConnectionManager.getPostgreSqlConnection());

        infoCuentas = actor.recall("listaCuentas");
        for (int i = 0; i < listaCuentasBd.size(); i++) {
            validarCuentas.add(listaCuentasBd.get(i).get("account_type").equals(infoCuentas.get(i).get("tipoCuenta")) &&
                    listaCuentasBd.get(i).get("account_number").equals(infoCuentas.get(i).get("numCuenta")));
        }
        return validarCuentas;
    }

    public List<Boolean> validarContactos(Actor actor, String idVinculacion) {

        List<Boolean> validarContactosNotificacion = new ArrayList<>();
        List<Map<String, Object>> listaContactosBd = Consulta
                .ejecutar(Query.consultarContactosNotificacion(idVinculacion),
                        ConnectionManager.getPostgreSqlConnection());

        infoContactos = actor.recall("listaContactos");

        for (int i = 0; i < listaContactosBd.size(); i++) {

            if (listaContactosBd.get(i).get("contact_name") != null) {
                validarContactosNotificacion.add(listaContactosBd.get(i).get("contact_name").equals(infoContactos.get(i).get("nombre")) &&
                        listaContactosBd.get(i).get("contact_email").equals(infoContactos.get(i).get("email")) &&
                        listaContactosBd.get(i).get("contact_phone_number").equals(infoContactos.get(i).get("celular")));
            } else {
                validarContactosNotificacion.add(listaContactosBd.get(i).get("contact_email").equals(infoContactos.get(i).get("email")));
            }
        }
        return validarContactosNotificacion;
    }

    public static ValidarVinculacionGuardada con(DataVinculacion data) {
        return new ValidarVinculacionGuardada(data);
    }
}
