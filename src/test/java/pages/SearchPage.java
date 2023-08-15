package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
public class SearchPage extends BasePage {

    public By productListSelector = By.xpath("//*[@id='productList']");


    public SearchPage(WebDriver driver) {
        super(driver);
    }
}
