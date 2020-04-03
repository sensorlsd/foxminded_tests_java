package DriverManager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInfo;
import utilities.Log;

import static utilities.SeleniumScreenShot.takeScreenShot;

@DisplayName("Preconditions and Postconditions")
public class BrowserController {

    public static DriverWrapper driverWrapper = new DriverWrapper();

    @BeforeEach
    public void setUp() throws Exception {
        Log.startLog("---> Test is starting. <---");
        driverWrapper.init("chrome");
    }

    @AfterEach
    public void tearDown(TestInfo testInfo) {
        takeScreenShot(driverWrapper.getDriver(),testInfo);
        Log.endLog("---> Test was end. <---");
        driverWrapper.close();
    }

}
