package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public abstract class DriverManager {
    public static WebDriver driver;
    public static String baseUrl = "https://www.beymen.com/";
    public static String testBrowser = "chrome";

    public static void setDriver() {

        if (testBrowser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--start-maximized");
            driver = new ChromeDriver(chromeOptions);
            LoggerUtil.logInfo("Tests are running on Chrome");
        } else if (testBrowser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            driver = new FirefoxDriver(firefoxOptions);
            driver.manage().window().fullscreen();
            LoggerUtil.logInfo("Tests are running on Firefox");
        } else {
            LoggerUtil.logError("Error: Unidentified Browser!", new IllegalArgumentException());
        }

    }


}