package co.com.bancolombia.certificacion.infologtransaccional.questions.listarnotificacion;

import co.com.bancolombia.certificacion.infologtransaccional.integrations.Query;
import co.com.bancolombia.certificacion.infologtransaccional.interactions.Wait;
import co.com.bancolombia.certificacion.infologtransaccional.models.listarnotificacion.FiltrosListar;
import co.com.bancolombia.certificacion.infologtransaccional.utils.conexiondb.ConnectionManager;
import co.com.bancolombia.certificacion.infologtransaccional.utils.listarnotificacion.ListarInfoNotificacion;
import co.com.bancolombia.conexion.utilidades.consults.Consulta;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;
import java.util.Map;

public class ValidarInformacion implements Question<Boolean> {

    private String escenario;
    private String paginationSize;
    private String tipoDocumento;
    private String numDocumento;
    private String tipoCuenta;
    private String numCuenta;
    private String fechaInicio;
    private String fechaFinal;

    public ValidarInformacion(FiltrosListar filtros) {
        this.escenario = filtros.getEscenario();
        this.paginationSize = filtros.getPaginationSize();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        /*  Se recuperan las variables de sesión guardadas al realizar la solicitud al servicio
        Listar notificaciones
        * */
        tipoDocumento = actor.recall("tipoDoc");
        numDocumento = actor.recall("numDoc");
        tipoCuenta = actor.recall("tipoCuenta");
        numCuenta = actor.recall("numCuenta");
        fechaInicio = actor.recall("fechaInicio");
        fechaFinal = actor.recall("fechaFin");

        /*  Se obtiene respuesta del servicio listar notificaciones
         */
        List<Map<String, Object>> respuesta = SerenityRest.lastResponse().getBody().jsonPath().getList("data");

        /*  Se realiza consulta a la base de datos de acuerdo a los filtros seleccionados para la consulta
         */
        List<Map<String, Object>> respuestaBd = obtenerConsultaBd(actor);
        /*        Se realiza la verificación de que la información arrojada por el servicio coincide
        con la información devuelta por la consulta de la bd
        Validar qr, comprobante, documento pagador, id venta comercio,cod respuesta notificacion
         */
        List<Map<String, String>> listInfoServicio = ListarInfoNotificacion.obtenerListInfo(respuesta, "qrInfo", "idQR", "transferVoucher", "transferDate", "clientInformation",
                "documentNumber", "documentType","accountNumber","accountType", "commerceInvoiceId", "responseNotification", "code", "destinationAccountType", "destinationDocumentNumber",
                "destinationDocumentType", "urlNotification", "destinationAccountNumber");
        List<Map<String, String>> listInfoBd = ListarInfoNotificacion.obtenerListInfoBd(respuestaBd, "tracking_number", "date", "qr_id", "info_trx", "origin", "document","product","type",
                "documentNumber", "documentType","number", "qrDetail", "purchaseIntention", "commerceInvoiceId", "response_code", "destination_document_type",
                "destination_document_number", "destination_account_type", "destination_account_number","notificationUrl");
        /* Se compara los resultados obtenidos en el servicio y los resultados arrojados para la consulta realizada en base de datos sea la misma
         */
        Boolean resultado = listInfoBd.containsAll(listInfoServicio);
        Boolean resultado2 = ListarInfoNotificacion.obtenerInfoServicio(respuesta, "transferDate", "transferAmount", "clientInformation", "accountNumber", "documentType", "customerLoyaltyId", "destinationAccountNumber", "transferReference", "transferCodeResponse");
        return resultado && resultado2;
    }

    public List<Map<String, Object>> obtenerConsultaBd(Actor actor) {
        actor.attemptsTo(Wait.per(4000));
        return Consulta.ejecutar(Query.consulNotificacionTodosLosCampos(
                this.tipoDocumento,
                this.numDocumento,
                this.tipoCuenta,
                this.numCuenta,
                this.fechaInicio,
                this.fechaFinal,
                this.escenario,
                this.paginationSize),
                ConnectionManager.getPostgreSqlConnection());
    }

    public static ValidarInformacion notificaciones(FiltrosListar filtros) {
        return new ValidarInformacion(filtros);
    }
}
