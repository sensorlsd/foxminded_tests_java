package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.WaitHelpers;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static utilities.Log.error;
import static utilities.Log.info;
import static utils.HandlingErrors.isAlertPresent;


public class LoginPage {

    private WebDriver driver = null;
    private WaitHelpers wait = null;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitHelpers(driver);
        PageFactory.initElements(this.driver, this);
    }

    //Selectors for login in system
    @FindBy(id = "login-form-link") private WebElement loginFrom;
    @FindBy(id = "register-form-link") private WebElement registerForm;
    @FindBy(id = "username") private WebElement loginField;
    @FindBy(id = "password") private WebElement passwordField;

    // Selector for registration a new user
    @FindBy(id = "register-username") private WebElement regUserName;
    @FindBy(id = "email") private WebElement regEmail;
    @FindBy(id = "register-password") private WebElement regPassword;
    @FindBy(id = "login-submit") private WebElement submitButton;
    @FindBy(id = "register-submit") private WebElement registrationBtn;

    /**
     * Actions methods
     *
     * @return
     */

    public LoginPage openLoginForm() {
        try {
            info("Click on login form");
            loginFrom.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public LoginPage fillUserName(String username) {
        try {
            info("Fill user name");
            loginField.sendKeys(username);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public LoginPage fillPassword(String password) {
        try {
            info("Fill password");
            passwordField.sendKeys(password);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public void clickSubmitBtn() {
        try {
            info("Click submit");
            submitButton.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
    }

    public LoginPage openRegisterForm() {
        try {
            info("Open register form");
            registerForm.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public LoginPage regUserNameField(String username) {
        try {
            info("Fill user name field of registration form");
            regUserName.sendKeys(username);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public LoginPage regEmailField(String password) {
        try {
            info("Fill email field of registration form");
            regEmail.sendKeys(password);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public LoginPage regPasswordField(String password) {
        try {
            info("Fill password field of registration form");
            regPassword.sendKeys(password);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public void registrationBtn() {
        try {
            info("Click registration button");
            registrationBtn.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
    }

    public MainPage loginUnderUser(String username, String password) throws Exception {
        try {
            info("Login under user: " + username + " password: " + password);
            fillUserName(username);
            fillPassword(password);
            clickSubmitBtn();
            this.wait.titleContains("Admin panel");
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return new MainPage(this.driver);
    }

    public LoginPage fillRegistrationForm(String username, String password, String email) {
        try {
            info("Fill registration form: " + username + " " + password + " " + email);
            openRegisterForm();
            regUserNameField(username);
            regPasswordField(password);
            regEmailField(email);
            registrationBtn();
            this.wait.alertIsPresent();
            boolean alertPresent = isAlertPresent(this.driver);
            assertTrue(alertPresent);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }
}
