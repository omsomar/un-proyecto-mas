package co.com.bancolombia.certificacion.vinculacion.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class CalendarioUI {
    public static final Target SELECTOR_ANIO = Target.the("campo selector de año de la fecha de realización de la vincuación")
            .located(By.xpath("(//div[contains(@class,'year')])[1]"));

    public static final Target SELECTOR_ANIO_FIN = Target.the("campo selector de año de la fecha en la cual se finaliza la exoneración")
            .located(By.xpath("(//div[contains(@class,'year')])[2]"));

    public static final Target ANIO = Target.the("campo año de la fecha de vinculacio")
            .located(By.xpath("//ul[1]/li/span"));

    public static final Target SELECTOR_MES = Target.the("campo selector de año de la fecha de realización de la vincuación")
            .located(By.xpath("(//div[contains(@class,'month')])[1]"));

    public static final Target SELECTOR_MES_FIN = Target.the("campo selector de año de la fecha en la cual se finaliza la exoneración")
            .located(By.xpath("(//div[contains(@class,'month')])[2]"));

    public static final Target MES = Target.the("campo selector del mes de la fecha seleccionada para la vinculación")
            .located(By.xpath("//ul[2]/li/span"));

    public static final Target MES_EXONERACION = Target.the("campo selector del mes de la fecha correspondiente al fin de la exoneracion")
            .located(By.xpath("//ul[6]/li/span"));

    public static final Target DIA = Target.the("opción para escoger el mes en que se realiza la vinculación")
            .located(By.xpath("(//tbody)[1]/tr/td[@data-day]/button"));

    public static final Target DIA_FIN = Target.the("opción para escoger el mes en que finaliza la exoneración")
            .located(By.xpath("(//tbody)[2]/tr/td[@data-day]/button"));

    public static final Target CONFIRMAR_FECHA = Target.the("opción para confirmar la fecha de la vinculación y cerrar el calendario")
            .located(By.xpath("(//button[.='Ok'])[1]"));

    public static final Target CONFIRMAR_FECHA_FIN = Target.the("opción para confirmar la fecha fin del periodo de exoneración y cerrar el calendario")
            .located(By.xpath("(//button[.='Ok'])[2]"));

    private CalendarioUI() {

    }
}
