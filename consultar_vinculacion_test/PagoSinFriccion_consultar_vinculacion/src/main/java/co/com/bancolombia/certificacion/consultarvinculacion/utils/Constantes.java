package co.com.bancolombia.certificacion.consultarvinculacion.utils;

public class Constantes {

    public static final String URL = "https://mp-vpn-qa.apps.ambientesbc.com/pago-sin-friccion/api/v1/vinculation-list";
    public static final String RECURSO = "/get";
    public static final String RECURSO_LISTAR = "/get-all";
    public static final String POSTGRE_SQ = "postgresql-bd.properties";
    public static final String EXONERATION = "exoneration";
    public static final String FECHA_FILTRO_RANGO = "vin.created_at";


    /*
    Se crean constantes del servicio Listar vinculacion
     */
    public static final String TODOS_LOS_CAMPOS = "todosLosCampos";
    public static final String CAMPOS_OBLIGATORIOS = "camposObligatorios";
    public static final String CAMPOS_INCOMPLETOS = "camposIncompletos";

    public static final String PAGINATION_SIZE = "paginationSize";
    public static final String PAGINATION_KEY = "paginationKey";

    private Constantes() {
    }
}
