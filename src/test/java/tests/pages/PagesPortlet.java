package tests.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PagesPortlet extends BasePage{

    @FindBy(id = "_com_liferay_layout_admin_web_portlet_GroupPagesPortlet_fm")
    public WebElement pagesPortlet;

    public boolean pagesPortletIsVisible() {
        waitForElementToBeVisible(pagesPortlet);
        return pagesPortlet.isDisplayed();
    }
}
