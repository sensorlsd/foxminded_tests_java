package PageObject;

import Database.DataBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.WaitHelpers;

import java.sql.SQLException;
import java.util.*;

import static Database.DBConstants.*;
import static org.junit.jupiter.api.Assertions.*;
import static utilities.Log.error;
import static utilities.Log.info;
import static utils.AssertionHelper.handlingAssert;
import static utils.FormattingHelpers.renderDate;
import static utils.FormattingHelpers.renderDateDBToUI;
import static utils.HandlingErrors.getErrorByText;
import static utils.HandlingErrors.isErrorVisible;
import static utils.RandomGenerators.getRandomData;
import static utils.WaitHelpers.impWait;



public class MainPage {

    private WebDriver driver = null;
    private String firstName, lastName, email,
            country, city, phoneNumber, socialNetwork = null;
    private DataBase dataBase = new DataBase();
    private WaitHelpers wait = null;

    public MainPage(WebDriver driver) throws Exception {
        this.driver = driver;
        this.wait = new WaitHelpers(this.driver);
        PageFactory.initElements(this.driver, this);
        defineFieldsData();
    }

    public void defineFieldsData() {
        this.firstName = getRandomData("random", 10);
        this.lastName = getRandomData("random", 10);
        this.email = getRandomData("email", 5);
        this.country = getRandomData("capitalizeFirstLetter", 10);
        this.city = getRandomData("capitalizeFirstLetter", 10);
        this.phoneNumber = getRandomData("stringNumber", 999999999);
        this.socialNetwork = getRandomData("random", 10);
    }

    /**
     * Selectors of Main Page
     */

    // Dashboard section
    @FindBy(xpath = "//div/section/ul/li[2]/a/span") private WebElement dashboardSection;
    @FindAll({@FindBy(xpath = "//section/div/div[1]/div/div/div/span[1]")}) private List<WebElement> sectionName;
    @FindBy(xpath = "//div/div[2]/div//div[1]/div/button[2]/i") private WebElement closeConsultancyTableOfDashboard;
    @FindBy(xpath = "//div/div[2]/div//div[1]/div/button[1]/i") private WebElement hideConsultancyTableOfDashboard;
    @FindBy(xpath = "//div/div[2]/div//div[1]/div/button[1]/i") private WebElement openConsultancyTableOfDashboard;
    @FindBy(xpath = "//div[2]/div/div/div[1]/h3") private WebElement tableNameOfDashboard;
    @FindAll({@FindBy(xpath = "//div[2]/div/table/thead/tr/th")}) private List<WebElement> columnsNameOfDashboardTable;
    @FindBy(xpath = "//div/div[2]/div/div/div[2]") private WebElement containerOfTable;
    // Deals section
    @FindBy(xpath = "//div/section/ul/li[3]/a/span[1]") private WebElement dealsSection;
    @FindBy(xpath = "//div/section/ul/li[3]/ul/li[1]/a") private WebElement newDealsCategory;
    @FindBy(xpath = "//div/section/ul/li[3]/ul/li[2]/a") private WebElement archiveDealCategory;
    @FindBy(xpath = "//div/section/ul/li[3]/ul/li[3]/a") private WebElement allDealsCategory;
    @FindAll({@FindBy(xpath = "//div[1]/div[2]/div[2]/table/thead/tr/th/div[1]")}) private List<WebElement> namesColumnsOfDealTable;
    @FindAll({@FindBy(xpath = "//div[1]/div[2]/div[2]/table/tbody/tr[1]/td")}) private List<WebElement> valuesOfDealTable;

    // Turn section
    @FindBy(xpath = "//a[@pathname='/admin/queues']/span[@innertext='Очередь']") private WebElement turnSection;
    @FindBy(xpath = "//a[@role='tab']/p[@innertext='Group Development']") private WebElement groupDevelopmentTabInTurnSection;
    @FindBy(xpath = "//a[@role='tab']/p[@innertext='Mentoring']") private WebElement mentoringTabInTurnSection;
    @FindBy(xpath = "//a[@role='tab']/p[@innertext='Personal Mentor']") private WebElement personalMentorsTabInTurnSection;

    // Client section
    @FindBy(xpath = "//ul[@class='sidebar-menu']//a[@href='/admin/clients']/span") private WebElement clientSection;
    @FindBy(xpath = "//div[@id='fixed-table-toolbar--buttons']/button[@type='button']") private WebElement createClientBtn;
    @FindBy(xpath = "//div[@id='myModal']//h4[@class='modal-title']") private WebElement titleInCreateNewClient;
    @FindBy(id = "firstName") private WebElement clientFirstName;
    @FindBy(id = "lastName") private WebElement clientLastName;
    @FindBy(id = "email") private WebElement clientEmail;
    @FindBy(id = "country") private WebElement clientCountry;
    @FindBy(id = "city") private WebElement clientCity;
    @FindBy(id = "phoneNumber") private WebElement clientPhoneNumber;
    @FindBy(id = "skype") private WebElement clientSkype;
    @FindAll({@FindBy(xpath = "//div[1]/div[1]/div/div/form/div/div[2]")}) private WebElement allInputFieldsOfCreateClient;
    @FindBy(xpath = "//table/tbody/tr[1]/td[1][@class='col-xs-2']") private WebElement valueOfCurrency;
    @FindBy(xpath = "//div[2]/div/div[1]/div[2]/div[2]/table/tbody/tr/td[2]") private WebElement amoutOfBalance;
    @FindBy(xpath = "//div[1]/div[2]/button") private WebElement addFundsButton;
    @FindBy(xpath = "//*[@id='depositForm']/div[1]/div[1]/input") private WebElement moneyAmountField;
    @FindBy(xpath = "//textarea[@id='depositDescriptionModal']") private WebElement descriptionOfDepositField;
    @FindBy(xpath = "//div[@id='depositModal']/div[@role='document']//button[@type='button submit']") private WebElement submitOfAddFunds;
    @FindBy(xpath = "//*[@id=\"depositModal\"]/div/div/div[3]/button[1]") private WebElement closeButtonOfAddFundsButton;
    @FindBy(xpath = "//div[1]/div[2]/div/div[1]/div[2]/div[2]//tr/td[1]") private WebElement clientCurrency;
    @FindBy(xpath = "//div[1]/div[2]/div/div[1]/div[2]/div[2]//tr/td[2]") private WebElement clientBalance;
    @FindBy(xpath = "//div[@id='myModal']//button[@type='submit']") private WebElement clientSubmitBtn;
    @FindBy(xpath = "//div[@role='group']/button[@innertext='Delete']") private WebElement clientDeleteBtn;
    @FindBy(xpath = "//table//tr/th[1]/div[@class='th-inner sortable both asc']") private WebElement columnIdInClientSection;
    @FindBy(xpath = "//table//tr/th[2]/div[@class='th-inner sortable both']") private WebElement columnNameInClientSection;

