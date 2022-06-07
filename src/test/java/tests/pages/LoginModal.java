package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginModal extends BasePage{

    @FindBy(className = "modal-body")
    public WebElement modalBody;

    @FindBy(id = "_com_liferay_login_web_portlet_LoginPortlet_login")
    public WebElement usernameField;

    @FindBy(id = "_com_liferay_login_web_portlet_LoginPortlet_password")
    public WebElement passwordField;

    public void signIn(String username, String password) {
        waitForElementToBeVisible(modalBody);
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        clickOnElement(modalBody.findElement(By.className("btn-primary")));
    }

}
