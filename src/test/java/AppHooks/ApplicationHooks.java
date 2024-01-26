package AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.qa.factory.DriverFactory;
import com.qa.utils.PropertiesReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

    private WebDriver driver;
    Properties prop;

    @Before()
    public void loadProperties() throws Throwable {
        PropertiesReader configReader = new PropertiesReader();
        prop = configReader.init_prop();
        String browserName = prop.getProperty("browser");
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);
        driver.get(prop.getProperty("appURL"));
    }


    @After()
    public void quitBrowser(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Screenshot");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        driver.close();
    }

}
