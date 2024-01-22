package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;


import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AgregarSocios {

    private static List<Map<String, String>> infoSocios;

    private AgregarSocios() {

    }

    public static void paraComercio(Actor actor, String cantSocios,
                                    Target tieneSocios, Target agregarSocio,
                                    Target nombreSocio, Target tipoDocSocio,
                                    Target numDocSocio, Target linkAgregarSocio,
                                    Target btnAgregarSocio) {
        infoSocios = new ArrayList<>();
        if (cantSocios != null && !"".equalsIgnoreCase(cantSocios)) {
            int cantidad = Integer.parseInt(cantSocios);
            if (cantidad > 0) {
                actor.attemptsTo(
                        WaitUntil.the(tieneSocios, WebElementStateMatchers.isVisible()).forNoMoreThan(10).seconds(),
                        Click.on(tieneSocios),
                        WaitUntil.the(agregarSocio, WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
                        Click.on(agregarSocio));

                if (cantidad == 1) {
                    agregarSocio(actor, nombreSocio, tipoDocSocio, numDocSocio, linkAgregarSocio, Constantes.NOMBRE_ACCIONISTA_1, Constantes.TIPO_DOC_ACCIONISTA_1, Constantes.NUM_DOC_ACCIONISTA_1);
                    adicionarSocioAMap(Constantes.NOMBRE_ACCIONISTA_1, Constantes.TIPO_DOC_ACCIONISTA_1, Constantes.NUM_DOC_ACCIONISTA_1);
                    actor.attemptsTo(Click.on(btnAgregarSocio));
                }

                if (cantidad == 2) {
                    agregarSocio(actor, nombreSocio, tipoDocSocio, numDocSocio, linkAgregarSocio, Constantes.NOMBRE_ACCIONISTA_1, Constantes.TIPO_DOC_ACCIONISTA_1, Constantes.NUM_DOC_ACCIONISTA_1);
                    adicionarSocioAMap(Constantes.NOMBRE_ACCIONISTA_1, Constantes.TIPO_DOC_ACCIONISTA_1, Constantes.NUM_DOC_ACCIONISTA_1);
                    agregarSocio(actor, nombreSocio, tipoDocSocio, numDocSocio, linkAgregarSocio, Constantes.NOMBRE_ACCIONISTA_2, Constantes.TIPO_DOC_ACCIONISTA_2, Constantes.NUM_DOC_ACCIONISTA_2);
                    adicionarSocioAMap(Constantes.NOMBRE_ACCIONISTA_2, Constantes.TIPO_DOC_ACCIONISTA_2, Constantes.NUM_DOC_ACCIONISTA_2);
                    actor.attemptsTo(Click.on(btnAgregarSocio));
                }

                if (cantidad == 3) {
                    agregarSocio(actor, nombreSocio, tipoDocSocio, numDocSocio, linkAgregarSocio, Constantes.NOMBRE_ACCIONISTA_1, Constantes.TIPO_DOC_ACCIONISTA_1, Constantes.NUM_DOC_ACCIONISTA_1);
                    adicionarSocioAMap(Constantes.NOMBRE_ACCIONISTA_1, Constantes.TIPO_DOC_ACCIONISTA_1, Constantes.NUM_DOC_ACCIONISTA_1);
                    agregarSocio(actor, nombreSocio, tipoDocSocio, numDocSocio, linkAgregarSocio, Constantes.NOMBRE_ACCIONISTA_2, Constantes.TIPO_DOC_ACCIONISTA_2, Constantes.NUM_DOC_ACCIONISTA_2);
                    adicionarSocioAMap(Constantes.NOMBRE_ACCIONISTA_2, Constantes.TIPO_DOC_ACCIONISTA_2, Constantes.NUM_DOC_ACCIONISTA_2);
                    agregarSocio(actor, nombreSocio, tipoDocSocio, numDocSocio, linkAgregarSocio, Constantes.NOMBRE_ACCIONISTA_3, Constantes.TIPO_DOC_ACCIONISTA_3, Constantes.NUM_DOC_ACCIONISTA_3);
                    adicionarSocioAMap(Constantes.NOMBRE_ACCIONISTA_3, Constantes.TIPO_DOC_ACCIONISTA_3, Constantes.NUM_DOC_ACCIONISTA_3);
                    actor.attemptsTo(Click.on(btnAgregarSocio));
                }
            }
            actor.remember("listaSocios", infoSocios);
        }
    }

    public static void agregarSocio(Actor actor, Target nombreSocio, Target tipoDocSocio,
                                    Target numDocSocio, Target linkAgregarSocio,
                                    String nombre, String tipoDoc, String numDoc) {
        actor.attemptsTo(
                Enter.theValue(nombre).into(nombreSocio),
                SelectFromOptions.byValue(tipoDoc).from(tipoDocSocio),
                Enter.theValue(numDoc).into(numDocSocio),
                WaitUntil.the(linkAgregarSocio, WebElementStateMatchers.isClickable()).forNoMoreThan(10).seconds(),
                Click.on(linkAgregarSocio)
        );

    }

    public static List<Map<String, String>> adicionarSocioAMap(String nombre, String tipoDoc, String numDoc) {

        Map<String, String> socio = new HashMap<>();
        socio.put("nombre", nombre);
        socio.put("tipoDoc", tipoDoc);
        socio.put("numDoc", numDoc);
        infoSocios.add(socio);

        return infoSocios;
    }
}
