package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavBarNotLogged extends BasePage{

    @FindBy(className = "sign-in")
    public WebElement signInBtn;

    public LoginModal goToLoginModal(){
        clickOnElement(signInBtn);
        return new LoginModal();
    }
}
