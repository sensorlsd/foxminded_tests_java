package Tests.Consultancy;

import DriverManager.BrowserController;
import PageObject.LoginPage;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static utils.RandomGenerators.getRandomString;

@Tag("main-page")
public class ConsultanciesTests extends BrowserController {

    LoginPage page = null;

    @Test
    @Tag("consultancies")
    @Story("Positive - Create a new consultancy ")
    @DisplayName("Positive - Create a new consultancy with valid parameters")
    public void createNewConsultancyWithRequiredField() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openConsultancy()
                .createNewConsultanciesRequiredFields("General Mentoring",
                        "Brain storm",  "1000");
    }

    @Test
    @Tag("consultancies")
    @Story("Positive - New consultancy with all fields")
    @DisplayName("Positive - New consultancy with all fields")
    public void createNewConsultancyWithAllField() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openConsultancy()
                .createNewConsultanciesWithAllFields("General Mentoring",
                        "Brain storm", "2000", "80",
                        "90", "1000");
    }

    @Test
    @Tag("consultancies")
    @Story("Positive - New consultancy with all fields")
    @DisplayName("Positive - New consultancy with all fields")
    @Disabled
    public void openAndCloseCreateNewConsultancy() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openConsultancy()
                .openAndCloseCreateNewConsultancy("Open and close");
    }

    @Test
    @Tag("consultancies")
    @Story("Positive - DELETE during creating")
    @DisplayName("Positive - DELETE during creating")
    @Disabled
    public void deleteNewConsultancy() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openConsultancy()
                .deleteInCreateNewConsultanciesFieldAllFields("Delete button");
    }

    @Test
    @Tag("consultancies")
    @Story("Negative - Create a new consultancy w/o data.")
    @DisplayName("Negative - Create a new consultancy w/o data.")
    public void createNewConsultancyWithoutData() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openConsultancy()
                .createNewConsultancyWithoutData();
    }

    @Test
    @Tag("consultancies")
    @Story("Negative - Create a new consultancy w/o required fields.")
    @DisplayName("Negative - Create a new consultancy  w/o required fields.")
    public void createNewConsultancyWithoutRequiredData() throws Exception {
        page = new LoginPage(driverWrapper.getDriver());
        page.loginUnderUser("admin", "admin")
                .openConsultancy()
                .typeInvalidDataDuringCreatingNewConsultancy(getRandomString(51),"2400",
                        "90", "100", "1200");
    }
}
