package co.com.bancolombia.certificacion.infologtransaccional.utils;

public class Constantes {

    public static final String URL_BASE = "https://mp-vpn-qa.apps.ambientesbc.com/pago-sin-friccion/" +
            "api/v1/info-transaction-qr";
    public static final String GET_INFO_LOG_TRX = "/get-info-trx";
    public static final String GET_ALL_INFO_LOG_TRX = "/get-all-info-trx";
    public static final String PAGINATION_SIZE = "paginationSize";
    public static final String PAGINATION_KEY = "paginationKey";
    public static final String GET_ALL_INFO = "getAllInfo";


    public static final String SERVICIO_LIST_NOTIFICACIONES = "/get-notification";
    public static final String POSTGRE_SQ = "postgresql-bd.properties";
    public static final String PAG_TOTAL_ELEMENTS = "totalElements";

    private Constantes() {
    }
}
