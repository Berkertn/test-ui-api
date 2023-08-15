package utils;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

import static utils.DateUtil.getCurrentTime;

public class ScreenshotOnFailure extends TestWatcher {

    private final WebDriver driver;

    public ScreenshotOnFailure() {
        driver = DriverManager.driver;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        captureScreenshot(description.getClassName() + "_" + description.getMethodName() + "_failed");
    }

    private void captureScreenshot(String screenshotName) {
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);
            String screenshotsDir = "src/test/resources/ScreenshotsOfDefects/";
            String screenshotPath = screenshotsDir.replace("/", File.separator) + screenshotName + getCurrentTime() + ".png";
            try {
                FileUtils.copyFile(screenshot, new File(screenshotPath));
                System.out.println("Screenshot saved: " + screenshotPath);
            } catch (IOException e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        }
    }
}

