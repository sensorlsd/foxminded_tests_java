package Tests.Registration;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

import static utils.RandomGenerators.getRandomString;

@Tag("login-page")
public class RegistrationTest extends BrowserController {

    LoginPage page = null;

    @Test
    @Tag("login")
    @Story("Validate alert popup after reg. user with incorrect data")
    @DisplayName("Registration user with incorrect data")
    public void typeRegistrationInvalidUser() {
        page = new LoginPage(driverWrapper.getDriver());
        page.fillRegistrationForm("username", "admin", "admin");
    }

    @Test
    @Tag("login")
    @Story("User try login under admin user to the system")
    @DisplayName("Login under admin with valid data")
    public void loginUnderAdmin() throws Exception {
        driverWrapper.assertTitle("Foxminded Accounting System");
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin");

    }

    @Test
    @Tag("registration")
    @Story("User create a new user ")
    @DisplayName("Create a new user in system with valid parameters")
    public void createNewUser() {
        page = new LoginPage(driverWrapper.getDriver());
        driverWrapper.pageFactoryInit(page);
        page.fillRegistrationForm(getRandomString(6),
                getRandomString(6),
                "random@random.com");
    }


    @Test
    @Tag("test")
    @Story("User open chrome browser")
    @DisplayName("Run test at Chrome browser")
    public void openChromeBrowser() {
        driverWrapper.assertTitle("Foxminded Accounting System");
    }


}
