package tests.runners;



import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features" }, glue = { "tests.steps" }, plugin = { "pretty",
		"json:target/cucumber-reports/cucumber.json", "html:target/cucumber-reports/report.html",
		"junit:target/cucumber-reports/cucumber.xml"},
		monochrome = true
		//, tags= "@advanced-object-apis"
)
public class RunCucumberTest {
}