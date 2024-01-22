package co.com.bancolombia.certificacion.consultarvinculacion.runners.consultarvinculacion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/consultarvinculacion/consultar_vinculacion_fallida.feature"
        , glue = "co.com.bancolombia.certificacion.consultarvinculacion.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)
public class ConsultarVinculacionFallida {


}
