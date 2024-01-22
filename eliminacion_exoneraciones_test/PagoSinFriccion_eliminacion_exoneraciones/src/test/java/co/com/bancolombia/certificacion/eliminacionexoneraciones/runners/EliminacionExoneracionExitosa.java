package co.com.bancolombia.certificacion.eliminacionexoneraciones.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/eliminacion_exoneracion_exitosa.feature"
        , glue = "co.com.bancolombia.certificacion.eliminacionexoneraciones.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class EliminacionExoneracionExitosa {
}
