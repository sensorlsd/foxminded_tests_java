package Tests.CashFlow;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("main-page")
public class CashFlowTests extends BrowserController {

    LoginPage page = null;

    @Test
    @Tag("cash-flow")
    @Story("Generate report with valid data")
    @DisplayName("Generate report with valid data")
    @Disabled
    public void generateReportValidDate() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openCashFlow()
                .generateReportOfCashFlow(3, "22032015", "01012020");
    }

    @Test
    @Tag("cash-flow")
    @Story("Generate report with invalid start date")
    @DisplayName("Generate report with invalid start date\"")
    @Disabled
    public void startDateMoreThanEndDate() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openCashFlow()
                .generateReportOfCashFlow(2, "01012020", "01012019");
    }

    @Test
    @Tag("cash-flow")
    @Story("Generate report with invalid end date")
    @DisplayName("Generate report with invalid length end date\"")
    @Disabled
    public void generateReportWithInvalidEndDate() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openCashFlow()
                .generateReportOfCashFlow(1, "220320015", "0101999999");
    }

    @Test
    @Tag("cash-flow")
    @Story("Generate report with invalid start date")
    @DisplayName("Generate report with invalid length start date\"")
    @Disabled
    public void generateReportWithInvalidStartDate() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openCashFlow()
                .generateReportOfCashFlow(2, "01019999999", "15032020");
    }
}
