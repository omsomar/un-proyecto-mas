package co.com.bancolombia.certificacion.vinculacion.userinterfaces;


import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaNegocioUI {

    public static final Target RAZON_SOCIAL = Target.the("campo para ingresar la razón social del comercio")
            .located(By.id("businessName"));

    public static final Target ACTIVIDAD_NEGOCIO = Target.the("campo para seleccionar la actividad del negocio")
            .located(By.id("businessActivity"));

    public static final Target TIPO_DOCUMENTO_NEGOCIO = Target.the("campo para seleccionar el tipo de documento del negocio")
            .located(By.id("businessDocumentType"));

    public static final Target NUM_DOCUMENTO_NEGOCIO = Target.the("campo para ingresar el número de documento del negocio")
            .located(By.id("businessDocumentNumber"));

    public static final Target TIPO_CUENTA = Target.the("campo para seleccionar el tipo de cuenta del comercio")
            .located(By.id("accountType"));

    public static final Target NUMERO_CUENTA = Target.the("campo para ingresar el número de cuenta del comercio")
            .located(By.id("accountNumber"));

    public static final Target CONFIRMAR_NUMERO_CUENTA = Target.the("campo para confirmar el número de cuenta del comercio")
            .located(By.id("accountNumberConfirmation"));

    public static final Target AVANZAR_FORM_RTE_LEGAL = Target.the("Botón Continuar para avanzar al formulario de rte legal")
            .located(By.xpath("(//button[contains(.,'Continuar')])[1]"));

    public static final Target CONFIRMAR_AVANZAR = Target.the("Botón para confirmar datos")
            .located(By.xpath("(//button[contains(.,'Continuar')])[2]"));

    public static final By CARGAR_ARCHIVO = By.xpath("(//div[contains(@class,'gray-text-p external')])[2]");

    public static final Target ARCHIVO = Target.the("campo para subir archivo").located(By.cssSelector("input.file"));

    public static final Target ARCHIVO_SUBIDO = Target.the("campo para validar que el archivo fue subido exitosamente")
            .located(By.xpath("(//div[@class='card-content'])[1]/div/div[1]"));

    public static final Target AÑADIR_CUENTA = Target.the("Botón para abrir modal que permite adicionar más cuentas al comercio")
            .located(By.xpath("//span[contains(.,'nueva cuenta')]"));

    public static final Target TIPO_CUENTA_ADICIONAL = Target.the("campo que permite seleccionar el tipo de cuenta, para la cuenta adicional que se desea agregar al comercio")
            .located(By.id("accountTypeN"));

    public static final Target NUM_CUENTA_ADICIONAL = Target.the("campo que permite ingresar el número de cuenta, para la cuenta adicional que se desea agregar al comercio")
            .located(By.id("accountNumberN"));

    public static final Target CONFIRMAR_NUM_CUENTA_ADICIONAL = Target.the("campo que permite confirmar el número de cuenta, para la cuenta adicional que se desea agregar al comercio")
            .located(By.id("accountNumberConfirmationN"));

    public static final By LIST_CUENTAS_ADICIONALES = By.xpath("//p[@class='gray-text-t']");

    public static final Target LINK_AÑADIR_CUENTA = Target.the("enlace que permite guardar la cuenta adicional con la informaicón diligenciada en el formulario, limpia el formulario para ingresar la información para adicionar otra cuenta  al comercio")
            .located(By.xpath("//span[contains(.,'otra cuenta')]"));

    public static final Target BTN_AÑADIR_CUENTA = Target.the("Cierra la modal que permite adicionar cuentas al comercio y asocia las cuentas adicionales que fueron añadidas")
            .located(By.xpath("//button[contains(.,'Añadir')]"));

    private PaginaNegocioUI() {

    }

}

