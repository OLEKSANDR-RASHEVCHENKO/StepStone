package e2e.tests;

import e2e.pages.homePage.HomePage;
import e2e.pages.startPage.StartPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserCanSeatchVacancy extends BaseTest{

    StartPage startPage;
    HomePage homePage;
    @Test
    public void userCanSearchVacanciesTest(){
        String email = "rashevchenkooleksandr@gmail.com";
        String password = "Gazmanov1234";
        String job = "Software Test Engineer";
        String city = "Dresden";
        startPage = new StartPage(app.driver);
        startPage.cookiesAgree();
        startPage.waitForLoading();
        startPage.clickOnLogin();
        startPage.waitForLoadingDialog();
        startPage.loginInSystem(email,password);

        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
        homePage.searchAnJob(job);
        homePage.waitForVacanciesToLoad();
        int actualVacancyCount = homePage.getVacanciesCount();
        Assert.assertEquals(actualVacancyCount,25,"Vacancy count row  should be 25");
        homePage.inputLocation(city);
        homePage.testVacancyHeaders();

    }
}
