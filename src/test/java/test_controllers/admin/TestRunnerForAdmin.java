package test_controllers.admin;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/admin", glue = "test_controllers.admin", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForAdmin {

}
