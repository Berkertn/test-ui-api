package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverManager;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {

    protected WebDriver driver;
    public WebDriverWait wait;
    protected String pageURL;
    protected String browserTitle;
    public String pageTitle;
    public By goParentNodeSelector = By.xpath("..");

    // Constructor
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.pageURL = DriverManager.baseUrl;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(12));
    }

    public WebElement getElement(By selector) { // returning element
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector);
    }

    public List<WebElement> getElements(By selector) { // returning element
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElements(selector);
    }

    public void waitTime(int time) {
        wait.withTimeout(Duration.ofSeconds(time));
    }

    public void waitTime() {
        try {
            // Sleep for 5 seconds
            Thread.sleep(5000); // 5000 milliseconds = 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getElementText(WebElement element, By selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return element.findElement(selector).getText();
    }

    public String getElementText(By selector) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
        return driver.findElement(selector).getText();
    }

    public String getDOMProperty(WebElement element,String key) {
        return element.getDomProperty(key);
    }
}

