package Tests.Clients;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("main-page")
public class ClientsTests extends BrowserController {
    LoginPage page = null;

    @Test
    @Tag("client")
    @Story("Add funds to the client")
    @DisplayName("Add funds to the client")
    @Disabled
    public void addFunds() throws Exception {
        driverWrapper.assertTitle("Foxminded Accounting System");
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openClient()
                .addFundOfClient();

    }

    @Test
    @Tag("client")
    @Story("Admin create a new client ")
    @DisplayName("Create a new client with valid parameters")
    @Disabled
    public void createNewClient() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openClient()
                .createNewClientInClientSection("Daniel", "Leruan", "test@test.com",
                        "Ukraine", "Kyev", "+38066666666", "myskype");
    }

    @Test
    @Tag("client")
    @Story("Create client and compare data from DB")
    @DisplayName("Create client with random data, find customer and compare data with Data Base")
    @Disabled
    public void createClientAndCompareDataInDataBase() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openClient()
                .updateClientAndSearchAtValidateInDataBase();

    }

    @Test
    @Tag("client")
    @Story("Create client, find new client and save all data")
    @DisplayName("Create client with random value and find him using the search component")
    @Disabled
    public void createClientAndSaveData() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openClient()
                .createClientAndSearchToValidateDataAtDataBase();

    }

    @Test
    @Tag("client")
    @Story("Get all data from columns in Mentor section")
    @DisplayName("This test is \uD83D\uDC4C\uD83C\uDFFB")
    @Disabled
    public void calculateTable() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        driverWrapper.assertTitle("Foxminded Accounting System");
        page.loginUnderUser("admin", "admin")
                .openMentorsSection()
                .getValueFromColumnsFromMentorsSection("id")
                .getValueFromColumnsFromMentorsSection("name")
                .getValueFromColumnsFromMentorsSection("workload");

    }

    @Test
    @Tag("client")
    @Story("Admin create a new mentor user")
    @DisplayName("Create a new mentor user")
    public void createNewMentor() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        driverWrapper.assertTitle("Foxminded Accounting System");
        page.loginUnderUser("admin", "admin")
                .openMentorsSection()
                .createNewMentorInMentorsSection("Dima", "Sensor", "20");

    }
}