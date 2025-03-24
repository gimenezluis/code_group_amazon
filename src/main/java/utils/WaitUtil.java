package utils;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtil {

    private static final int DEFAULT_TIMEOUT = 10;

    public static WebElement waitForVisibility(By locator) {
        WebDriver driver = DriverUtil.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static boolean waitUntilVisible(By locator) {
        try {
            waitForVisibility(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitUntilInvisible(By locator) {
        WebDriver driver = DriverUtil.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public static void waitUntilListHasItems(By locator) {
        WebDriver driver = DriverUtil.getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(driver1 -> {
            List<WebElement> elements = driver1.findElements(locator);
            return elements.size() > 0;
        });
    }

}
