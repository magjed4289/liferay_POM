package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NavBarUnlogged extends BasePage{

    @FindBy(className = "sign-in")
    public WebElement signInBtn;

    @FindBy(css = "[name='username']")
    public WebElement usernameField;

    @FindBy(css = "[name='password']")
    public WebElement passwordField;

    @FindBy(css = "[name='login']")
    public WebElement loginBtn;

    @FindBy(css=".message.message-error")
    public WebElement loginErrorAlert;

    public NavBarUnlogged editUsername(String user) {
        usernameField.sendKeys(user);
        return this;
    }

    public NavBarUnlogged editPassword(String pass) {
        passwordField.sendKeys(pass);
        return this;
    }

    public void goToSignIn() {
        signInBtn.click();
    }

    public void submitLogin() {
        loginBtn.click();
    }

    public String getLoginErrorText() {
        wait.until(ExpectedConditions.visibilityOf(loginErrorAlert));
        return loginErrorAlert.getText();
    }

}
