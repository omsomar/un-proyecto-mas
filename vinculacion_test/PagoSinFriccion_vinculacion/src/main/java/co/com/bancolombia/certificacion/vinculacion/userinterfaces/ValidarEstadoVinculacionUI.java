package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ValidarEstadoVinculacionUI {

    /*Mensajes para validación Estado EXITOSO*/
    public static final Target TITULO_RTA_EXITOSA = Target.the("Campo que contiene el mensaje indicando que la validación de información de la vinculación fue exitosa")
            .located(By.xpath("//h1[contains(.,'La validación de la información ha sido exitosa')]"));

    public static final Target DESCRIPCION_RTA_EXITOSA = Target.the("Campo que contiene el mensaje indicando que las cuentas fueron validadas correctamente")
            .located(By.xpath("//p[contains(.,'El servicio estará asociado a la(s) cuenta(s):')]"));

    /*Mensaje para validación Estado EXITOSO CON REVISIÓN
     * Para el estado exitoso con revisión: aplican los target creados para la respuesta exitosa tambien*/
    public static final Target DESCRIPCION_RTA_REVISION = Target.the("Campo que contiene el mensaje indicando que algunas cuentas no fueron válidas")
            .located(By.xpath("//p[contains(.,'Revisar la(s) siguiente(s) cuentas que incumplen los requisitos')]"));

    /*Mensaje para validación Pendiente*/
    public static final Target TITULO_RTA_PENDIENTE = Target.the("Campo que contiene el mensaje indicando que la solicitud está en estado Pendiente")
            .located(By.xpath("//h1[contains(.,'solicitud pendiente')]"));

    public static final Target DESCRIPCION_RTA_PENDIENTE = Target.the("Campo que contiene el mensaje indicando que se deben gestionar los casos encontrados " +
            "para continuar con la solciitud de vinculación")
            .located(By.xpath("//p[contains(.,'Continuar si gestionó')]"));

    /*Mensajes para validacion Estado NO VALIDO*/
    public static final Target TITULO_RTA_NO_VALIDO = Target.the("Campo que contiene el mensaje indicando que la validación de información de la vinculación no fue válida")
            .located(By.xpath("//h1[contains(.,'no válida')]"));

    public static final Target DESCRIPCION_RTA_NO_VALIDO = Target.the("Campo que contiene el mensaje indicando que la validación de información de la vinculación fue exitosa")
            .located(By.xpath("//p[contains(.,'El negocio no cuenta con los requerimientos')]"));

    /*Mensajes para validación Estado Fallida*/
    public static final Target TITULO_RTA_FALLIDA = Target.the("Campo que contiene el mensaje indicando que la validación de información de la vinculación fue fallida")
            .located(By.xpath("//h1[contains(.,'No es posible realizar la solicitud')]"));

    public static final Target DESCRIPCION_RTA_FALLIDA = Target.the("Campo que contiene descripción indicando que la validación de información de la vinculación fue fallida")
            .located(By.xpath("//p[contains(.,'Para reintentar la vinculación revisa el estado')]"));

    /* El botón Continuar aplica para los estados: EXITOSA, EXITOSA CON REVISIÓN, PENDIENTE*/
    public static final Target BTN_CONTINUAR = Target.the("Botón que permite avanzar para completar la vinculación")
            .located(By.xpath("//button[contains(.,'Continuar')]"));

    private ValidarEstadoVinculacionUI() {

    }
}
