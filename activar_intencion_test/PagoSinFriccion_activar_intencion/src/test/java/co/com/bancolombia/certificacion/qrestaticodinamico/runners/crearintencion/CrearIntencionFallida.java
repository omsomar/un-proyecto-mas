package co.com.bancolombia.certificacion.qrestaticodinamico.runners.crearintencion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/crearintencion/crear_intencion_fallida.feature"
        , glue = "co.com.bancolombia.certificacion.qrestaticodinamico.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class CrearIntencionFallida {
}
