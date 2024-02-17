package e2e.pages.homePage;

import e2e.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='app-membersArea']")
    WebElement halloHeaderOnHomePage;


    public void waitForLoadingHomePage(){
        getWait().forVisibility(halloHeaderOnHomePage);
        Assert.assertTrue(halloHeaderOnHomePage.isDisplayed());
    }
}
