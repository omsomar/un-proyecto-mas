package co.com.bancolombia.certificacion.listarvinculacion.runners.modificarvinculacion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/modificarvinculacion/mvc_modi_vinculacion_inexistente.feature"
        , glue = "co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)
public class MvcVinculacionInexiste {
}
