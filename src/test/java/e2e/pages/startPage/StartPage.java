package e2e.pages.startPage;

import e2e.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class StartPage extends BasePage {
    public StartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@class='cc-prompt-modal rebrand']")
    WebElement cookiesDialog;
    @FindBy(xpath = "//*[@id='ccmgt_explicit_accept']")
    WebElement allesAkzeptierenButton;
    @FindBy(xpath = "//*[@id='app-header']")
    WebElement header;
    @FindBy(xpath = "//*[text()='Login']")
    WebElement loginButton;
    @FindBy(xpath = "//*[@data-testid='sign-in']")
    WebElement loginButtonOnDropDown;
    @FindBy(xpath = "//*[@id='login-registration-dialog-1']")
    WebElement loginDialog;
    @FindBy(xpath = "//*[@data-testid='email-input']")
    WebElement emailInputField;
    @FindBy(xpath = "//*[@data-testid='password-input']")
    WebElement passwordInputField;
    @FindBy(xpath = "//*[@data-testid='login-submit-btn']")
    WebElement einloggenButton;
    @FindBy(xpath = "//*[@id='stepstone-checkbox-31']")
    WebElement checkBoxOnEinloggenBleiben;

    public void cookiesAgree() {
        getWait().forVisibility(cookiesDialog);
        allesAkzeptierenButton.click();
    }

    public void waitForLoading() {
        getWait().forVisibility(header);
    }

    public void clickOnLogin() {
        loginButton.click();
        loginButtonOnDropDown.click();
    }
    public void loginInSystem(String email,String password){
        emailInputField.sendKeys(email);
        passwordInputField.sendKeys(password);
        checkBoxOnEinloggenBleiben.click();
        einloggenButton.click();
    }


    public void waitForLoadingDialog() {
        getWait().forVisibility(loginDialog);
        getWait().forVisibility(emailInputField);
        getWait().forVisibility(passwordInputField);
        getWait().forVisibility(checkBoxOnEinloggenBleiben);
        getWait().forVisibility(einloggenButton);


    }
}
