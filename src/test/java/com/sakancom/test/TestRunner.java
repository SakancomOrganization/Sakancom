package com.sakancom.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features", glue = "com.sakancom.test", snippets = CucumberOptions.SnippetType.CAMELCASE)
public class TestRunner {

}
