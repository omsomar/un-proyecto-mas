package co.com.bancolombia.certificacion.qrintencioncompra.runners.consultarintencion;


import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/consultarintencion/consultas_malas.feature"
        , glue = "co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)
public class ConsultasMalas {
}