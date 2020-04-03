package Tests.ExtraFields;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("main-page")
@Tag("extra-fields")
public class ExtraFieldsTests extends BrowserController {
    LoginPage page = null;

    @Test
    @Story("Positive - Create extra field for Employee")
    @DisplayName("Positive - Create extra field for Employee")
    public void createExtraFieldForEmployee() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openExtraField()
                .createNewEmployeeField("Employee Field");
    }

    @Test
    @Story("Positive - Create extra field for client")
    @DisplayName("Positive - Create extra field for client")
    public void createExtraFieldForClient() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openExtraField()
                .createNewClientField("Client Field");
    }


    @Test
    @Story("Positive - Delete extra field for Employee")
    @DisplayName("Positive - Delete extra field for Employee")
    public void deleteExtraFieldForEmployee() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openExtraField()
                .deleteExtraFieldOfEmployee("Employee Field");
    }

    @Test
    @Story("Positive - Delete extra field for Employee")
    @DisplayName("Positive - Delete extra field for Employee")
    public void deleteExtraFieldForClient() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openExtraField()
                .deleteExtraFieldOfClient("Client Field");
    }
}
