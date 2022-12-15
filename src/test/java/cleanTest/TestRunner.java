package cleanTest;

import io.cucumber.java.*;
import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import singletonSession.Session;
import utils.GetProperties;

import static utils.ExcelGenerator.initExcel;
import static utils.ExcelGenerator.saveExcel;


/*@Cucumber
@CucumberOptions(
        features = "src/test/java/features/",
        glue = {"src/test/java/cleanTest/todoIstCucumber"},
        plugin = {"json:test/report/cucumber_report.json"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
//        tags = {"@backend"}
)*/
@IncludeEngines("cucumber")
@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@cuidadoSolar")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "cleanTest")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "json:docs/cucumber_report.json")
public class TestRunner {

    @AfterAll
    public static void after_all() {
        try {

            String[] cmd = {"cmd.exe", "/c", "npm run report"};
            Runtime.getRuntime().exec(cmd);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }
    @BeforeAll
    public static void before_all() throws Exception{
        initExcel();
    }
    @Before
    public void setUp() throws Exception {
//        Session.getInstance().getBrowser().get(GetProperties.getInstance().getHost());

    }

    @After
    public void cleanup(Scenario scenario) throws Exception {
        saveExcel();
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) Session.getInstance().getBrowser()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
//        MyScreenRecorder.stopRecording();
        Session.getInstance().closeBrowser();
    }

}
