package DriverManager;

import Helpers.PropertyHelper;
import org.apache.log4j.BasicConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DriverWrapper {

    private WebDriver driver = null;
    private int TIMEOUT = 10;

    public WebDriver init(String browser) throws Exception {
        PropertyHelper prop = new PropertyHelper();

        if (browser.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + prop.getChromeDriver());
            this.driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + prop.getFireFoxDriver());
            this.driver = new FirefoxDriver();
        }

        this.driver.manage().timeouts().implicitlyWait(this.TIMEOUT, TimeUnit.SECONDS);
        this.driver.get(prop.getURL());
        this.driver.manage().window().maximize();
        this.driver.manage().deleteAllCookies();

        BasicConfigurator.configure();

        return this.driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void pageFactoryInit(Object pageName) {
        PageFactory.initElements(this.driver, pageName);
    }

    public void assertTitle(String expectTitle) {
        String title = this.driver.getTitle();
        assertEquals(expectTitle, title);
    }

    public void close() {
        if (this.driver != null) {
            this.driver.quit();
            System.out.println("Test was finished.... View log and make your choice..");
        }
    }
}
