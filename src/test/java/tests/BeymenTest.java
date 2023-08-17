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
import static utils.AssertionUtil.assertEqualText;
import static utils.AssertionUtil.assertTrueIsDisplayed;
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

        ArrayList<WebElement> elementOfProduct = new ArrayList<>();

        HomePage homePage = new HomePage(driver);
        NavigationComponent navigationComponent = new NavigationComponent(driver);
        assertEqualText(baseUrl, driver.getCurrentUrl());
        assertEqualText(homePage.pageTitle, driver.getTitle());

        //searching product
        String excelFilePath = "searchData.xlsx"; // searching data Excel file path
        List<String> searchTexts = ExcelDataUtil.readSearchTexts(excelFilePath);
        WebElement searchBarElement = navigationComponent.getElement(navigationComponent.searchSelector);
        searchBarElement.sendKeys(searchTexts.get(0));
        clearTheInput(searchBarElement);
        searchBarElement.sendKeys(searchTexts.get(1));
        searchBarElement.sendKeys(Keys.ENTER);
        assertEqualText(searchTexts.get(1), searchBarElement.getDomAttribute("_value"));

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
        List<String> productPrice = List.of(productPage.getElementText(productPage.productPriceSelector).split(" ")); // assertion value
        productPage.writeProductInfoInAFile(elementOfProduct);

        // add to chart
        productPage.getElement(productPage.addToChartSelector).click();
        assertTrueIsDisplayed(productPage.getElement(productPage.successNotificationSelector));

        // navigate to the chart
        ChartPage chartPage = new ChartPage(driver);
        productPage.getElement(productPage.goToTheChartButtonSelector).click();
        String forOneProductPrice = chartPage.getElementText(chartPage.productPriceSelector);
        List<String> priceActualList = List.of(forOneProductPrice.split(" "));

        //Assertion For Price Check
        String currencyExpected = productPrice.get(1);
        String priceExpected = productPrice.get(0);

        String priceActual = priceActualList.get(0).replace(",00", "");
        String currencyActual = priceActualList.get(1);
        assertEqualText(priceExpected, priceActual);
        assertEqualText(currencyExpected.replace(" ", ""), currencyActual.replace(" ", ""));

        // adding one more product
        chartPage.selectElementInDropDownList("2");
        WebElement numberOfProductElement = chartPage.getElement(chartPage.numberOfProductDropdownSelector);
        assertEqualText("2", chartPage.getDOMProperty(numberOfProductElement, "value"));

        // removing the products
        chartPage.getElement(chartPage.deleteButtonSelector).click();
        assertTrueIsDisplayed(chartPage.getElement(chartPage.emptyMessageSelector));
    }


}
