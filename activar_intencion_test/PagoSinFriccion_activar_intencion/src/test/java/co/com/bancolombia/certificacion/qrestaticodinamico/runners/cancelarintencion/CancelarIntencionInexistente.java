package co.com.bancolombia.certificacion.qrestaticodinamico.runners.cancelarintencion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/cancelarintencion/cancelar_intencion_inexistente.feature",
        glue = "co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions",
        snippets = SnippetType.CAMELCASE
)
public class CancelarIntencionInexistente {
}
