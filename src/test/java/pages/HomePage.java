package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage{

    public By genderMaleButtonSelector = By.xpath("//*[@id='genderManButton']");
    public By cookieSelector = By.xpath("//*[@id='onetrust-accept-btn-handler']");


    public HomePage(WebDriver driver) {
        super(driver);
        this.pageTitle = "Beymen.com – Türkiye’nin Tek Dijital Lüks Platformu";
    }


}
