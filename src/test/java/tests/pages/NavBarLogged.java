package tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBarLogged extends BasePage{

    @FindBy(className = "icon-product-menu-closed")
    public WebElement productMenuClosed;

    @FindBy(className = "icon-product-menu-open")
    public WebElement productMenuOpen;

    @FindBy(className = "lexicon-icon-user")
    public WebElement userIcon;

    public boolean isMenuOpened() {
        waitForElementToBeVisible(productMenuOpen);
        return driver.findElements(By.className("icon-product-menu-open")).size() > 0;
    }

    public SidebarMenu openSidebarMenu() {
        if(!isMenuOpened()){
            clickOnElement(productMenuClosed);
        }
        else {
            waitForElementToBeVisible(productMenuOpen);
            productMenuOpen.isDisplayed();
        }
        return new SidebarMenu();
    }

    public boolean userIconIsVisible() {
        waitForElementToBeVisible(userIcon);
        return userIcon.isDisplayed();
    }

}
