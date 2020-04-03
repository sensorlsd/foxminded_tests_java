import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        Tests.Accounts.AccountTests.class,
        Tests.Consultancy.ConsultanciesTests.class,
        Tests.Contracts.ContractTest.class,
        Tests.Deal.DealTests.class,
        Tests.CashFlow.CashFlowTests.class,
        Tests.Clients.ClientsTests.class,
        Tests.ExtraFields.ExtraFieldsTests.class,
        Tests.Dashboard.DashboardTests.class
})
@IncludeTags({"accounts", "consultancy", "contacts", "deal", "cash-flow",
               "clients", "extra-field", "dashboards"})
@ExcludeTags("login-page")
public class MainPageTestsSuite {

}
