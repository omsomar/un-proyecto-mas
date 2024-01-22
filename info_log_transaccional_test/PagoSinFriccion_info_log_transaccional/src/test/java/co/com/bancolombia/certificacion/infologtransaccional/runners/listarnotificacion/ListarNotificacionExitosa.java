package co.com.bancolombia.certificacion.infologtransaccional.runners.listarnotificacion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/listarnotificaciones/listar_notificacion_exitosa.feature"
        , glue = "co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class ListarNotificacionExitosa {
}
