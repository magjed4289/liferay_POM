package tests.runners;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.restassured.RestAssured;
import lombok.extern.java.Log;
import tests.utils.ConfigFileReader;
import tests.utils.WebDriverFactory;

import java.net.MalformedURLException;

@Log
public class Hooks {
    private final ConfigFileReader config = ConfigFileReader.getInstance();
    private WebDriverFactory factory = WebDriverFactory.getInstance();


    public Hooks() {
    }

    @Before()
    public void tearUp() throws MalformedURLException {
        factory.tearUp();
    }

    @After()
    public void tearDown(Scenario scenario) {
        factory.tearDown(scenario);
    }
}