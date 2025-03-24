package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.DriverUtil;

public class Hooks {

    @Before
    public void beforeScenario(Scenario scenario) {
        DriverUtil.getDriver(); // Inicializa o driver
    }

    @After
    public void afterScenario(Scenario scenario) {
        WebDriver driver = DriverUtil.getDriver();

        // Tira print e anexa sempre
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");
        }

        DriverUtil.killDrivers(); // Finaliza a sessão
    }
}
