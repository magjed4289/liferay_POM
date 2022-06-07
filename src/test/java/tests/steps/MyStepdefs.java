package tests.steps;

import io.cucumber.java.en.Given;
import tests.pages.LoginModal;
import tests.utils.ConfigFileReader;

public class MyStepdefs {

    private final LoginModal loginPage = new LoginModal();
    private final ConfigFileReader reader = ConfigFileReader.getInstance();


    @Given("on login page")
    public void assertLogged() {
        loginPage.goToSignIn();
        try {
            Thread.sleep(70000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        loginPage.editUsername(reader.getUsername())
                 .editPassword(reader.getPassword())
                 .submitLogin();
    }
}
