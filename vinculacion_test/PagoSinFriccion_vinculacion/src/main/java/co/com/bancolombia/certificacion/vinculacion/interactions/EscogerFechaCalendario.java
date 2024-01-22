package co.com.bancolombia.certificacion.vinculacion.interactions;

import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;

public class EscogerFechaCalendario implements Interaction {

    private Target calendario;
    private Target selectorAnio;
    private Target anio;
    private Target selectorMes;
    private Target mes;
    private Target dia;
    private Target confirmarFecha;
    private String anioFecha;
    private String mesFecha;
    private String diaFecha;

    public EscogerFechaCalendario(Target calendario, Target selectorAnio, Target anio, Target selectorMes, Target mes, Target dia, Target confirmarFecha, String anioFecha, String mesFecha, String diaFecha) {
        this.calendario = calendario;
        this.selectorAnio = selectorAnio;
        this.anio = anio;
        this.selectorMes = selectorMes;
        this.mes = mes;
        this.dia = dia;
        this.confirmarFecha = confirmarFecha;
        this.anioFecha = anioFecha;
        this.mesFecha = mesFecha;
        this.diaFecha = diaFecha;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        /* se selecciona opción para abrir el calendario y el selector del año*/
        actor.attemptsTo(
                WaitUntil.the(calendario, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                Click.on(calendario),
                WaitUntil.the(selectorAnio, WebElementStateMatchers.isVisible()).forNoMoreThan(60).seconds(),
                Click.on(selectorAnio));

        /* se selecciona el año del calendario que coincida con el año enviado por parámetro*/
        List<WebElementFacade> listaAnios = anio.resolveAllFor(actor);
        for (WebElementFacade iterator : listaAnios) {
            Ensure.that(iterator.getAttribute("innerHTML")).isNotEmpty();
            iterator.waitUntilEnabled();
            if (iterator.getAttribute("innerHTML").equalsIgnoreCase(anioFecha) && iterator.isEnabled()) {
                JavascriptExecutor executor = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
                executor.executeScript("arguments[0].click();", iterator);
                break;
            }
        }
        actor.attemptsTo(
                WaitUntil.the(selectorMes, WebElementStateMatchers.isVisible()).forNoMoreThan(40).seconds(),
                Click.on(selectorMes)
        );
        /* se selecciona el mes del calendario que coincida con el mes enviado por parámetro*/
        List<WebElementFacade> listaMes = mes.resolveAllFor(actor);
        for (WebElementFacade iteratorMes : listaMes) {
            String mesEncontrado = iteratorMes.getAttribute("innerHTML");
            Ensure.that(mesEncontrado).isNotEmpty();
            if (mesEncontrado.equalsIgnoreCase(mesFecha)) {
                actor.attemptsTo(Click.on(iteratorMes));
                break;
            }
        }

        actor.attemptsTo(WaitUntil.the(dia, WebElementStateMatchers.isEnabled()).forNoMoreThan(50).seconds());

        /* se selecciona el dia del calendario que coincida con el dia enviado por parámetro*/
        List<WebElementFacade> listaDia = dia.resolveAllFor(actor);
        for (WebElementFacade iteratorDia : listaDia) {
            if (iteratorDia.getAttribute("innerHTML").equalsIgnoreCase(diaFecha)) {
                JavascriptExecutor executor = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
                executor.executeScript("arguments[0].click();", iteratorDia);
                break;
            }
        }

        actor.attemptsTo(
                WaitUntil.the(confirmarFecha, WebElementStateMatchers.isVisible()).forNoMoreThan(50).seconds(),
                Click.on(confirmarFecha));
    }

    public static EscogerFechaCalendario con(Target calendario,
                                             Target selectorAnio,
                                             Target anio,
                                             Target selectorMes,
                                             Target mes,
                                             Target dia,
                                             Target confirmarFecha,
                                             String anioFecha,
                                             String mesFecha,
                                             String diaFecha) {
        return new EscogerFechaCalendario(calendario, selectorAnio, anio, selectorMes, mes, dia, confirmarFecha, anioFecha, mesFecha, diaFecha);
    }
}
