package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationComponent extends BasePage{
    public By searchSelector = By.xpath("//div[@role='combobox']//input");
    public NavigationComponent(WebDriver driver) {
        super(driver);
    }
}
