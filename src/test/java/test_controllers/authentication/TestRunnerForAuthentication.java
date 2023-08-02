package test_controllers.authentication;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/authentication", glue = "test_controllers.authentication", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForAuthentication {

}
