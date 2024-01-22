package co.com.bancolombia.certificacion.qrestaticodinamico.utils.constantes;

public class Constantes {

    public static final String URL_BASE = "https://mp-vpn-qa.apps.ambientesbc.com/pago-sin-friccion/" +
            "api/v1/dynamic-static-qr/";
    public static final String URL_INTEGRACION = "https://api-qa.apps.ambientesbc.com/ext/testing/v1/sales-services/servicing/payment-initiation/qr-codes";
    public static final String CLIENT_SECRECT = "M7gM2tR7mY3wP2mG3xV8dN4wE1kY1lI2dG3bL6xR8wU1fX5vB8";
    public static final String CLIENT_ID = "68896986-d80e-454e-b73f-907f214d0504";
    public static final String MESSAGE_ID = "ssrojas";
    public static final String POSTGRE_SQ = "postgresql-bd.properties";
    public static final String CAMPO_CLIENT_SECRET = "x-ibm-client-secret";
    public static final String CAMPO_CLIENT_ID = "X-IBM-Client-Id";
    public static final String CAMPO_MESSAGE_ID = "message-id";

    public static final String CAMPO_INTENCION = "qrIntentionId";
    /*
    se declaran las constantes utilizadas para el servicio de Activar intención de compra
     */
    public static final String REFERENCIA_INTENCION = "Pago de factura con Método QR - Recibido por caja # 7";
    public static final String ID_INTENCION_CORRECTA = "2e850432-a8e6-472b-ac09-6eb9d44e6f12";
    public static final String ESTADO_INTENCION_CREADA = "Pendiente";
    public static final String WITHOUT_QR_ID = "qrId";
    public static final String WITHOUT_AMOUNT = "amount";

    /*
    Se declaran las constantes utilizadas para el servicio de Cancelar intención de compra, cuando se prueba las cancelaciones fallidas
     */
    public static final String CAMPO_ID_INTENCION_JSON = "idIntencion";
    public static final String RAZON_CANCELACION = "razonCancelacion";
    public static final String ID_INTENCION = "idIntencion";
    public static final String ID_INTENCION_INEXISTENTE = "idIntenInexistente";
    public static final String INTENCION_ESTADO_CANCELADO = "intencionCancelada";
    public static final String INTENCION_ESTADO_EXPIRADA = "intencionExpirada";
    public static final String INTENCION_ESTADO_EXITOSA = "intencionExitosa";

    public static final String ID_INTENCION_CANCELADA = "027d03ca-c7c2-4ef5-8d2e-467a97028c51";
    public static final String ID_INTENCION_EXPIRADA = "f5dd1fe0-2b4b-4d53-bbf4-2236560147b9";
    public static final String ID_INTENCION_EXITOSA = "cd3e0dcd-e89b-4457-a0a1-c0c2f8d7c434";

    /*
   Se declaran las constantes utilizada spara el servicio de Cancelar intención de compra, cuando se prueba las cancelaciones exitosas
    */
    public static final String CANCEL_VALOR_ERRADO = "PorValorErrado";
    public static final String CANCEL_CAMBIO_PRODUCTO = "PorCambioProducto";
    public static final String CANCEL_DESCTO_ERRADO = "PorDescuentoErrado";
    public static final String CANCEL_PROPINA_ERRADA = "PorPropinaErrada";
    public static final String CANCEL_CAMBIO_MEDIOPAGO = "PorCambioMedioPago";
    public static final String CANCEL_CLIENTE = "PorCancelacionCliente";
    public static final String ESTADO_INTENCION_CANCELADA = "Cancelado";
    public static final String NOMBRE_ARCHIVO_JSON = "intenciones.json";
    public static final String ESTADO_INTENCION_EXPIRADA = "Expirada";
    public static final String RAZON_EXPIRACION = "Superó el tiempo máximo";

    public static final String CAMPO_ESTADO_INTENCION_BD = "intention_status";


    private Constantes() {
    }
}
