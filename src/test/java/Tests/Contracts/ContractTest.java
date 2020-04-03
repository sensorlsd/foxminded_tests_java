package Tests.Contracts;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("main-page")
public class ContractTest extends BrowserController {

    LoginPage page = null;

    @Test
    @Tag("contacts")
    @Story("Data of contact")
    @DisplayName("Check data of contacts")
    public void checkDataOfContracts() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openContract()
                .openAllContract()
                .validateContractDataFromTableAndDataBase();
    }

    @Test
    @Tag("contacts")
    @DisplayName("Search contact")
    public void checkDataOfContactsTest() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openContract()
                .openAllContract()
                .searchField("Raven Darkhölme");
    }
}

