package e2e.pages.homePage;

import e2e.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//*[@id='app-membersArea']")
    WebElement halloHeaderOnHomePage;
    @FindBy(xpath = "//*[@data-testid='menu-item-profile']")
    WebElement profilButton;
    @FindBy(xpath = "//*[@data-testid='menu-item-my-jobs']")
    WebElement meineJobsButton;
    @FindBy(xpath = "//*[@data-testid='menu-item-my-career']")
    WebElement meineKarriereButton;
    @FindBy(xpath = "//*[@data-testid='menu-item-magazin']")
    WebElement magazinButton;
    @FindBy(xpath = "//*[@data-testid='menu-item-for-recruiters']")
    WebElement fürArbeitgeberButton;


    @FindBy(xpath = "//*[@id='stepstone-autocomplete-26']")
    WebElement jobFormField;
    @FindBy(xpath = "//*[@id='stepstone-autocomplete-52']")
    WebElement locationFormField;
    @FindBy(xpath = "//*[@data-at='searchbar-search-button']")
    WebElement jobsFindenButton;







    public void waitForLoadingHomePage(){
        getWait().forVisibility(halloHeaderOnHomePage);
        getWait().forVisibility(profilButton);
        getWait().forVisibility(meineJobsButton);
        getWait().forVisibility(meineKarriereButton);
        getWait().forVisibility(magazinButton);
        getWait().forVisibility(fürArbeitgeberButton);
        getWait().forClickable(jobFormField);
        getWait().forClickable(jobsFindenButton);
        Assert.assertTrue(halloHeaderOnHomePage.isDisplayed());
    }

    public void searchAnJob(String job){
        jobFormField.sendKeys(job + Keys.ENTER);
    }
    public void inputLocation(String city){
        locationFormField.sendKeys(city + Keys.ENTER);
    }
    public void waitForVacanciesToLoad(){
        List<WebElement> vacancyElements = driver.findElements(By.xpath("//*[@data-at='job-item']"));
        getWait().forAllVisibility(vacancyElements);
        Assert.assertTrue(vacancyElements.size() >= 25,"No vacancies loaded");
    }
    public int getVacanciesCount(){
        return driver.findElements(By.xpath("//*[@data-at='job-item']")).size();
    }
    public void testVacancyHeaders(){
        List<String> keywords = Arrays.asList("Software","Test","Tester","Softwaretester","Testentwickler");
        List<WebElement> vacancyElements = driver.findElements(By.xpath("//*[@data-at='job-item']"));

        for (WebElement vacancyElement:vacancyElements){
            WebElement headerElement = vacancyElement.findElement(By.xpath("//h2"));
            String headerText = headerElement.getText();

            boolean containsKeyword = false;
            for (String keyword : keywords){
                if (headerText.toLowerCase().contains(keyword.toLowerCase())){
                    containsKeyword = true;
                    break;
                }
            }
            if (containsKeyword){
                System.out.println("Заголовок \"" + headerText + "\" содержит хотя бы одно из ключевых слов.");
            }else {
                System.out.println("Заголовок \"" + headerText + "\" не содержит ни одного из ключевых слов.");
            }
        }
    }



}
