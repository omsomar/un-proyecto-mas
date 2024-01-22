package co.com.bancolombia.certificacion.qrintencioncompra.runners.listarintencion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/listarintencion/paginar_listar_intencion.feature",
        glue = "co.com.bancolombia.certificacion.qrintencioncompra.stepdefinitions",
        snippets = SnippetType.CAMELCASE
)
public class PaginarListarIntenciones {
}
