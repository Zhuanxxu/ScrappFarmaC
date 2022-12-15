package factoryBrowser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Chrome implements IBrowser {

    @Override
    public WebDriver create() {
        //Esto settea el path al archivo driver webdriver
        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        Map<String,Object> preferences= new HashMap<>();
        preferences.put("profile.default_content_settings.popups", 0);

        preferences.put("download.default_directory",System.getProperty("user.dir")+"\\output");
        //Para correr en headless
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");
        options.setExperimentalOption("prefs",preferences);
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));

        //Maximizar ventana
//        driver.manage().window().setPosition(new Point(2000, 1));
        driver.manage().window().maximize();
        return driver;
    }
}
