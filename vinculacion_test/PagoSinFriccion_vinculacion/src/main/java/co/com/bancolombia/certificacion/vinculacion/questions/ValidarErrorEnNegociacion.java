package co.com.bancolombia.certificacion.vinculacion.questions;

import co.com.bancolombia.certificacion.vinculacion.userinterfaces.PaginaNegociacionUI;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ValidarErrorEnNegociacion implements Question<Boolean> {
    @Override
    public Boolean answeredBy(Actor actor) {

        return PaginaNegociacionUI.BTN_CONTINUAR.resolveFor(actor).isDisabled();
    }

    public static ValidarErrorEnNegociacion con() {
        return new ValidarErrorEnNegociacion();
    }
}
