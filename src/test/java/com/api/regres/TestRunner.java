package com.api.regres;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/Reports"}, features = "src/test/resources/com.api.regres.features")
public class TestRunner {
}
