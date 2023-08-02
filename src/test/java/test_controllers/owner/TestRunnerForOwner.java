package test_controllers.owner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/owner", glue = "test_controllers.owner", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunnerForOwner {

}
