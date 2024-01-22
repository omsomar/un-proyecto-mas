package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.utils.GeneradorArchivosJSON;
import lombok.extern.log4j.Log4j2;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class AgregarCuentas {

    private static List<Map<String, String>> infoCuentas;

    private AgregarCuentas() {
    }

    public static void con(Actor actor, String cantidadCuentas,
                           String primerTipoCuenta,
                           String primerNumCuenta,
                           Target numCuentaPcpal,
                           Target confirmarCuentaPcpal,
                           Target tipoCuentaPcpal,
                           Target opcionAgregar,
                           Target tipoCuenta,
                           Target numCuenta,
                           Target confirmarNumCuenta,
                           Target linkAgregarCuenta,
                           Target btnAgregarCuenta,
                           By listCuentasAdicionales
    ) {

        infoCuentas = new ArrayList<>();

        if("1".equals(cantidadCuentas)) {
            agregarCuentaPcipal(actor, tipoCuentaPcpal, numCuentaPcpal, confirmarCuentaPcpal, primerTipoCuenta, primerNumCuenta);
            adicionarCuentaAMap(primerTipoCuenta, primerNumCuenta);
        } else if("2".equals(cantidadCuentas)) {
            agregarCuentaPcipal(actor, tipoCuentaPcpal, numCuentaPcpal, confirmarCuentaPcpal, primerTipoCuenta, primerNumCuenta);
            adicionarCuentaAMap(primerTipoCuenta, primerNumCuenta);
            actor.attemptsTo(Click.on(opcionAgregar));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "S", getCuentasAdicionales().get(1));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(1));
            adicionarCuentaAMap("S", getCuentasAdicionales().get(1));
            actor.attemptsTo(Click.on(btnAgregarCuenta));
        } else if("3".equals(cantidadCuentas)) {
            agregarCuentaPcipal(actor, tipoCuentaPcpal, numCuentaPcpal, confirmarCuentaPcpal, primerTipoCuenta, primerNumCuenta);
            adicionarCuentaAMap(primerTipoCuenta, primerNumCuenta);
            actor.attemptsTo(Click.on(opcionAgregar));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "S", getCuentasAdicionales().get(1));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(1));
            adicionarCuentaAMap("S", getCuentasAdicionales().get(1));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "D", getCuentasAdicionales().get(2));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(2));
            adicionarCuentaAMap("D", getCuentasAdicionales().get(2));
            actor.attemptsTo(Click.on(btnAgregarCuenta));
        } else if("4".equals(cantidadCuentas)) {
            agregarCuentaPcipal(actor, tipoCuentaPcpal, numCuentaPcpal, confirmarCuentaPcpal, primerTipoCuenta, primerNumCuenta);
            adicionarCuentaAMap( primerTipoCuenta, primerNumCuenta);
            actor.attemptsTo(Click.on(opcionAgregar));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "S", getCuentasAdicionales().get(1));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(1));
            adicionarCuentaAMap("S", getCuentasAdicionales().get(1));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "D", getCuentasAdicionales().get(2));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(2));
            adicionarCuentaAMap("D", getCuentasAdicionales().get(2));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "D", getCuentasAdicionales().get(3));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(3));
            adicionarCuentaAMap( "D", getCuentasAdicionales().get(3));
            actor.attemptsTo(Click.on(btnAgregarCuenta));
        } else if("5".equals(cantidadCuentas)) {
            agregarCuentaPcipal(actor, tipoCuentaPcpal, numCuentaPcpal, confirmarCuentaPcpal, primerTipoCuenta, primerNumCuenta);
            adicionarCuentaAMap( primerTipoCuenta, primerNumCuenta);
            actor.attemptsTo(Click.on(opcionAgregar));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "S", getCuentasAdicionales().get(1));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(1));
            adicionarCuentaAMap("S", getCuentasAdicionales().get(1));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "D", getCuentasAdicionales().get(2));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(2));
            adicionarCuentaAMap("D", getCuentasAdicionales().get(2));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "D", getCuentasAdicionales().get(3));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(3));
            adicionarCuentaAMap("D", getCuentasAdicionales().get(3));
            agregarCuenta(actor, tipoCuenta, numCuenta, confirmarNumCuenta, linkAgregarCuenta, "S", getCuentasAdicionales().get(4));
            seAgregoCuenta(actor, listCuentasAdicionales, getCuentasAdicionales().get(4));
            adicionarCuentaAMap("S", getCuentasAdicionales().get(4));
            actor.attemptsTo(Click.on(btnAgregarCuenta));
        }else{
            agregarCuentaPcipal(actor, tipoCuentaPcpal, numCuentaPcpal, confirmarCuentaPcpal, primerTipoCuenta, primerNumCuenta);
            adicionarCuentaAMap(primerTipoCuenta, primerNumCuenta);
        }
        actor.remember("listaCuentas", infoCuentas);
    }

    public static Map<Integer, String> getCuentasAdicionales() {

        Map<Integer, String> cuentasAdicionales = new HashMap<>();
        try {
            cuentasAdicionales = GeneradorArchivosJSON.readFileToMap("cuentas.json");
        } catch (IOException e) {
            log.error("error leyendo el archivo json", e);
        }
        return cuentasAdicionales;
    }


    private static void agregarCuentaPcipal(Actor actor,
                                            Target tipoCuenta,
                                            Target numCuenta,
                                            Target confirmarNumCuenta,
                                            String valorTipoCuenta,
                                            String valorNumCuenta) {
        actor.attemptsTo(
                SelectFromOptions.byValue(valorTipoCuenta).from(tipoCuenta),
                Enter.theValue(valorNumCuenta).into(numCuenta),
                Enter.theValue(valorNumCuenta).into(confirmarNumCuenta)
        );
    }

    private static void agregarCuenta(Actor actor,
                                      Target tipoCuenta,
                                      Target numCuenta,
                                      Target confirmarNumCuenta,
                                      Target linkAgregarCuenta,
                                      String valorTipoCuenta,
                                      String valorNumCuenta) {
        actor.attemptsTo(
                SelectFromOptions.byValue(valorTipoCuenta).from(tipoCuenta),
                Enter.theValue(valorNumCuenta).into(numCuenta),
                Enter.theValue(valorNumCuenta).into(confirmarNumCuenta),
                WaitUntil.the(linkAgregarCuenta, WebElementStateMatchers.isClickable()).forNoMoreThan(15).seconds(),
                Click.on(linkAgregarCuenta),
                WaitUntil.the(tipoCuenta, WebElementStateMatchers.isNotSelected())
        );
    }

    /***
     * Este método se utiliza para verificar que la cuenta que fue adicionada si se está listando en el front
     * al cerrar la modal que permite añadir cuentas
     * @param actor
     * @param listCuentas Es el localizador que contiene las cuentas adicionadas
     * @param valorNumCuenta Es el num de cuenta que se adicionó recientemente
     */
    public static void seAgregoCuenta(Actor actor, By listCuentas, String valorNumCuenta) {

        List<WebElement> cuentas = BrowseTheWeb.as(actor).getDriver().findElements(listCuentas);
        for (WebElement cuenta : cuentas) {
            String cuentaAdicional = cuenta.getText().replace("-", "");
            if (cuentaAdicional.equalsIgnoreCase(valorNumCuenta)) {
                actor.attemptsTo(Ensure.that(cuentaAdicional).isEqualTo(valorNumCuenta));
                break;
            }
        }
    }

    public static List<Map<String, String>> adicionarCuentaAMap(String tipoCuenta, String numCuenta) {

        Map<String, String> cuenta = new HashMap<>();
        cuenta.put("tipoCuenta", tipoCuenta);
        cuenta.put("numCuenta", numCuenta);
        infoCuentas.add(cuenta);

        return infoCuentas;
    }
}

