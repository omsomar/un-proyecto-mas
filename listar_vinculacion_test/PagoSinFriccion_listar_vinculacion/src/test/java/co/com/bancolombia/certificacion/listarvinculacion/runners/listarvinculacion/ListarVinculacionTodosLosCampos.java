package co.com.bancolombia.certificacion.listarvinculacion.runners.listarvinculacion;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/listarvinculacion/listar_vinculacion_todos_los_campos.feature"
        , glue = "co.com.bancolombia.certificacion.listarvinculacion.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class ListarVinculacionTodosLosCampos { }
