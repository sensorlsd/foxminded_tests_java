package utilities;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class SeleniumScreenShot {

    public static void takeScreenShot(WebDriver webDriver, TestInfo testInfo) {
        File takeScreen = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(takeScreen, new File("ScreenShots/" + testInfo.getDisplayName() + ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
