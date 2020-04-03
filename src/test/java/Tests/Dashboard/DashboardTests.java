package Tests.Dashboard;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;

@Tag("main-page")
public class DashboardTests extends BrowserController {
    LoginPage page = null;

    @Test
    @Tag("dashboard")
    @Story("Positive - hide consultancy table")
    @DisplayName("Positive - hide consultancy table")
    public void hideTable() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openDashBoard()
                .hideConsultancyStatisticsTable();
    }

    @Test
    @Tag("dashboard")
    @Story("Positive - close consultancy table")
    @DisplayName("Positive - close consultancy table")
    public void closeTable() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openDashBoard()
                .closeConsultancyStatisticsTable();
    }

    @Test
    @Tag("dashboard")
    @Story("Positive - hide and open table")
    @DisplayName("Positive - hide and open table")
    public void hideAndOpenTable() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openDashBoard()
                .hideConsultancyStatisticsTable()
                .openHiddenConsultancyStatisticsTable();
    }

    @Test
    @Tag("dashboard")
    @Story("Positive - validate name of columns")
    @DisplayName("Positive - validate name of columns")
    public void checkColumnNames() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openDashBoard()
                .checkNamesOfTable();
    }

    @Test
    @Tag("dashboard")
    @Story("Positive - validate name of sections")
    @DisplayName("Positive - validate name of sections")
    public void checkSectionNames() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openDashBoard()
                .checkSectionNames();
    }
}
