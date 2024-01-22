package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class PaginaRteLegalUI {

    public static final Target NOMBRE_REPRESENTANTE = Target.the("campo para ingresar el nombre completo del representante legal del comercio")
            .located(By.id("fullName"));

    public static final Target TIPO_DOCUMENTO_RTE_LEGAL = Target.the("campo para seleccionar el tipo de documento del representante legal del comercio")
            .located(By.id("documentType"));

    public static final Target NUM_DOCUMENTO_RTE_LEGAL = Target.the("campo para ingresar el número de documento del representante legal del comercio")
            .located(By.id("documentNumber"));

    public static final Target AVANZAR = Target.the("Botón Continuar para avanzar a pantalla validación de info de vinculación")
            .located(By.xpath("(//button[contains(.,'Continuar')])[1]"));

    public static final Target CONFIRMAR_AVANZAR = Target.the("Botón para confirmar datos")
            .located(By.xpath("(//button[contains(.,'Continuar')])[2]"));

    public static final Target CARGAR_ARCHIVO = Target.the("Botón para cargar el archivo correspondiente a la cedula del representante legal del comercio")
            .located(By.xpath("//span[contains(.,'Adjuntar')]"));

    public static final Target ARCHIVO = Target.the("Campo para subir archivo").located(By.cssSelector("input.file"));

    public static final Target ARCHIVO_SUBIDO = Target.the("Campo para confirmar que el archivo cargado, subió exitosamente ")
            .located(By.xpath("(//div[contains(@class,'card-content')]/div/div[contains(.,'pdf')])[1]"));

    public static final Target TIENE_SOCIOS = Target.the("Check para indicar que el comercio tiene socios/accionistas con participación inferior al 5%")
            .located(By.xpath("//label/span[.='Sí']"));

    public static final Target AGREGAR_SOCIO = Target.the("Botón que permite abrir la modal para agregar un nuevo socio")
            .located(By.xpath("//span[contains(.,'Agregar socio')]"));

    public static final Target NOMBRE_SOCIO = Target.the("Campo para ingresar el nombre del nuevo socio")
            .located(By.id("fullNameN"));

    public static final Target TIPO_DOC_SOCIO = Target.the("Campo para seleccionar el tipo de documento del nuevo socio")
            .located(By.id("documentTypeN"));

    public static final Target NUM_DOC_SOCIO = Target.the("Campo para ingresar el número de documento del nuevo socio")
            .located(By.id("documentNumberN"));

    public static final Target LINK_AGREGAR_SOCIO = Target.the("Botón que permite guardar los datos del nuevo socio y limpiar el formulario para ingresar datos de otro socio")
            .located(By.xpath("//span[contains(.,'Añadir otro socio')]"));

    public static final Target BTN_AGREGAR_SOCIO = Target.the("Botón que permite cerrar la modal y asociar los socios al comercio")
            .located(By.xpath("//button[contains(.,'Añadir')]"));

    private PaginaRteLegalUI() {

    }
}
