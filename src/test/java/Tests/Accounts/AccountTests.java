package Tests.Accounts;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("main-page")
public class AccountTests extends BrowserController {

    LoginPage page = null;

    @Test
    @Tag("account")
    @Story("Data of Invoice")
    @DisplayName("NameClass")
    @Disabled
    public void checkDataOfContracts() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openInvoices()
                .openAllInvoices()
                .compareInvoiceOfUIWithDB();
    }
}
