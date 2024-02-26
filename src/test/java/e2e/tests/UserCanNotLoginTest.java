package e2e.tests;

import e2e.pages.homePage.HomePage;
import e2e.pages.startPage.StartPage;
import e2e.utils.DataProviders;
import org.testng.annotations.Test;

import static e2e.tests.BaseTest.app;

public class UserCanNotLoginTest extends BaseTest{
    StartPage startPage;
    HomePage homePage;
    //Should be finish
    @Test(dataProvider = "invalidEmail", dataProviderClass = DataProviders.class)
    public void userCanNotLoginTest(String email,String password){
        startPage = new StartPage(app.driver);
        startPage.cookiesAgree();
        startPage.waitForLoading();
        startPage.clickOnLogin();
        startPage.waitForLoadingDialog();
        startPage.loginInSystem(email,password);
        startPage.waitForVisibilityErrorMassage();
    }
}
