package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features",

		glue = { "stepDefinitions", "hooks" },

		plugin = { "pretty", "html:target/CucumberReports.html", "json:target/Cucumber.json" },

		monochrome = true,

		publish = true,

		tags = "@First1")
public class TestRunner extends AbstractTestNGCucumberTests {

}