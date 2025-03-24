package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverUtil {

    public static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            // options.addArguments("--headless");
            // options.addArguments("--window-size=1920,1080");
            // options.addArguments("--disable-gpu");
            // options.addArguments("--no-sandbox");
            // options.addArguments("--disable-dev-shm-usage");

            driver = new ChromeDriver(options);
            driver.manage().window().setSize(new Dimension(1920, 1080));
        }

        return driver;
    }

    public static void killDrivers() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }

    public static String getUrl(String url) {
        getDriver().get(url);
        return url;
    }

    public static void setViewport(int width, int height) {
        driver.manage().window().setSize(new Dimension(width, height));
    }

    public static void setViewportDesktop() {
        setViewport(1920, 1080);
    }

    public static void setViewportTablet() {
        setViewport(1024, 768);
    }

    public static void setViewportMobile() {
        setViewport(375, 667);
    }
}
