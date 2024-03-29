package co.com.bancolombia.certificacion.infologtransaccional.runners.getallinfotrx;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/getallinfotrx/get_all_info_log_campos_en_bd.feature"
        , glue = "co.com.bancolombia.certificacion.infologtransaccional.stepdefinitions"
        , snippets = SnippetType.CAMELCASE
)

public class GetAllInfoLogCamposEnBd {
}
