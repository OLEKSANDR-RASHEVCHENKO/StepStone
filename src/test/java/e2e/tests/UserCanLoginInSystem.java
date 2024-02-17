package e2e.tests;

import e2e.pages.homePage.HomePage;
import e2e.pages.startPage.StartPage;
import org.testng.annotations.Test;

public class UserCanLoginInSystem extends BaseTest{

    StartPage startPage;
    HomePage homePage;
    @Test
    public void userCanLoginInSystem(){
        String email = "rashevchenkooleksandr@gmail.com";
        String password = "Gazmanov1234";
        startPage = new StartPage(app.driver);
        startPage.cookiesAgree();
        startPage.waitForLoading();
        startPage.clickOnLogin();
        startPage.waitForLoadingDialog();
        startPage.loginInSystem(email,password);

        homePage = new HomePage(app.driver);
        homePage.waitForLoadingHomePage();
    }
}
