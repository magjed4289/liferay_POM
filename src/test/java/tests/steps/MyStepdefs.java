package tests.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import tests.pages.*;
import tests.utils.ConfigFileReader;

public class MyStepdefs {

    private final LoginModal loginModal = new LoginModal();
    private final NavBarNotLogged navBarUnlogged = new NavBarNotLogged();
    private final NavBarLogged navBarLogged = new NavBarLogged();
    private final SidebarMenu sidebarMenu = new SidebarMenu();
    private final PagesPortlet pagesPortlet = new PagesPortlet();
    private final ConfigFileReader reader = ConfigFileReader.getInstance();


    @Given("I am logged in")
    public void assertLogged() {
        navBarUnlogged.goToLoginModal().signIn(reader.getUsername(),reader.getPassword());
        Assert.assertTrue(navBarLogged.userIconIsVisible());
    }

    @When("I open the sidebar menu")
    public void iOpenTheSidebarMenu() {
        navBarLogged.openSidebarMenu();
    }

    @Then("I am able to navigate to Pages manager")
    public void iAmAbleToNavigateToPagesManager() {
        sidebarMenu.goToPages();
        Assert.assertTrue(pagesPortlet.pagesPortletIsVisible());
    }
}
