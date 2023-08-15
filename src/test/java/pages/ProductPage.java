package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utils.FileWriteUtil.writeTxtFile;

public class ProductPage extends BasePage {

    public By productNameSelector = By.xpath("//*[@class='o-productDetail__description']");
    public By productPriceSelector = By.xpath("//*[@id='priceNew']");
    public By productSizeListSelector = By.xpath("//*[@class='m-variation__item']");
    public By addToChartSelector = By.xpath("//*[@id='addBasket']");
    public By successNotificationSelector = By.xpath("//*[@class='m-notification success']");
    public By goToTheChartButtonSelector = By.xpath("//*[@class='m-notification__button btn']");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void writeProductInfoInAFile(List<WebElement> elements) {
        List fileData = new ArrayList<String>();

        fileData.add("Product Name: " + elements.get(0).getText());
        fileData.add("Product Price: " + elements.get(1).getText());
        fileData.add("Product Size: " + elements.get(2).getText());
        writeTxtFile(fileData);
    }
}
