package co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions.modificarvinculacion;

import co.com.bancolombia.certificacion.listarvinculacion.models.DataServicios;
import co.com.bancolombia.certificacion.listarvinculacion.questions.ModificacionBd;
import co.com.bancolombia.certificacion.listarvinculacion.questions.ModificacionTope;
import co.com.bancolombia.certificacion.listarvinculacion.questions.VerificacionCampos;
import cucumber.api.java.es.Entonces;
import net.serenitybdd.screenplay.actors.OnStage;
import org.hamcrest.Matchers;

import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;

public class MvcModVinStepdefinition {

    @Entonces("^el podra ver que el servicio responde con la modificacion exitosa$")
    public void elPodraVerQueElServicioRespondeConLaModificacionExitosa(List<DataServicios> data) {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El servicio responde de manera adecuada" ,
                        response->response.statusCode(200)),
                seeThat(ModificacionBd.modificacionBd(data.get(0).getTipovalidacion()), Matchers.is(true))
        );
    }

    @Entonces("^el podra comprobar el cambio del tope$")
    public void elPodraComprobarElCambioDelTope(List<DataServicios> data) {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El servicio responde de manera adecuada" ,
                        response->response.statusCode(200)),
                seeThat(ModificacionTope.modificacionTope(data.get(0).getCustomStop()), Matchers.is(true))
        );
    }

    @Entonces("^los campos creator_visit_date y creator_adviser_id estan vacios$")
    public void losCamposCreator_visit_dateYCreator_adviser_idSonNull() {
        OnStage.theActorInTheSpotlight().should(
                seeThatResponse("El servicio responde de manera adecuada" ,
                        response->response.statusCode(200)),
                seeThat(VerificacionCampos.verificacionCampos(), Matchers.is(true))
        );

    }
}
