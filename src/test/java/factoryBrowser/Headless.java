package factoryBrowser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class Headless implements IBrowser {
    @Override
    public WebDriver create() {
        //Esto settea el path al archivo driver webdriver
//        System.setProperty("webdriver.chrome.driver", "src/test/resources/driver/chromedriver.exe");
        Map<String,Object> preferences= new HashMap<>();
        preferences.put("profile.default_content_settings.popups", 0);

        preferences.put("download.default_directory",System.getProperty("user.dir")+"\\output");



        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs",preferences);
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.manage().window().maximize();
        //Para correr en headless
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));*/

        return driver;
    }
}
