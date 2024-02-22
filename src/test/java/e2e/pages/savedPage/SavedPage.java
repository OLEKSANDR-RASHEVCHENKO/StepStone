package e2e.pages.savedPage;

import e2e.pages.BasePage;
import e2e.utils.ScrollHelper;
import org.bouncycastle.jcajce.provider.asymmetric.X509;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SavedPage extends BasePage {
    public SavedPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='members-area-host-wv462e']")
    WebElement headerOnGespeicherteJobsPage;
    @FindBy(xpath = "//*[@alt='Meine 1 gespeicherten Jobs']")
    WebElement iconOnHeader;
    @FindBy(xpath = "//*[@class='members-area-host-1pwxwr8 CardsSpecialContainer']//*[@data-genesis-element='BASE']//*[@data-genesis-element='CARD']//h2")
    WebElement likedJobOnGeschpeiherteJobsPage;
    @FindBy(xpath = "//*[@class='members-area-host-1pwxwr8 CardsSpecialContainer']//*[@data-testid='card-container']//*[@class='members-area-host-5gadwe']//*[@data-testid='savedjob-icon-heart']")
    WebElement likeButtonOnGeschpeiherteJobsPage;
    @FindBy(xpath = "//*[@data-testid='savedjobs-expired-container-empty']")
    WebElement massageThatThereAreNoVacanciesSaved;

    public void waitForLoading(){
        getWait().forVisibility(headerOnGespeicherteJobsPage);
        try {
            iconOnHeader.click();
        }catch (ElementClickInterceptedException e){
            e.fillInStackTrace();
        }
    }
    public void waitForVisibilityMassageThatThereAreNoVacanciesSaved(){
        getWait().forVisibility(massageThatThereAreNoVacanciesSaved);
        Assert.assertTrue(massageThatThereAreNoVacanciesSaved.isDisplayed());
    }
    public String getLikedJobHeaderOnGeschpeiherteJobsPage(){
        return likedJobOnGeschpeiherteJobsPage.getText();
    }
    public void clickOnLikeButtonOnGeschpeiherteJobsPageToDislikeThat(){
        getWait().forClickable(likeButtonOnGeschpeiherteJobsPage);
        likeButtonOnGeschpeiherteJobsPage.click();
    }


}
