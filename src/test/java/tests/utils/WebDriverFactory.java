package tests.utils;

import io.cucumber.java.Scenario;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromiumDriverManager;
import io.github.bonigarcia.wdm.webdriver.WebDriverCreator;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class WebDriverFactory {

    private static WebDriverFactory webDriverFactory;
    private ConfigFileReader configFileReader = ConfigFileReader.getInstance();

    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait longWait;
    private Wait<WebDriver> fluentWait;
    private Actions action;
    private JavascriptExecutor javascript;

    private WebDriverFactory() {

    }

    public static WebDriverFactory getInstance() {
        return webDriverFactory == null ? webDriverFactory = new WebDriverFactory() : webDriverFactory;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public WebDriverWait getLongWait() {
        return longWait;
    }

    public Wait<WebDriver> getFluentWait() {
        return fluentWait;
    }

    public Actions getAction() {
        return action;
    }

    public JavascriptExecutor getJavascript() {
        return javascript;
    }

    public void tearUp() throws MalformedURLException {

        String browser = configFileReader.getBrowser();
        String base_url = configFileReader.getLoginPage();
        String headlessMode = configFileReader.getHeadlessMode();

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().clearDriverCache();
            WebDriverManager.chromedriver().setup();

            ChromeOptions chromeOptions = new ChromeOptions();

            // HEADLESS MODE
            if (headlessMode.equals("headless")) {
                chromeOptions.addArguments("--headless");
                chromeOptions.addArguments("--window-size=1920,1080");
            } else {
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--incognito");

            }

            chromeOptions.addArguments("--no-sandbox", "--ignore-certificate-errors");
            chromeOptions.addArguments("--disable-extensions", "--test-type", "--disable-infobars", "--incognito");
            driver = new ChromeDriver(chromeOptions);

        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().clearDriverCache();
            WebDriverManager.firefoxdriver().driverVersion("v0.23.0").setup();

            FirefoxOptions firefoxOptions = new FirefoxOptions();

            // HEADLESS MODE
            if (headlessMode.equals("headless")) {
                firefoxOptions.setHeadless(true);
            }

            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().maximize();
        }

        wait = new WebDriverWait(driver, 20, 1000);
        longWait = new WebDriverWait(driver, 120, 1000);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(2))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .ignoring(TimeoutException.class)
                .ignoring(StaleElementReferenceException.class);
        action = new Actions(driver);
        javascript = (JavascriptExecutor) driver;
        driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        driver.navigate().to(base_url);
    }

    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png",scenario.getName());
        }
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }

    public void tearDown() {
        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
            driver = null;
        }
    }

    public void waitForVisible(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void clickOnEL(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

}
