package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SidebarMenu extends BasePage{

    @FindBy( linkText = "Site Builder")
    public WebElement siteBuilder;

    @FindBy(linkText = "Pages")
    public WebElement pages;

    @FindBy(className = "sidenav-menu")
    public WebElement sideNavMenu;

    public void goToPages() {
        waitForElementToBeVisible(sideNavMenu);
        clickOnElement(siteBuilder);
        clickOnElement(pages);
    }
}
