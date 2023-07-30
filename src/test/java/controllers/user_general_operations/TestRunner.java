package controllers.user_general_operations;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features/user_general_operations", glue = "controllers.user_general_operations", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}
