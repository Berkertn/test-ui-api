package tests;

import org.junit.*;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import pages.*;
import utils.ExcelDataUtil;
import utils.ScreenshotOnFailure;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static utils.RandomUtil.getRandomNumber;

public class BeymenTest extends BaseTest {
    @Rule
    public ScreenshotOnFailure screenshotOnFailure = new ScreenshotOnFailure();

    @Before
    public void beforeTheTestCases() {
        HomePage homePage = new HomePage(driver);
        homePage.waitTime(10);
        homePage.getElement(homePage.cookieSelector).click();
        homePage.waitTime(3);
        homePage.getElement(homePage.genderMaleButtonSelector).click();
    }

    @Test
    public void beymenE2E() {

        ArrayList elementOfProduct = new ArrayList<WebElement>();

        HomePage homePage = new HomePage(driver);
        NavigationComponent navigationComponent = new NavigationComponent(driver);
        Assert.assertEquals(String.format("\nURL doesnt mach with Home Page:\nExpected: %s\nActual: %s", baseUrl, driver.getCurrentUrl()), driver.getCurrentUrl(), baseUrl);
        Assert.assertEquals(String.format("\nPage Title doesnt match with Home Page:\nExpected: %s\nActual: %s", homePage.pageTitle, driver.getTitle()), driver.getTitle(), homePage.pageTitle);

        //searching product
        String excelFilePath = "searchData.xlsx"; // searching data Excel file path
        List<String> searchTexts = ExcelDataUtil.readSearchTexts(excelFilePath);
        WebElement searchBarElement = navigationComponent.getElement(navigationComponent.searchSelector);
        //searchBarElement.sendKeys(searchTexts.get(0));

        searchBarElement.clear();
        searchBarElement.sendKeys(searchTexts.get(1));
        searchBarElement.sendKeys(Keys.ENTER);

        // Select random product
        SearchPage searchPage = new SearchPage(driver);
        selectRandomElement(searchPage.getElements(searchPage.productListSelector));

        //write product detail in txt file
        ProductPage productPage = new ProductPage(driver);
        List<WebElement> sizeInfoElements = productPage.getElements(productPage.productSizeListSelector);
        int randomNumber = getRandomNumber(sizeInfoElements.size());

        elementOfProduct.add(productPage.getElement(productPage.productNameSelector));
        elementOfProduct.add(productPage.getElement(productPage.productPriceSelector));

        sizeInfoElements.get(randomNumber).click();
        elementOfProduct.add(sizeInfoElements.get(randomNumber));
        List<String> productPrice = List.of(productPage.getElement(productPage.productPriceSelector).getText().split(" ")); // assertion value
        productPage.writeProductInfoInAFile(elementOfProduct);

        // add to chart
        productPage.getElement(productPage.addToChartSelector).click();
        Assert.assertTrue(productPage.getElement(productPage.successNotificationSelector).isDisplayed());

        // navigate to the chart
        ChartPage chartPage = new ChartPage(driver);
        productPage.getElement(productPage.goToTheChartButtonSelector).click();
        WebElement forOneProductPrice = chartPage.getElement(chartPage.productPriceSelector);
        List priceActualList = List.of(forOneProductPrice.getText().split(" "));
        System.out.println(priceActualList);
        System.out.println(productPrice);
        //Assertion
        float priceExpected = Float.parseFloat(productPrice.get(0));
        String currencyExpected = productPrice.get(1);

        float priceActual = Float.parseFloat((String) priceActualList.get(0));
        String currencyActual = (String) priceActualList.get(1);
        Assert.assertEquals(priceExpected, priceActual);
        Assert.assertEquals(currencyExpected.toLowerCase().replace(" ", ""), currencyActual.toLowerCase().replace(" ", ""));
        homePage.waitTime();
        Assert.assertEquals(driver.getTitle(), homePage.pageTitle);

    }


}