    // Mentors section
    @FindBy(xpath = "//div/section/ul/li[6]/a") private WebElement mentorsSection;
    @FindBy(xpath = "//div[@id='fixed-table-toolbar--buttons']/button[@type='button']") private WebElement createNewEmployeesButton;
    @FindBy(xpath = "//h4[@class=\"modal-title\"]") private WebElement titleInCreateNewEmployees;
    @FindBy(xpath = "//input[@name=\"firstName\"]") private WebElement firstNameFieldInCreateNewEmployees;
    @FindBy(xpath = "//input[@name=\"lastName\"]") private WebElement lastNameFieldInCreateNewEmployees;
    @FindBy(xpath = "//input[@name=\"maxClients\"]") private WebElement employeeMaxLoadFieldInCreateNewEmployees;
    @FindBy(xpath = "//button[@class=\"btn btn-primary\"]") private WebElement saveNewEmployeesButton;
    @FindBy(xpath = "//section[@class='content-header display-grid']//div[@class='fixed-table-body']/table//div[.='Id']") private WebElement nameColumnId;
    @FindBy(xpath = "//section[@class='content-header display-grid']//div[@class='fixed-table-body']/table//div[.='name']") private WebElement nameColumnName;
    @FindBy(xpath = "//section[@class='content-header display-grid']//div[@class='fixed-table-body']/table//div[.='workload']") private WebElement nameColumnWorkload;
    @FindAll({@FindBy(xpath = "//div[@class='content-wrapper']/section[@class='content-header display-grid']//div[@class='fixed-table-body']/table/tbody/tr/td[2]")}) private List<WebElement> valueOfColumnName;
    @FindAll({@FindBy(xpath = "//div[@class='content-wrapper']/section[@class='content-header display-grid']//div[@class='fixed-table-body']/table/tbody/tr/td[3]")}) private List<WebElement> valueOfColumnWorkload;
    @FindAll({@FindBy(xpath = "//div[@class='content-wrapper']/section[@class='content-header display-grid']//div[@class='fixed-table-body']/table/tbody/tr/td[1]")}) private List<WebElement> valueOfColumnId;

