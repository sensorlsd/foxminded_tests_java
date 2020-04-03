package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static utilities.Log.error;
import static utilities.Log.info;

public class WaitHelpers {
    private WebDriverWait wait = null;

    public WaitHelpers(WebDriver driver) {
        this.wait = new WebDriverWait(driver, 10);
    }

    public static void impWait() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitElementSelectionState(WebElement element, boolean selected) {
        try {
            info("Wait element selection state is: " + element + " : " + selected);
            this.wait.until(ExpectedConditions.elementSelectionStateToBe(element,selected));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void waitTextToBePresentInElement(WebElement element, String text) {
        try {
            info("Wait text to be present in element : " + element + " : " + text);
            this.wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void textToBePresentInElementLocated(By element, String text) {
        try {
            info("Wait text to be present in element located : " + element + " : " + text);
            this.wait.until(ExpectedConditions.textToBePresentInElementLocated(element, text));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void textToBePresentInElementValue(WebElement element, String text) {
        try {
            info("Wait text to be present in element value : " + element + " : " + text);
            this.wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void titleIs(String text) {
        try {
            info("Title is: " + text);
            this.wait.until(ExpectedConditions.titleIs(text));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void alertIsPresent() {
        try {
            info("Alert is present.");
            this.wait.until(ExpectedConditions.alertIsPresent());
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void titleContains(String text) {
        try {
            info("Title contains: " + text);
            this.wait.until(ExpectedConditions.titleContains(text));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void waitVisibilityOfAllElements(WebElement element) {
        try {
            info("Wait visibility Of all elements: " + element);
            this.wait.until(ExpectedConditions.visibilityOfAllElements(element));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void waitVisibilityOfAllElementsLocatedBy(By element) {
        try {
            info("Wait visibility of all elements located By: " + element);
            this.wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(element));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void waitVisibilityOfElementLocated(By element) {
        try {
            info("Wait visibility of element located: " + element);
            this.wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void waitVisibilityOf(WebElement element) {
        try {
            info("Wait visibility Of " + element);
            this.wait.until(ExpectedConditions.visibilityOf(element));
        } catch (ElementNotVisibleException e) {
            error(e.getMessage());
        }
    }

    public void waitElementToBeClickable(WebElement element) {
        try {
            info("Wait when element to be clickable " + element);
            this.wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (ElementClickInterceptedException e) {
            error(e.getMessage());
        }
    }
}
