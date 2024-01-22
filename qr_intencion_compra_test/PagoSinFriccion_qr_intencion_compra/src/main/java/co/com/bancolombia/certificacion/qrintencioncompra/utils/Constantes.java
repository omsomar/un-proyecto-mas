package co.com.bancolombia.certificacion.qrintencioncompra.utils;

public class Constantes {

    public static final String URI_LISTAR = "https://mp-vpn-qa.apps.ambientesbc.com/pago-sin-friccion/api/v1/intention-list";
    public static final String URI_INTEGRATE = "https://api-qa.apps.ambientesbc.com" +
            "/ext/testing/v1/sales-services/servicing/payment-initiation/qr-codes";
    public static final String SERVICIO_CONSULTAR = "/intention/";
    public static final String SERVICIO_LISTAR = "/get-all";
    public static final String SERVICIO_CONSULTAR_INTEN_POR_QR = "/get-qr";
    public static final String PAGINATION_KEY = "0";
    public static final String FORMATO_FECHA = "hh: mm: ss a dd-MMM-aaaa";
    public static final int NUM_SIN_DATOS_LISTAR = -1;
    public static final String ESTADO = "state";

    public static final String POSTGRE_SQ = "postgresql-bd.properties";

    public static final String CAMPOS_OBLIGATORIOS = "camposObligatorios";
    public static final String TODOS_LOS_CAMPOS = "todosLosCampos";
    public static final String SIN_NUM_FIDELIDAD = "todosSinNumFidelidad";
    public static final String SIN_ID_VENTA_COMERCIO = "todosSinIdVentaComercio";

    public static final String CAMPO_INTENCION = "idIntention";
    public static final String CAMPO_CANTIDAD = "amount";
    public static final String CAMPO_VENTA_COMERCIO = "commerceInvoiceId";
    public static final String CAMPO_NUM_FIDELIDAD = "customerLoyaltyId";
    public static final String CAMPO_BD_INTENCION = "id_intention";
    public static final String CAMPO_BD_VENTA_COMERCIO = "commerce_invoice_id";
    public static final String CAMPO_BD_NUM_FIDELIDAD = "customer_loyalty_id";

    public static final String PAGINATION_SIZE_PAGINACION = "20";

    private Constantes() {
        throw new IllegalStateException("Utility class");
    }

}