    // Contract section
    @FindBy(xpath = "//aside/div/section/ul/li[7]/a/span[1]") private WebElement contractSection;
    @FindBy(xpath = "//aside/div/section/ul/li[7]/ul/li[1]/a") private WebElement allContractCategory;
    @FindBy(xpath = "//aside/div/section/ul/li[7]/ul/li[2]/a") private WebElement testContractCategory;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[1]") private WebElement columnContactId;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[2]") private WebElement columnClientName;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[3]") private WebElement columnConsultancy;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[4]") private WebElement columnMentorName;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[5]") private WebElement columnContractDate;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[6]") private WebElement columnType;
    @FindBy(xpath = "//div/section/div[1]/div[2]/div[2]//thead/tr/th[7]") private WebElement columnStatus;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[1]")}) private WebElement valueOfColumnContactId;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[2]")}) private List<WebElement>  valuesOfColumnClientName;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]")}) private List<WebElement>  valuesOfColumnConsultancy;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[4]")}) private List<WebElement>  valuesOfColumnMentorName;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[5]")}) private List<WebElement>  valuesOfColumnContractDate;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[6]")}) private List<WebElement>  valuesOfColumnType;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[7]")}) private List<WebElement>  valuesOfColumnStatus;
    @FindAll({@FindBy(xpath = "/html/body/div/section/div[1]/div[2]/div[2]/table/thead/tr/th")}) private List<WebElement> namesOfColumns;
    @FindAll({@FindBy(xpath = "//div/section/div[1]/div[2]/div[2]/table/tbody/tr[1]/td")}) private List<WebElement> valuesOfContractTable;

    // Consultancy section
    @FindBy(xpath = "//div/section/ul/li[8]/a") private WebElement consultancySection;
    @FindBy(xpath = "//div[@id='fixed-table-toolbar--buttons']/a[@href='/admin/consultancies/new']") private WebElement createNewConsultancyButton;
    @FindBy(id = "name") private WebElement titleFieldCreateNewConsultanciesInServicesSection;
    @FindBy(id = "description") private WebElement descriptionFieldInServicesSection;
    @FindBy(id = "prices[0].amount") private WebElement priceUAHFieldInServicesSection;
    @FindBy(id = "prices[1].amount")  private WebElement priceEURFieldInServicesSection;
    @FindBy(id = "prices[2].amount") private WebElement priceUSDFieldInServicesSection;
    @FindBy(id = "employeeRate.amount") private WebElement employeeRateFieldInServicesSection;
    @FindBy(xpath = "//div[1]/button[@innertext='Go back']") private WebElement goBakcButoonInServicesSection;
    @FindBy(xpath = "//div[@role='group']/button[@innertext='Delete']") private WebElement deleteButtonInServicesSection;
    @FindAll({@FindBy(xpath = "//div/div[2]/output[@class='text-danger']")}) private List<WebElement> errorLabel;
    private String errorByText = "//div/div[2]/output[@class='text-danger'][contains(text(),'%s')]";

    // Invoices section
    @FindBy(xpath = "//div/section/ul/li[9]/a/span[1]") private WebElement invoicesSection;
    @FindBy(xpath = "//div/section/ul/li[9]/ul/li[1]") private WebElement allInvoicesCategory;
    @FindBy(xpath = "//div/section/ul/li[9]/ul/li[2]") private WebElement putUpInvoicesCategory;
    @FindBy(xpath = "//div/section/ul/li[9]/ul/li[3]") private WebElement overdueInvoicesCategory;
    @FindBy(xpath = "//div/div[1]/div[2]/div[2]/table/thead/tr/th[1]/div[1]") private WebElement columnIdOfInvoiceTable;
    @FindAll({@FindBy(xpath = "//div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[1]")}) private WebElement valueOfColumnInvoiceId;
    @FindAll({@FindBy(xpath = "//div/div[1]/div[2]/div[2]/table/thead/tr/th")}) private List<WebElement> namesColumnsOfInvoiceTable;
    @FindAll({@FindBy(xpath = "//div/div[1]/div[2]/div[2]/table/tbody/tr[1]/td")}) private List<WebElement> valuesOfInvoiceTable;

    // Salary section
    @FindBy(xpath = "//li[10]/a[@pathname='/admin']") private WebElement salariesSection;
    @FindBy(xpath = "//?/a[@innertext='Зарплата']") private WebElement salaryCategory;
    @FindBy(xpath = "//section[@safeclass~'\\bcontent-header\\b.*\\bdisplay-grid\\b']/h3[@innertext='Salary']") private WebElement headerInSalaryCategory;
    @FindBy(xpath = "//?/a[@innertext='Формирование зарплаты']") private WebElement salaryFormationSalaryCategory;
    @FindBy(xpath = "///h3[@innertext='Choose range to calculate salary']") private WebElement headerInFormationSalaryCategory;
    @FindBy(xpath = "///input[@name='dateFrom']") private WebElement dateFromInFormationSalaryCategory;
    @FindBy(xpath = "///input[@name='dateTo']") private WebElement dateFromToFormationSalaryCategory;
    @FindBy(xpath = "//*[@id=\"payroll-form\"]/div/div/div[2]/form/button") private WebElement calculateSalariesButtonInFormationSalaryCategory;

    // Payments section
    @FindBy(xpath = "//a[@pathname='/admin/payments']/span") private WebElement paymentsSection;

    // Users section
    @FindBy(xpath = "//a[@pathname='/admin/users']/span[@innertext='Пользователи']") private WebElement usersSection;
    @FindBy(xpath = "//div[#'fixed-table-toolbar--buttons']/button[@innertext='      Add new User    ']") private WebElement addNewUserButton;
    @FindBy(xpath = "//*[@id=\"addNewModalLabel\"]") private WebElement titleInPopupCreateNewUser;
    @FindBy(xpath = "//input[#'register-username']") private WebElement userNameInAddNewUser;
    @FindBy(xpath = "//input[#'register-email']") private WebElement emailInAddNewUser;
    @FindBy(xpath = "//input[#'register-password']")  private WebElement passwordInAddNewUser;
    @FindBy(xpath = "//button[#'register-submit-button']") private WebElement createUserButtonInAddNewUser;
    @FindBy(xpath = "///button[@innertext='Close']") private WebElement closeButtonInAddNewUser;

    // Cash flow section
    @FindBy(xpath = "//div/section/ul/li[14]/a/span") private WebElement cashFlowSection;
    @FindBy(xpath = "//div[1]/section/h3") private WebElement headerNameOfCashFlow ;
    @FindBy(id = "selectedConsultancyField") private WebElement consultancyField;
    @FindAll({@FindBy(xpath = "//*[@id='selectedConsultancyField']/option")}) private List<WebElement> listOfOptionsCashFlow;
    @FindBy(xpath = "//input[#'beginDateField']") private WebElement beginDateField;
    @FindBy(xpath = "//input[#'endDateField']") private WebElement endDateField;
    @FindBy(xpath = "//form/div[4]/button") private WebElement generateReportButton;
    @FindAll({@FindBy(xpath = "//div[1]/section/div/div/h3")}) private List<WebElement> headerOfTables;


    // Extra field section
    @FindBy(xpath = "//a[@pathname='/admin/conf/extra-fields']/span[@innertext='Доп. поля']") private WebElement extraFieldSection;
    @FindBy(xpath = "//div[@id='fixed-table-toolbar--buttons-employee']/button[@type='button']")  private WebElement createNewEmployeeFieldInExtraFieldSection;
    @FindBy(xpath = "//div[@id='fixed-table-toolbar--buttons-client']/button[@type='button']") private WebElement createNewClientFieldInExtraFieldSection;
    @FindBy(xpath = "//h4[@innertext='Create Employee Field']") private WebElement titleNewEmployeeFieldPopupInExtraFieldSection;
    @FindBy(xpath = "//h4[@innertext='Create Employee Field']") private WebElement nameFieldNewEmployeeFieldPopupInExtraFieldSection;
    @FindBy(xpath = "//*[@id=\"createEmployeeFieldModal\"]/div/div/div[2]/form/button") private WebElement saveButtonNewEmployeeFieldPopupInExtraFieldSection;
    @FindBy(xpath = "///h4[@innertext='Create Client Field']")  private WebElement titleNewClientFieldPopupInExtraFieldSection;
    @FindBy(xpath = "//input[#'clientFieldName']") private WebElement nameFieldNewClientFieldPopupInExtraFieldSection;
    @FindBy(xpath = "//div[@id='createClientFieldModal']//form/button[@type='submit']") private WebElement saveButtonNewClientFieldPopupInExtraFieldSection;
    @FindBy(xpath = "//div[1]/div/div[2]/div[1]/div[1]/div[2]/input") private WebElement searchOfEmployeeField;
    @FindBy(xpath = "//div[2]/div/div[2]/div[1]/div[1]/div[2]/input") private WebElement searchOfClientField;
    @FindAll({@FindBy(xpath = "//div[1]/div/div[2]/div[1]/div[2]/div[2]//tr[1]/td/p")}) private List<WebElement> getDataFromTableOfEmployee;
    @FindAll({@FindBy(xpath = "//div[2]/div/div[2]/div[1]/div[2]/div[2]//tr[1]/td/p")}) private List<WebElement> getDataFromTableOfClient;
    @FindBy(xpath = "//div[1]/div[2]/div[2]/table/tbody/tr[1]/td[3]/button") private WebElement deleteButtonOfEmployeeExtraField;
    @FindBy(xpath = "//*[@id=\"deleteClientField\"]/button") private WebElement deleteButtonOfClientExtraField;
    String selectorGetIdFromClientTable = "//div[@class='fixed-table-container']/div[@class='fixed-table-body']//td[1]//a[@href='/admin/clients/%d']";

    // User profile
    @FindBy(xpath = "//a[@pathname='/admin']/img[@alt='User Image']") private WebElement userProfile;
    @FindBy(xpath = "///a[@innertext='Sign out']") private WebElement signOutUserProfile;

    // Global buttons
    @FindBy(xpath = "//nav[@safeclass~'\\bnavbar\\b.*\\bnavbar-static-top\\b']/a[@role='button']") private WebElement burgerButton;
    @FindBy(css = "input") private WebElement searchField;
    @FindBy(xpath = "//section[@class='content-header display-grid']//button[@name='refresh']") private WebElement searchBtn;
    @FindBy(xpath = "//button[@name='refresh']") private WebElement refreshButton;
    @FindBy(xpath = "//div[2]/button[@class='btn btn-primary'][contains(text(),'Save')]") private WebElement saveButtonForRegistrationNewForm;

    // Dashboard section
    public MainPage openDashBoard() {
        try {
            info("Open dashboard");
            wait.waitTextToBePresentInElement(dashboardSection,"Dashboard");
            this.dashboardSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage closeConsultancyStatisticsTable() {
        try {
            info("Close Consultancy Statistics Table.");
            wait.waitElementToBeClickable(closeConsultancyTableOfDashboard);
            closeConsultancyTableOfDashboard.click();
            if (!containerOfTable.isDisplayed() && !closeConsultancyTableOfDashboard.isDisplayed()) {
                assertFalse(containerOfTable.isDisplayed());
                assertFalse(closeConsultancyTableOfDashboard.isDisplayed());
                assertFalse(hideConsultancyTableOfDashboard.isDisplayed());
                System.out.println("Table was closed");
            } else {
                System.out.println("Table is visible");
            }
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage hideConsultancyStatisticsTable() {
        try {
            info("Hide Consultancy Statistics Table.");
            this.wait.waitElementToBeClickable(hideConsultancyTableOfDashboard);
            this.hideConsultancyTableOfDashboard.click();
            if (!containerOfTable.isDisplayed()) {
                assertFalse(containerOfTable.isDisplayed());
                System.out.println("Table was hided");
            } else {
                System.out.println("Table is visible");
            }
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openHiddenConsultancyStatisticsTable() {
        try {
            info("Open hidden table.");
            wait.waitElementToBeClickable(openConsultancyTableOfDashboard);
            openConsultancyTableOfDashboard.click();
            wait.waitVisibilityOf(containerOfTable);
            assertTrue(containerOfTable.isDisplayed());
            assertTrue(closeConsultancyTableOfDashboard.isDisplayed());
            assertTrue(hideConsultancyTableOfDashboard.isDisplayed());
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public String getTableNameOfDashboard() {
        return tableNameOfDashboard.getText();
    };

    public void checkSectionNames() {
        List<String> expectedNames = new ArrayList<String>();
        expectedNames.add("ACTIVE STUDENTS");
        expectedNames.add("NEW STUDENTS");
        expectedNames.add("FROZEN STUDENTS");
        expectedNames.add("GRADUATED STUDENTS");
        List <String> actualNames = getColumnNameOfDashboard(sectionName);
        assertEquals(expectedNames, actualNames);
    }

    public void checkNamesOfTable() {
        assertEquals("Consultancy Statistics",getTableNameOfDashboard());
        List<String> expectedNamesOfTable = new ArrayList<String>();
        expectedNamesOfTable.add("Consultancy");
        expectedNamesOfTable.add("Active Cases");
        expectedNamesOfTable.add("Frozen Cases");
        expectedNamesOfTable.add("Completed Cases");
        List <String> actualNamesOfTable = getColumnNameOfDashboard(columnsNameOfDashboardTable);
        assertEquals(expectedNamesOfTable, actualNamesOfTable);
    }

    // Deals section
    public MainPage openDeals() {
        try {
            info("Open deal");
            this.dealsSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openNewDealsCategory() {
        try {
            info("Open new deal category");
            this.newDealsCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openArchiveDealCategory() {
        try {
            info("Open archive deal category");
            this.archiveDealCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openAllDealsCategory() {
        try {
            info("Open all deal category");
            this.allDealsCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage compareDealsDataWithDB() {
        info("Validate deals data from UI and DB");

        Map<String, String> dataOfDataBase = new HashMap<String, String>();
        Map<String, String> dataOfTable =  new HashMap<String, String>();

        try {
            dataOfDataBase = dataBase.queryRequest(randomDataOfDeal(), "id", "client_fname", "client_lname",
                    "consultancy", "status", "open_date", "close_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String fullClientName = String.format("%s %s",dataOfDataBase.get("client_lname"), dataOfDataBase.get("client_fname"));
        String dealDate;

        System.out.println(dataOfDataBase.get("id") + " " + dataOfDataBase.get("open_date") + " " + dataOfDataBase.get("close_date"));
        if (dataOfDataBase.get("open_date") == null && dataOfDataBase.get("close_date") == null ) {
            dealDate = null;
        } else if (dataOfDataBase.get("open_date") == null){
            dealDate = String.format("%s" + " - " + "%s",dataOfDataBase.get("open_date"), renderDate(dataOfDataBase.get("close_date")));
        } else if (dataOfDataBase.get("close_date") == null){
            dealDate = String.format("%s" + " - " + "%s",renderDate(dataOfDataBase.get("open_date")), dataOfDataBase.get("close_date"));
        } else {
            dealDate = String.format("%s" + " - " + "%s",renderDate(dataOfDataBase.get("open_date")), renderDate(dataOfDataBase.get("close_date")));
        }

        dataOfDataBase.put("client", fullClientName);
        dataOfDataBase.put("deal status", dataOfDataBase.get("status"));
        dataOfDataBase.put("start date - close date", dealDate);

        dataOfDataBase.remove("client_fname");
        dataOfDataBase.remove("client_lname");
        dataOfDataBase.remove("open_date");
        dataOfDataBase.remove("close_date");
        dataOfDataBase.remove("status");

        searchField(fullClientName);

        impWait();
        wait.waitVisibilityOf(this.valuesOfDealTable.get(0));
        for (int i = 0; i < this.valuesOfDealTable.size(); i++) {
            dataOfTable.put(this.namesColumnsOfDealTable.get(i).getText().toLowerCase(), this.valuesOfDealTable.get(i).getText());
        }

        handlingAssert(dataOfDataBase, dataOfTable);

        return this;
    }


    // Turn section
    public MainPage openTurn() {
        try {
            info("Open TURN");
            this.turnSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openGroupDevelopmentTabOfTurn() {
        try {
            info("Open Group Development Tab Of Turn");
            this.groupDevelopmentTabInTurnSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }


    public MainPage openMentoringTabOfTurn() {
        try {
            info("Open Mentoring Tab Of Turn");
            this.mentoringTabInTurnSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }


    public MainPage openPersonalMentorTabOfTurn() {
        try {
            info("Open Personal Mentor Tab Of Turn");
            this.personalMentorsTabInTurnSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }


    // Client section
    public MainPage openClient() {
        try {
            info("Open client");
            this.clientSection.click();
            wait.waitElementToBeClickable(createClientBtn);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage addFundOfClient() throws Exception {
        info("Add Fund to random client and check in DB");

        Map<String, String> dataFromDB = new HashMap<String, String>();
        Map<String, String> dataOfDBAfterUpdate = new HashMap<String, String>();
        Map<String, String> dataOfUI = new HashMap<String, String>();

        try {
            dataFromDB = dataBase.queryRequest(randomClient(), "id", "fname", "lname", "email",
                    "country", "city", "skype", "phone", "balance", "currency");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String fullName = dataFromDB.get("fname") + " " + dataFromDB.get("lname");

        searchField(fullName);

        Integer getClientId = Integer.parseInt(getValueFromColumnsFromClientSection("id"));
        String getClientIdFromTable = String.format(this.selectorGetIdFromClientTable, getClientId);
        By getClient = By.xpath(getClientIdFromTable);
        wait.waitVisibilityOfElementLocated(getClient);
        this.driver.findElement(getClient).click();

        wait.waitVisibilityOf(addFundsButton);
        addFundsButton.click();
        moneyAmountField.sendKeys("200");
        descriptionOfDepositField.sendKeys("New Test Deposite");
        submitOfAddFunds.click();

        try {
            dataOfDBAfterUpdate = dataBase.queryRequest(getFullDataOfClient(dataFromDB.get("fname"),dataFromDB.get("lname")),
                    "id", "fname", "lname", "email", "country", "city", "skype", "phone", "balance", "currency");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        wait.waitElementToBeClickable(this.clientEmail);
        dataOfUI.put("fname", getText(this.clientFirstName));
        dataOfUI.put("lname", getText(this.clientLastName));
        dataOfUI.put("email", getText(this.clientEmail));
        dataOfUI.put("country", getText(this.clientCountry));
        dataOfUI.put("city", getText(this.clientCity));
        dataOfUI.put("phone", getText(this.clientPhoneNumber));
        dataOfUI.put("skype", getText(this.clientSkype));
        dataOfUI.put("currency", getText(this.clientCurrency));
        dataOfUI.put("balance", getText(this.clientBalance));

        handlingAssert(dataOfDBAfterUpdate, dataOfUI);

        return this;
    }

    public void createNewClientInClientSection(
            String firstName, String lastName, String email, String country,
            String city, String phoneNumber, String skype) {

        clickCreateClient();
        wait.waitTextToBePresentInElement(this.titleInCreateNewClient, "Create Client");
        assertEquals("Create Client", this.titleInCreateNewClient.getText());
        this.clientFirstName.sendKeys(firstName);
        this.clientLastName.sendKeys(lastName);
        this.clientSubmitBtn.submit();
        wait.waitVisibilityOfAllElements(allInputFieldsOfCreateClient);
        this.clientEmail.sendKeys(email);
        this.clientCountry.sendKeys(country);
        this.clientCity.sendKeys(city);
        this.clientPhoneNumber.sendKeys(phoneNumber);
        this.clientSkype.sendKeys(skype);
        wait.waitVisibilityOf(saveButtonForRegistrationNewForm);
        this.saveButtonForRegistrationNewForm.click();

        info("Create new client: " + firstName + " " + lastName);
    }

    public Map<String, String> createClientAndSearchToValidateDataAtDataBase() {
        Map<String, String> dataFromDB = new HashMap<String, String>();
        LinkedHashMap<String, String> clientData = null;

        info("Create new client and assert with Data Base");
        clickCreateClient();
        wait.waitTextToBePresentInElement(this.titleInCreateNewClient, "Create Client");
        assertEquals("Create Client", this.titleInCreateNewClient.getText());
        this.clientFirstName.sendKeys(this.firstName);
        this.clientLastName.sendKeys(this.lastName);
        this.clientSubmitBtn.submit();
        wait.waitVisibilityOfAllElements(allInputFieldsOfCreateClient);
        this.clientEmail.sendKeys(this.email);
        this.clientCountry.sendKeys(this.country);
        this.clientCity.sendKeys(this.city);
        this.clientPhoneNumber.sendKeys(this.phoneNumber);
        this.clientSkype.sendKeys(this.socialNetwork);
        wait.waitVisibilityOf(this.saveButtonForRegistrationNewForm);
        this.saveButtonForRegistrationNewForm.click();

        clientData = new LinkedHashMap<String, String>();
        clientData.put("first_name", this.firstName);
        clientData.put("last_name", this.lastName);
        clientData.put("email", this.email);
        clientData.put("country", this.country);
        clientData.put("city", this.city);
        clientData.put("phone_number", this.phoneNumber);
        clientData.put("skype", this.socialNetwork);

        String fullName = clientData.get("first_name") + " " + clientData.get("last_name");
        searchField(fullName);

        wait.waitVisibilityOf(this.columnNameInClientSection);
        String fullNameFromTable = getValueFromColumnsFromClientSection("name");
        assertEquals(fullName, fullNameFromTable);

        try {
            dataFromDB = dataBase.queryRequest(selectDataFromClient(clientData.get("first_name"), clientData.get("last_name")),
                    "first_name", "last_name", "email", "country", "city", "phone_number", "skype");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        handlingAssert(clientData, dataFromDB);

        return clientData;
    }

    public MainPage updateClientAndSearchAtValidateInDataBase() throws Exception {
        info("Update client and assert with Data Base");
        Map<String, String> dataOfClient = createClientAndSearchToValidateDataAtDataBase();
        Map<String, String> dataOfClientAfterUpdate = new HashMap<String, String>();
        Map<String, String> dataFromDB = new HashMap<String, String>();
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String fullName = dataOfClient.get("first_name") + " " + dataOfClient.get("last_name");

        searchField(fullName);

        Integer getClientId = Integer.parseInt(getValueFromColumnsFromClientSection("id"));
        String getClientIdFromTable = String.format(this.selectorGetIdFromClientTable, getClientId);
        By getClient = By.xpath(getClientIdFromTable);
        wait.textToBePresentInElementLocated(getClient, Integer.toString(getClientId));
        this.driver.findElement(getClient).click();

        wait.waitVisibilityOf(this.clientEmail);
        overwriteField(this.clientEmail, this.email);
        overwriteField(this.clientCountry, this.country);
        overwriteField(this.clientCity, this.city);
        overwriteField(this.clientPhoneNumber, this.phoneNumber);
        overwriteField(this.clientSkype, this.socialNetwork);
        this.saveButtonForRegistrationNewForm.click();

        js.executeScript("window.history.go(-1)");

        dataOfClientAfterUpdate.put("fist_name", this.clientFirstName.getText());
        dataOfClientAfterUpdate.put("last_name", this.clientLastName.getText());
        dataOfClientAfterUpdate.put("email", this.clientEmail.getText());
        dataOfClientAfterUpdate.put("country", this.clientCountry.getText());
        dataOfClientAfterUpdate.put("city", this.clientCity.getText());
        dataOfClientAfterUpdate.put("phone_number", this.clientPhoneNumber.getText());
        dataOfClientAfterUpdate.put("skype", this.clientSkype.getText());

        try {
            dataFromDB = dataBase.queryRequest(selectDataFromClient(dataOfClientAfterUpdate.get("first_name"),
                    dataOfClientAfterUpdate.get("last_name")), "first_name", "last_name", "email", "country", "city", "phone_number", "skype");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        handlingAssert(dataOfClient, dataFromDB);

        return this;
    }


    public String getValueFromColumnsFromClientSection(String columnName) {
        info("Handling column from Client section");
        String result = null;

        switch (columnName) {
            case "id":
                for (WebElement valueColumn : this.valueOfColumnId) {
                    result = valueColumn.getText();
                    System.out.println(this.columnIdInClientSection.getText() + ": " + valueColumn.getText());
                }
                break;
            case "name":
                for (WebElement valueColumn : this.valueOfColumnName) {
                    result = valueColumn.getText();
                    System.out.println(this.columnNameInClientSection.getText() + ": " + valueColumn.getText());
                }
                break;
            default:
                System.out.println("Column not exist in this Page");
                break;
        }

        return result;
    }

    public void clickCreateClient(){
        try {
            info("Click create client button");
            wait.waitVisibilityOf(this.createClientBtn);
            this.createClientBtn.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }


    }

    // Mentors section
    public MainPage openMentorsSection() {
        try {
          info("Open mentor section");
          this.mentorsSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
          return this;
    }

    public MainPage createNewMentorInMentorsSection(String fistName, String lastName, String maxLoad) {
        info("Create new mentor in mentor section params: " + fistName + " " + lastName + " " + maxLoad);
        this.createNewEmployeesButton.click();
        wait.waitVisibilityOf(this.titleInCreateNewEmployees);
        this.firstNameFieldInCreateNewEmployees.sendKeys(fistName);
        this.lastNameFieldInCreateNewEmployees.sendKeys(lastName);
        this.employeeMaxLoadFieldInCreateNewEmployees.sendKeys(maxLoad);
        this.saveNewEmployeesButton.click();
        return this;
    }

    public MainPage getValueFromColumnsFromMentorsSection(String columnName) {
        info("Handling columns from mentor section");

        switch (columnName) {
            case "id":
                for (WebElement valueColumn : this.valueOfColumnId) {
                    System.out.println(this.nameColumnId.getText() + ": " + valueColumn.getText());
                }

                System.out.println("Column size: " + this.valueOfColumnId.size());
                break;
            case "name":
                for (WebElement valueColumn : this.valueOfColumnName) {
                    System.out.println(this.nameColumnName.getText() + ": " + valueColumn.getText());
                }

                System.out.println("Column size: " + this.valueOfColumnName.size());
                break;
            case "workload":
                for (WebElement valueColumn : this.valueOfColumnWorkload) {
                    System.out.println(this.nameColumnWorkload.getText() + ": " + valueColumn.getText());
                }

                System.out.println("Column size: " + this.valueOfColumnWorkload.size());
                break;
            default:
                System.out.println("Column not exist in this Page");
                break;
        }

        return this;
    }

    // Contract section
    public MainPage openContract() {
        try {
            info("Open contract");
            this.contractSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openAllContract() {
        try {
            info("Open all contract category");
            this.allContractCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openTestContract() {
        try {
            info("Open test contract category");
            this.testContractCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }

        return this;
    }

    // Consultancy section
    public MainPage openConsultancy() {
        try {
            info("Open services");
            this.consultancySection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage createNewConsultanciesRequiredFields(
            String titleName, String description, String employeeRate) {

        info("Create new consultancies with required fields");
        this.createNewConsultancyButton.click();
        wait.waitVisibilityOf(this.titleFieldCreateNewConsultanciesInServicesSection);
        this.titleFieldCreateNewConsultanciesInServicesSection.sendKeys(titleName);
        this.descriptionFieldInServicesSection.sendKeys(description);
        this.employeeRateFieldInServicesSection.sendKeys(employeeRate);
        wait.waitVisibilityOf(this.saveButtonForRegistrationNewForm);
        this.saveButtonForRegistrationNewForm.click();

        return this;
    }
    public MainPage createNewConsultanciesWithAllFields(
            String titleName, String description, String priceUAH, String priceEUR, String priceUSD, String employeeRate) {

        info("Create new consultancies with all fields");
        this.createNewConsultancyButton.click();
        wait.waitVisibilityOf(this.titleFieldCreateNewConsultanciesInServicesSection);
        this.titleFieldCreateNewConsultanciesInServicesSection.sendKeys(titleName);
        this.descriptionFieldInServicesSection.sendKeys(description);
        this.priceUAHFieldInServicesSection.sendKeys(priceUAH);
        this.priceEURFieldInServicesSection.sendKeys(priceEUR);
        this.priceUSDFieldInServicesSection.sendKeys(priceUSD);
        this.employeeRateFieldInServicesSection.sendKeys(employeeRate);
        wait.waitVisibilityOf(this.saveButtonForRegistrationNewForm);
        this.saveButtonForRegistrationNewForm.click();

        return this;
    }

    public MainPage  createNewConsultancyWithoutData() {

        info("Create new consultancy w/o data");
        this.createNewConsultancyButton.click();
        wait.waitVisibilityOf(this.titleFieldCreateNewConsultanciesInServicesSection);
        wait.waitElementToBeClickable(this.saveButtonForRegistrationNewForm);
        this.saveButtonForRegistrationNewForm.click();

        isErrorVisible(this.driver, errorByText, getErrorByText(errorLabel,1));
        isErrorVisible(this.driver, errorByText, getErrorByText(errorLabel,2));

        try {
            assertEquals("size must be between 2 and 50", getErrorByText(errorLabel,1));
            assertEquals("It is required field", getErrorByText(errorLabel,2));
            assertEquals("Failed to convert property value of type java.lang.String to required type long for property employeeRate.amount; nested exception is java.lang.NumberFormatException: For input string: \"\"\n",
                    getErrorByText(errorLabel,3));
            info("Errors: Title - " + getErrorByText(errorLabel,1) + " Description: " +
                    getErrorByText(errorLabel,2) + " -> is PRESENT" +
                    getErrorByText(errorLabel, 3) + " -> is PRESENT");
        } catch (AssertionError e) {
            error(e.getMessage());
        }

        return this;
    }

    public MainPage typeInvalidDataDuringCreatingNewConsultancy(
            String titleName, String priceUAH, String priceEUR, String priceUSD, String employeeRate) {

        info("Create new consultancies with invalid data");
        this.createNewConsultancyButton.click();
        wait.waitVisibilityOf(this.titleFieldCreateNewConsultanciesInServicesSection);
        this.titleFieldCreateNewConsultanciesInServicesSection.sendKeys(titleName);
        this.priceUAHFieldInServicesSection.sendKeys(priceUAH);
        this.priceEURFieldInServicesSection.sendKeys(priceEUR);
        this.priceUSDFieldInServicesSection.sendKeys(priceUSD);
        this.employeeRateFieldInServicesSection.sendKeys(employeeRate);
        wait.waitElementToBeClickable(this.saveButtonForRegistrationNewForm);
        this.saveButtonForRegistrationNewForm.click();

        isErrorVisible(this.driver, errorByText, getErrorByText(errorLabel,1));
        isErrorVisible(this.driver, errorByText, getErrorByText(errorLabel,2));

        try {
            assertEquals("size must be between 2 and 50", getErrorByText(errorLabel,1));
            assertEquals("It is required field", getErrorByText(errorLabel,2));
            info("Errors: Title - " + getErrorByText(errorLabel,1) + " Description: " +
                    getErrorByText(errorLabel,2) + " -> is PRESENT");
        } catch (AssertionError e) {
            error(e.getMessage());
        }

        return this;
    }

    public MainPage openAndCloseCreateNewConsultancy(String titleName) {
        info("Open and close new consultancy page");
        this.createNewConsultancyButton.click();
        wait.waitVisibilityOf(this.titleFieldCreateNewConsultanciesInServicesSection);
        this.titleFieldCreateNewConsultanciesInServicesSection.sendKeys(titleName);
        this.goBakcButoonInServicesSection.click();

        return this;
    }

    public MainPage deleteInCreateNewConsultanciesFieldAllFields(String titleName) {
        info("Delete in create new consultancies field all fields");
        this.createNewConsultancyButton.click();
        wait.waitVisibilityOf(this.titleFieldCreateNewConsultanciesInServicesSection);
        this.titleFieldCreateNewConsultanciesInServicesSection.sendKeys(titleName);
        this.deleteButtonInServicesSection.click();
        this.driver.switchTo().alert().accept();

        return this;
    }

    public MainPage validateContractDataFromTableAndDataBase() throws Exception{
        info("Validate contract data from table and DataBase");

        Map<String, String> dataOfDataBase = new HashMap<String, String>();
        Map<String, String> dataOfTable =  new HashMap<String, String>();

        try {
            dataOfDataBase = dataBase.queryRequest(contactInfoOfClient("Bobby", "Drake"),"contract", "client_first_name",
                    "client_last_name", "consultancy", "employee_first_name", "employee_last_name", "contract_date",
                    "type", "status");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String fullNameOfClient = dataOfDataBase.get("client_first_name") + " " + dataOfDataBase.get("client_last_name");
        String fullNameEmployee = dataOfDataBase.get("employee_first_name") + " " + dataOfDataBase.get("employee_last_name");

        dataOfDataBase.put("client", fullNameOfClient);
        dataOfDataBase.put("mentor", fullNameEmployee);
        dataOfDataBase.put("contract date", renderDateDBToUI(dataOfDataBase.get("contract_date")));

        dataOfDataBase.remove("client_first_name");
        dataOfDataBase.remove("client_last_name");
        dataOfDataBase.remove("employee_first_name");
        dataOfDataBase.remove("employee_last_name");
        dataOfDataBase.remove("contract_date");

        searchField(fullNameOfClient);

        impWait();
        wait.waitVisibilityOf(this.valueOfColumnContactId);
        for (int i = 0; i < this.valuesOfContractTable.size(); i++) {
            dataOfTable.put(this.namesOfColumns.get(i).getText().toLowerCase(), this.valuesOfContractTable.get(i).getText());
        }

        handlingAssert(dataOfDataBase, dataOfTable);

        return this;
    }

    // Invoices section
    public MainPage openInvoices() {
        try {
            info("Open invoices");
            this.invoicesSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openAllInvoices() {
        try {
            info("Open all invoices");
            this.allInvoicesCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openPutUpInvoices() {
        try {
            info("Open put up invoices");
            this.putUpInvoicesCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openOverdueInvoices() {
        try {
            info("Open overdue invoices");
            this.overdueInvoicesCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage compareInvoiceOfUIWithDB() {
        info("Validate invoice data with UI and DB");

        Map<String, String> dataOfDB = new HashMap<String, String>();
        Map<String, String> dataOfTable =  new HashMap<String, String>();

        try {
            dataOfDB = dataBase.queryRequest(randomDataOfInvoice(), "id", "client_fname", "client_lname",
                    "contract_id", "currency", "money_amount", "payment_date");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String fullClientName = String.format("%s %s",dataOfDB.get("client_fname"), dataOfDB.get("client_lname"));
        String balanceWithCurrencyCode = String.format("%s %s",dataOfDB.get("currency"), dataOfDB.get("money_amount"));

        dataOfDB.put("client", fullClientName);
        dataOfDB.put("payment amount", balanceWithCurrencyCode);
        dataOfDB.put("payment date", dataOfDB.get("payment_date"));
        dataOfDB.put("contract id", dataOfDB.get("contract_id"));

        dataOfDB.remove("client_fname");
        dataOfDB.remove("client_lname");
        dataOfDB.remove("currency");
        dataOfDB.remove("money_amount");
        dataOfDB.remove("contract_id");
        dataOfDB.remove("payment_date");

        searchField(fullClientName);

        impWait();
        wait.waitVisibilityOf(this.valueOfColumnInvoiceId);
        for (int i = 0; i < this.valuesOfInvoiceTable.size(); i++) {
            dataOfTable.put(this.namesColumnsOfInvoiceTable.get(i).getText().toLowerCase(), this.valuesOfInvoiceTable.get(i).getText());
        }
        dataOfTable.remove("payment status");

        handlingAssert(dataOfDB, dataOfTable);

        return this;
    }

    // Salary section
    public MainPage openSalaries() {
        try {
            info("Open SALARIES");
            this.salariesSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openSalary() {
        try {
            info("Open SALARY");
            this.salaryCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage openSalaryFormation() {
        try {
            info("---> Open SALARY FORMATION");
            this.salaryFormationSalaryCategory.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    // Payment section
    public MainPage openPayments() {
        try {
            info("---> Open PAYMENTS");
            this.paymentsSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    // Users section
    public MainPage openUsers() {
        try {
            info("---> Open users");
            this.usersSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
}


    public MainPage addNewUser(String name, String email, String password) {
        info("---> Open ADD NEW USER");
        this.addNewUserButton.click();
        wait.waitVisibilityOf(this.titleInPopupCreateNewUser);
        this.userNameInAddNewUser.sendKeys(name);
        this.emailInAddNewUser.sendKeys(email);
        this.passwordInAddNewUser.sendKeys(password);
        this.createUserButtonInAddNewUser.click();

        return this;
    }

    // Cash Flow section
    public MainPage openCashFlow() {
        try {
            info("---> Open CASH FLOW");
            this.cashFlowSection.click();
            wait.waitTextToBePresentInElement(this.headerNameOfCashFlow, "Cash Flow Report");
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage generateReportOfCashFlow(int optionNumber,String beginDate, String endDate){
        try {
            assertEquals("Cash Flow Report", this.headerNameOfCashFlow.getText());
            Select dropDownConsultancy = new Select(this.consultancyField);
            dropDownConsultancy.selectByIndex(optionNumber);
            this.beginDateField.sendKeys(beginDate);
            this.endDateField.sendKeys(endDate);
            this.generateReportButton.click();
            info("---> Generate report");
            wait.waitVisibilityOf(headerOfTables.get(3));
            matchHeaderOfTables();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public void matchHeaderOfTables() {
        List<String> expectedHeaders = new ArrayList<String>();
        expectedHeaders.add("Inlow");
        expectedHeaders.add("Outflow");
        expectedHeaders.add("Total inflow");
        expectedHeaders.add("Total outflow");
        expectedHeaders.add("Balance (inflow - outflow)");
        for (int i = 0; i < expectedHeaders.size(); i++) {
            assertEquals(expectedHeaders.get(i), headerOfTables.get(i).getText());
        }
    }

    // Extra field section
    public MainPage openExtraField() {
        try {
            info("Open EXTRA FIELD");
            this.wait.waitTextToBePresentInElement(this.extraFieldSection,"Доп. поля");
            this.extraFieldSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public String createNewEmployeeField(String nameField) {
        try {
            info("Open NEW EMPLOYEE FIELD");
            this.createNewEmployeeFieldInExtraFieldSection.click();
            wait.waitVisibilityOf(this.titleNewEmployeeFieldPopupInExtraFieldSection);
            this.driver.switchTo().alert();
            this.nameFieldNewEmployeeFieldPopupInExtraFieldSection.sendKeys(nameField);
            this.saveButtonNewEmployeeFieldPopupInExtraFieldSection.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }

        return nameField;
    }

    public String createNewClientField(String nameField) {
       try {
           info("Create NEW CLIENT FIELD");
            this.createNewClientFieldInExtraFieldSection.click();
            wait.waitVisibilityOf(this.titleNewClientFieldPopupInExtraFieldSection);
            this.driver.switchTo().alert();
            this.nameFieldNewClientFieldPopupInExtraFieldSection.sendKeys(nameField);
            this.saveButtonNewClientFieldPopupInExtraFieldSection.click();
       } catch (WebDriverException e) {
            error(e.getMessage());
       }

        return nameField;
    }

    public MainPage deleteExtraFieldOfEmployee(String nameField){
        try {
            info("Delete NEW EMPLOYEE FIELD");
            createNewEmployeeField(nameField);
            wait.waitVisibilityOf(this.createNewEmployeeFieldInExtraFieldSection);
            this.searchOfEmployeeField.sendKeys(nameField);
            wait.waitVisibilityOf(this.getDataFromTableOfEmployee.get(2));
            assertEquals(nameField, this.getDataFromTableOfEmployee.get(2));
            this.deleteButtonOfEmployeeExtraField.click();
            wait.alertIsPresent();
            this.driver.switchTo().alert().accept();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }

        return this;
    }

    public MainPage deleteExtraFieldOfClient(String nameField){
        try {
            info("Delete NEW CLIENT FIELD");
            createNewClientField(nameField);
            wait.waitVisibilityOf(this.createNewClientFieldInExtraFieldSection);
            this.searchOfClientField.sendKeys(nameField);
            wait.waitVisibilityOf(this.getDataFromTableOfClient.get(2));
            assertEquals(nameField, this.getDataFromTableOfClient.get(2));
            this.deleteButtonOfClientExtraField.click();
            wait.alertIsPresent();
            this.driver.switchTo().alert().accept();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }

        return this;
    }


    // Global components
    public void clickBurgerButton() {
        try {
            info("Click BURGER button");
            this.burgerButton.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
    }

    public MainPage openUserProfile() {
        try {
            info("Open USER profile");
            this.userProfile.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage signOutFromSystem() {
        try {
            info("Sign out from System");
            this.userProfile.click();
            wait.waitElementToBeClickable(this.signOutUserProfile);
            this.signOutUserProfile.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public MainPage searchField(String input) {
        try {
            info("Select search field");
            wait.waitVisibilityOf(this.searchField);
            this.searchField.sendKeys(input);
            this.searchBtn.click();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
        return this;
    }

    public String getText(WebElement element) {
        String textFromField = null;
        try {
            info("Get text from field");
            textFromField = element.getText();
        } catch (WebDriverException e) {
            error(e.getMessage());
        }

        return textFromField;
    }

    public void overwriteField(WebElement element, String data) {
        try {
            info("Overwrite Field");
            element.clear();
            element.sendKeys(data);
        } catch (WebDriverException e) {
            error(e.getMessage());
        }
    }

    public List<String> getColumnNameOfDashboard(List<WebElement> names) {
        List<String> name = new ArrayList<String>();

        for (int i = 0; i < names.size(); i++) {
            name.add(names.get(i).getText());
        }
        return name;
    }
}
