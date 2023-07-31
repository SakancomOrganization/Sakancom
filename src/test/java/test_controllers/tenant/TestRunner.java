package test_controllers.tenant;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/tenant", glue = "test_controllers.tenant", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}