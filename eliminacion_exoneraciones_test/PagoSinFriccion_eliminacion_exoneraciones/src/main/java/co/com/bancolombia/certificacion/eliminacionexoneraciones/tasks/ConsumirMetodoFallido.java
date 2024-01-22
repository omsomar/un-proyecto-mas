package co.com.bancolombia.certificacion.eliminacionexoneraciones.tasks;

import co.com.bancolombia.certificacion.eliminacionexoneraciones.interactions.Post;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.models.CamposEliminarExoneracion;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.constantes.Constantes;
import co.com.bancolombia.certificacion.eliminacionexoneraciones.utils.utilidades.DataRequest;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ConsumirMetodoFallido implements Task {

    private CamposEliminarExoneracion filtro;

    public ConsumirMetodoFallido(CamposEliminarExoneracion filtro){

        this.filtro = filtro;
    }
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(Constantes.RECURSO).with(
                        requestSpecification -> requestSpecification
                        .relaxedHTTPSValidation()
                        .contentType(ContentType.JSON)
                        .header("Authorization",Constantes.SECRETO_AUTHORIZATION)
                                .body(DataRequest.dataListarFallido(filtro))
                )
        );
    }

    public static ConsumirMetodoFallido eliminacion(CamposEliminarExoneracion filtro){
        return Tasks.instrumented(ConsumirMetodoFallido.class, filtro);
    }
}
