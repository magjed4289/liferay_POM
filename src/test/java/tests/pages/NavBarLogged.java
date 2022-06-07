package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class NavBarLogged extends BasePage{

    @FindBy(className = "control-menu-nav-item")
    public WebElement productMenuBtn;

    public SidebarMenu openSidebarMenu() {
        clickOnElement(productMenuBtn);
        return new SidebarMenu();
    }

}
