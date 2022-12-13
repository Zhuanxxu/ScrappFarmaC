package cleanTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import singletonSession.Session;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static utils.Attach.takeSnapShot;
import static utils.ExcelGenerator.excelSheet;
//import static utils.ExcelGenerator.excelSheet;

public class TestWithCucumber extends TestBaseCucumber {



    @Given("Entro a la pagina principal")
    public void entroALaPaginaPrincipal() {

    }

    @When("Extraigo los precios")
    public void extraigoLosPrecios() throws InterruptedException {

        do {
            Thread.sleep(1300);
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            Thread.sleep(1000);
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

    }

    @Then("Los paso a un excel")
    public void losPasoAUnExcel() throws IOException {

        excelSheet(articulos,"Farmacia");
    }

    @Given("Entro a la pagina principal de nuetricion")
    public void entroALaPaginaPrincipalDeNuetricion() {
        Session.getInstance().getBrowser().navigate().to("https://www.farmacity.com/nutricion-y-deportes");
    }

    @When("Extraigo los precios de nutricion")
    public void extraigoLosPreciosDeNutricion() throws InterruptedException {
        articulos.clear();
        do {
            Thread.sleep(2000);
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            Thread.sleep(1000);
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));
    }

    @Then("Paso los articulso de nutrcion a excel")
    public void pasoLosArticulsoDeNutrcionAExcel() throws IOException {

        excelSheet(articulos,"Nutricion y deportes");
    }
}
