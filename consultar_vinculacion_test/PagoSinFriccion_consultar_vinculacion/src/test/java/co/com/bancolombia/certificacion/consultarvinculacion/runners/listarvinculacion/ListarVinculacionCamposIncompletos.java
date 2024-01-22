package co.com.bancolombia.certificacion.consultarvinculacion.runners.listarvinculacion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/listarvinculacion/listar_vinculacion_campos_incompletos.feature"
        , glue = "co.com.bancolombia.certificacion.consultarvinculacion.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class ListarVinculacionCamposIncompletos {
}
