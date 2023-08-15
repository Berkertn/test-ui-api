package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ChartPage extends BasePage {
    public By productPriceSelector = By.xpath("//*[@class='m-productPrice__salePrice']");
    public By numberOfProductDropdownSelector = By.xpath("//*[@class='m-basket__quantity']//select");
    public By deleteButtonSelector = By.xpath("//*[@class='m-basket__remove']");
    public By emptyMessageSelector = By.xpath("//*[@id='emtyCart']");

    public ChartPage(WebDriver driver) {
        super(driver);
    }

    public void selectElementInDropDownList(String value) {
        WebElement numberOfProductDropdownElement = driver.findElement(numberOfProductDropdownSelector);
        Select dropDownListElement = new Select(numberOfProductDropdownElement);
        dropDownListElement.selectByValue(value);
    }
}
