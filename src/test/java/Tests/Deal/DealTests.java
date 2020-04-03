package Tests.Deal;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

@Tag("main-page")
public class DealTests extends BrowserController {

    LoginPage page = null;

    @Test
    @Tag("deal")
    @Story("Data of deal")
    @DisplayName("NameClass2")
    public void checkDataOfContracts() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openDeals()
                .openAllDealsCategory()
                .compareDealsDataWithDB();
    }
}
