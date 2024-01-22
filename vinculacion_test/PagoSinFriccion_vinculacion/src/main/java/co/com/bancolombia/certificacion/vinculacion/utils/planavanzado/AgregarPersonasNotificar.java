package co.com.bancolombia.certificacion.vinculacion.utils.planavanzado;

import co.com.bancolombia.certificacion.vinculacion.utils.Constantes;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AgregarPersonasNotificar {

    private static List<Map<String, String>> infoContactos;

    private AgregarPersonasNotificar() {
    }

    public static void con(Actor actor, String cantidadPersonas,
                           Target nombre, Target celular, Target email, Target emailSec1,
                           Target emailSec2, Target emailSec3, Target emailSec4,
                           Target btnAgregarPersona, Target enlaceAgregarPersona, Target btnConfirmarAnadirPersona) {
        infoContactos = new ArrayList<>();
        if("1".equals(cantidadPersonas)) {
            ingresarInfoPersona(actor, nombre, celular, email, Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL);
            adicionarContactoPcipalAMap(Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL);
        } else if("2".equals(cantidadPersonas)) {
            ingresarInfoPersona(actor, nombre, celular, email, Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL);
            adicionarContactoPcipalAMap(Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL);
            actor.attemptsTo(Click.on(btnAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec1, Constantes.EMAIL_ADICIONAL_1);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_1);
            actor.attemptsTo(Click.on(btnConfirmarAnadirPersona));
        } else if("3".equals(cantidadPersonas)) {
            ingresarInfoPersona(actor, nombre, celular, email, Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL);
            adicionarContactoPcipalAMap(Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL);
            actor.attemptsTo(Click.on(btnAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec1, Constantes.EMAIL_ADICIONAL_1);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_1);
            actor.attemptsTo(Click.on(enlaceAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec2, Constantes.EMAIL_ADICIONAL_2);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_2);
            actor.attemptsTo(Click.on(btnConfirmarAnadirPersona));
        } else if("4".equals(cantidadPersonas)) {
            ingresarInfoPersona(actor, nombre, celular, email, Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL);
            adicionarContactoPcipalAMap(Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL);
            actor.attemptsTo(Click.on(btnAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec1, Constantes.EMAIL_ADICIONAL_1);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_1);
            actor.attemptsTo(Click.on(enlaceAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec2, Constantes.EMAIL_ADICIONAL_2);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_2);
            actor.attemptsTo(
                    Click.on(btnConfirmarAnadirPersona),
                    WaitUntil.the(btnAgregarPersona, WebElementStateMatchers.isVisible()).forNoMoreThan(15).seconds(),
                    Click.on(btnAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec1, Constantes.EMAIL_ADICIONAL_3);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_3);
            actor.attemptsTo(Click.on(btnConfirmarAnadirPersona));
        } else if("5".equals(cantidadPersonas)) {

            ingresarInfoPersona(actor, nombre, celular, email, Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL);
            adicionarContactoPcipalAMap(Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL);
            actor.attemptsTo(Click.on(btnAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec1, Constantes.EMAIL_ADICIONAL_1);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_1);
            actor.attemptsTo(Click.on(enlaceAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec2, Constantes.EMAIL_ADICIONAL_2);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_2);
            actor.attemptsTo(Click.on(enlaceAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec3, Constantes.EMAIL_ADICIONAL_3);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_3);
            actor.attemptsTo(Click.on(enlaceAgregarPersona));
            ingresarCorreoAdicional(actor, emailSec4, Constantes.EMAIL_ADICIONAL_4);
            adicionarCorreoAMap(Constantes.EMAIL_ADICIONAL_4);
            actor.attemptsTo(Click.on(btnConfirmarAnadirPersona));
        } else {
            ingresarInfoPersona(actor, nombre, celular, email, Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL);
            adicionarContactoPcipalAMap(Constantes.NOMBRE_CONTACTO_PCIPAL, Constantes.EMAIL_CONTACTO_PCIPAL, Constantes.CELULAR_CONTACTO_PCIPAL);
        }
        actor.remember("listaContactos", infoContactos);
    }

    public static void ingresarInfoPersona(Actor actor, Target nombreTg, Target celularTg, Target emailTg,
                                           String nombre, String celular, String email) {
        actor.attemptsTo(Enter.theValue(nombre).into(nombreTg),
                Enter.theValue(celular).into(celularTg),
                Enter.theValue(email).into(emailTg));
    }

    public static void ingresarCorreoAdicional(Actor actor, Target emailTg, String email) {
        actor.attemptsTo(Enter.theValue(email).into(emailTg));
    }

    public static List<Map<String, String>> adicionarContactoPcipalAMap( String nombre, String email, String celular) {

        Map<String, String> contactoNotificacion = new HashMap<>();
        contactoNotificacion.put("nombre", nombre);
        contactoNotificacion.put("email", email);
        contactoNotificacion.put("celular", celular);
        infoContactos.add(contactoNotificacion);
        return infoContactos;
    }

    public static List<Map<String, String>> adicionarCorreoAMap( String email) {

        Map<String, String> contactoNotificacion = new HashMap<>();
        contactoNotificacion.put("email", email);
        infoContactos.add(contactoNotificacion);
        return infoContactos;
    }

}
