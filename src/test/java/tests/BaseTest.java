package tests;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import utils.DriverManager;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static utils.RandomUtil.getRandomNumber;


public abstract class BaseTest extends DriverManager {


    @BeforeClass
    public static void beforeTest() {
        setDriver();
        driver.get(baseUrl);
    }

    @AfterClass
    public static void afterTest() {
        if (driver != null)
            driver.quit();
    }


    //helper functions
    public void scrollToTheElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void selectRandomElement(List<WebElement> productList) {

        if (!productList.isEmpty()) {
            WebElement product = productList.get(getRandomNumber(productList.size()));
            scrollToTheElement(product);
            product.click();
        } else {
            throw new RuntimeException("Missing elements for products in Product page");
        }

    }


    public void hoverTheElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    void takeScreenshot(String testMethod, String testClass) {
        Date date = new Date();
        String screenshotsDir = "src/test/resources/ScreenshotsOfDefects/";
        String extension = ".png";
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotsDir + testClass + "/" + testMethod + "_"
                    + date.getTime() + extension));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void highlightElement(WebElement element) {
        // Use JavaScript to change the element's border style to yellow
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = '6px solid yellow'", element);
        // Wait for 500 milliseconds to see the highlighted element
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Use JavaScript to change the element's border style back to its original value
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border = ''", element);
    }
}