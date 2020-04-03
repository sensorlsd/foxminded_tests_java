package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utilities.Log.info;

public class HandlingErrors {

    public static boolean isAlertPresent(WebDriver driver) {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException Ex) {
            return false;
        }
    }

    public static String getErrorByText(List<WebElement> errorLabel, int number) {
        info("Get error list");
        return errorLabel.get(number - 1).getText();
    }

    public static boolean isErrorVisible(WebDriver driver, String errorPath, String message) {
        info("Check visible error.");
        String error = String.format(errorPath, message);
        return driver.findElements(By.xpath(error)).size() > 0
                && driver.findElements(By.xpath(error)).get(0).isDisplayed();
    }
}
