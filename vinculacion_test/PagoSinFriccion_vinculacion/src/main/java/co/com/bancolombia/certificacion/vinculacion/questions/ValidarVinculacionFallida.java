package co.com.bancolombia.certificacion.vinculacion.questions;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.ValidarEstadoVinculacionUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.ensure.Ensure;
import net.serenitybdd.screenplay.questions.Text;

import java.time.Duration;

public class ValidarVinculacionFallida implements Question<Boolean> {

    private String estado;

    public ValidarVinculacionFallida(String estado) {
        this.estado = estado;
    }

    @Override
    public Boolean answeredBy(Actor actor) {

        switch (estado) {
            case "noValida":
                actor.attemptsTo(Ensure.that(ValidarEstadoVinculacionUI.TITULO_RTA_NO_VALIDO.waitingForNoMoreThan(Duration.ofSeconds(40))).text().isNotEmpty());
                return "Solicitud de vinculación no válida".equalsIgnoreCase(Text.of(ValidarEstadoVinculacionUI.TITULO_RTA_NO_VALIDO).viewedBy(actor).asString()) &&
                        Text.of(ValidarEstadoVinculacionUI.DESCRIPCION_RTA_NO_VALIDO).viewedBy(actor).asString().contains("El negocio no cuenta con los requerimientos");
            case "fallida":
                actor.attemptsTo(Ensure.that(ValidarEstadoVinculacionUI.TITULO_RTA_FALLIDA.waitingForNoMoreThan(Duration.ofSeconds(40))).text().isNotEmpty());
                return "No es posible realizar la solicitud".equalsIgnoreCase(Text.of(ValidarEstadoVinculacionUI.TITULO_RTA_FALLIDA).viewedBy(actor).asString()) &&
                        Text.of(ValidarEstadoVinculacionUI.DESCRIPCION_RTA_FALLIDA).viewedBy(actor).asString().contains("Para reintentar la vinculación revisa el estado de la(s) cuenta(s)");
            default:
                break;
        }
        return false;
    }

    public static ValidarVinculacionFallida con(String estado) {
        return new ValidarVinculacionFallida(estado);
    }
}
