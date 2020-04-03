
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.ExcludeTags;
import org.junit.platform.suite.api.IncludeTags;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({
        Tests.Registration.RegistrationTest.class
})
@IncludeTags({"login-page", "login", "registration"})
@ExcludeTags("main-page")
public class LoginPageTestsSuite {
}
