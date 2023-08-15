package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ChartPage extends BasePage{
    public By productPriceSelector = By.xpath("//*[@class='m-productPrice__salePrice']");
    public ChartPage(WebDriver driver) {
        super(driver);
    }
}
