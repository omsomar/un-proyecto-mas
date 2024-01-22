package co.com.bancolombia.certificacion.vinculacion.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/guardar_vinculacion_exitosa.feature"
        , glue = "co.com.bancolombia.certificacion.vinculacion.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class GuardarVinculacionExitosa {
}
