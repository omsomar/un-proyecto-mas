package co.com.bancolombia.certificacion.vinculacion.utils;

import java.io.File;

public class Constantes {

    public static final String RUTA_WEBDRIVER = System.getProperty("user.dir") + "\\src\\test\\resources\\files\\";
    public static final String URL = "https://portal-interno-qr-qa.apps.ambientesbc.com/login-adp";
    public static final String RUTA_ARCHIVOS = System.getProperty("user.dir") + File.separator +
            "src" + File.separator + "test" + File.separator + "resources" + File.separator +
            "datadriven" + File.separator;

    public static final String NOMBRE_ARCHIVO_CAMARA_COMERCIO = "CamaraComercio.pdf";
    public static final String NOMBRE_ARCHIVO_CAMARA = "Camara.pdf";
    public static final String NOMBRE_ARCHIVO_DOCUMENTO_RTE = "Document.pdf";
    public static final String NOMBRE_ARCHIVO_ACUERDO = "Contrato.pdf";

    public static final String POSTGRE_SQ = "postgresql-bd.properties";

    public static final String MENSAJE_VINCULACION_EXITOSA = "¡Vinculación exitosa!";

    public static final String MENSAJE_ERROR_CORREOS = "El correo es invalido";

    public static final String NOMBRE_RTE_LEGAL = "Representante Legal Bancolombia";

    public static final String NOMBRE_ACCIONISTA_1 = "Socio Bancolombia";
    public static final String TIPO_DOC_ACCIONISTA_1 = "CC";
    public static final String NUM_DOC_ACCIONISTA_1 = "114566776";

    public static final String NOMBRE_ACCIONISTA_2 = "Accionista Comercio";
    public static final String TIPO_DOC_ACCIONISTA_2 = "CE";
    public static final String NUM_DOC_ACCIONISTA_2 = "11990233";

    public static final String NOMBRE_ACCIONISTA_3 = "Accionista Dos Bancolombia";
    public static final String TIPO_DOC_ACCIONISTA_3 = "PASS";
    public static final String NUM_DOC_ACCIONISTA_3 = "0009991772";

    public static final String NOMBRE_RESPONSABLE = "Responsable Tecnico Comercio";
    public static final String CORREO_RESPONSABLE = "responsable.comercio@gmail.es";
    public static final String CELULAR_RESPONSABLE = "3167789090";

    public static final String NOMBRE_CONTACTO_PCIPAL = "Calidad Bancolombia";
    public static final String EMAIL_CONTACTO_PCIPAL = "calidad@bancolombia.com";
    public static final String CELULAR_CONTACTO_PCIPAL = "3124456767";

    public static final String EMAIL_ADICIONAL_1 = "cnb@hotmail.com";
    public static final String EMAIL_ADICIONAL_2 = "personas.bancolombia@hotmail.es";
    public static final String EMAIL_ADICIONAL_3 = "perso3.bancolombia@gmail.com";
    public static final String EMAIL_ADICIONAL_4 = "contacto_adicional_1@banco.com.co";

    public static final String EMAIL_REPORT_1 = "sharon@gmail.com";
    public static final String EMAIL_REPORT_2 = "sa@gmail.com";
    public static final String EMAIL_REPORT_3 = "ssrojas@gmail.com";


    public static final String APP = "commerceshop";


    public static final String TO_PLAN = "to_plan";
    public static final String TO_MODEL = "to_model";
    public static final String MODELO = "modelo";

    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String TIPO_COMISION = "tipoComision";
    public static final String VALOR_COMISION = "valorComision";

    public static final String COMMISSION_TYPE = "commission_type";
    public static final String EXONERATION_BD = "exoneration";

    public static final String TIENE_EXONERACION = "tieneExoneracion";
    private Constantes() {

    }
}
