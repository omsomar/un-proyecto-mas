package co.com.bancolombia.certificacion.consultarvinculacion.tasks.listarvinculacion;

import co.com.bancolombia.certificacion.consultarvinculacion.models.listarvinculacion.DataServicios;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;

public class ConsumirServicio extends DataServicios implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

    }

    public static ListarVinculacionGet listarVinculacionGet(DataServicios datos) {
        return Tasks.instrumented(ListarVinculacionGet.class, datos);
    }

}
