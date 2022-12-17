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



@IncludeEngines("cucumber")
@Suite
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.FILTER_TAGS_PROPERTY_NAME, value = "@cuidadoSolar")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, value = "cleanTest")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, value = "json:docs/cucumber_report.json")
public class TestRunner {

    /**
     * Se ejecuta al final final y ejecuta un comnado en cmd para generar un reporte que usa node
     */
    @AfterAll
    public static void after_all() {
        try {

            String[] cmd = {"cmd.exe", "/c", "npm run report"};
            Runtime.getRuntime().exec(cmd);

        } catch (Exception ex) {

            ex.printStackTrace();
        }
    }

    /**
     * Se ejecuta primero e inicia el archivo excel donde se van a guardar los datos
     */
    @BeforeAll
    public static void before_all() throws Exception{
        initExcel();
    }

    /**
     * Lo dejo por si se llega a usar pero actualmente no esta en uso
     */
    @Before
    public void setUp() throws Exception {
//        Session.getInstance().getBrowser().get(GetProperties.getInstance().getHost());

    }

    /**
     * Guarda el excel luego de la ejecucion de cada escenario y saca una screen si el test fallo
     * por ultimo cierra el browser
     */
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
