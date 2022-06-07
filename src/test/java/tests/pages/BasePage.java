package tests.pages;

import lombok.extern.java.Log;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import tests.utils.ConfigFileReader;
import tests.utils.WebDriverFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Log
public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public WebDriverWait longWait;
    public Wait<WebDriver> fluentWait;
    public Actions action;
    public JavascriptExecutor javascript;

    private final ConfigFileReader reader = ConfigFileReader.getInstance();


    public WebDriverFactory factory;

    public BasePage() {

        factory = WebDriverFactory.getInstance();

        driver = factory.getDriver();
        wait = factory.getWait();
        longWait = factory.getLongWait();
        fluentWait = factory.getFluentWait();
        action = factory.getAction();
        javascript = factory.getJavascript();

        PageFactory.initElements(driver, this);

    }

    public void waitSeconds(double seconds) {

        synchronized (driver) {
            try {
                int convertedSeconds = (int) (seconds * 1000);
                driver.wait(convertedSeconds);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void changeBrowserSize(int width, int height) {
        Dimension targetSize = new Dimension(width, height);
        driver.manage().window().setSize(targetSize);
    }

    public List<String> getTextFromWebElements(List<WebElement> webElements) {
        List<String> textFromElements = new ArrayList<String>();
        for (WebElement webElement : webElements) {
            textFromElements.add(webElement.getText());
        }
        return textFromElements;
    }

    public List<String> getTextFromInputElements(List<WebElement> webElements) {
        List<String> textFromElements = new ArrayList<String>();
        for (WebElement webElement : webElements) {
            textFromElements.add(webElement.getAttribute("value"));
        }
        return textFromElements;
    }

    public void waitForInvisibilityOfElement(By by) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void waitForElementToAppear(By locator) {
        WebElement element = null;

        try {

            element = fluentWait.until(new Function<WebDriver, WebElement>() {
                public WebElement apply(WebDriver t) {
                    return driver.findElement(locator);
                }
            });

        } catch (Exception e) {
            log.info("Element has not appeared.");
        }

    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForAllElementsToBeVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public void clickOnElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void confirmCorrectNavigation(String partialURL) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            System.out.println("URL ES " + driver.getCurrentUrl());
            wait.until(ExpectedConditions.urlContains(partialURL));
            Assert.assertTrue(driver.getCurrentUrl(), driver.getCurrentUrl().contains(partialURL));
        } catch (Exception ex) {
            Assert.fail("Something went wrong");
        }
    }

    public void presenceOfElementByCss(String elementName) {
        WebElement element = driver.findElement(By.cssSelector(elementName));
        waitForElementToBeVisible(element);
        Assert.assertTrue(element.isEnabled());
    }

    public void inputStringValue(WebElement element, String stringValue) {
        clickOnElement(element);
        waitSeconds(0.5);
        element.clear();
        waitSeconds(0.5);
        element.sendKeys(stringValue);
    }

    public void selectOption(String optionTxt, WebElement element, List<WebElement> optionsList) {
        boolean found = false;
        clickOnElement(element);
        wait.until(ExpectedConditions.visibilityOfAllElements(optionsList));
        for (WebElement option : optionsList) {
            if (option.getText().equals(optionTxt)) {
                found = true;
                clickOnElement(option);
            }
        }
        Assert.assertTrue("The option " + optionTxt + " has not been found on the list", found);
    }

}
