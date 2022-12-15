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

import static controlSelenium.Control.waitAjaxToFinishAsync;
import static java.lang.String.valueOf;
import static utils.Attach.takeSnapShot;
import static utils.ExcelGenerator.excelSheet;
//import static utils.ExcelGenerator.excelSheet;

public class TestWithCucumber extends TestBaseCucumber {



    @Given("Entro a la pagina de farmacia")
    public void entroALaPaginaDeFarmacia(String url) {
        System.out.println(url);
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios")
    public void extraigoLosPrecios() throws InterruptedException {

        do {
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            waitAjaxToFinishAsync();
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Los paso a un excel")
    public void losPasoAUnExcel() throws IOException {

        excelSheet(articulos,"Farmacia");
    }

    @Given("Entro a la pagina de nutricion")
    public void entroALaPaginaDeNutricion(String url) {
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios de nutricion")
    public void extraigoLosPreciosDeNutricion() throws InterruptedException {
        articulos.clear();
        do {
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            waitAjaxToFinishAsync();
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Paso los articulso de nutrcion a excel")
    public void pasoLosArticulsoDeNutrcionAExcel() throws IOException {

        excelSheet(articulos,"Nutricion y deportes");
    }


    @Given("Entro a la pagina de electrosalud")
    public void entroALaPaginaDeElectrosalud(String url) {
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios de electrosalud")
    public void extraigoLosPreciosDeElectrosalud() throws InterruptedException {
        articulos.clear();
        do {
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            waitAjaxToFinishAsync();
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Paso los articulos de electrosalud a excel")
    public void pasoLosArticulosDeElectrosaludAExcel() throws IOException {
        excelSheet(articulos,"Electrosalud");
    }

    @Given("Entro a la pagina de cuidado bucal")
    public void entroALaPaginaDeCuidadoBucal(String url) {
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios de cuidado bucal")
    public void extraigoLosPreciosDeCuidadoBucal() throws InterruptedException {
        articulos.clear();
        do {
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            waitAjaxToFinishAsync();

        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Paso los articulos de cuidado bucal a excel")
    public void pasoLosArticulosDeCuidadoBucalAExcel() throws IOException {
        excelSheet(articulos,"Cuidado bucal");
    }

    @Given("Entro a la pagina de cuidado del rostro")
    public void entroALaPaginaDeCuidadoDelRostro(String url) {
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios de cuidado del rostro")
    public void extraigoLosPreciosDeCuidadoDelRostro() throws InterruptedException {

        articulos.clear();
        do {
            Thread.sleep(1000);
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            Thread.sleep(1000);
//            waitAjaxToFinishAsync();
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Paso los articulos de cuidado del rostro a excel")
    public void pasoLosArticulosDeCuidadoDelRostroAExcel() throws IOException {
        excelSheet(articulos,"Cuidado rostro");
    }

    @Given("Entro a la pagina de cuidado del cuerpo")
    public void entroALaPaginaDeCuidadoDelCuerpo(String url) {
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios de cuidado del cuerpo")
    public void extraigoLosPreciosDeCuidadoDelCuerpo() throws InterruptedException {
        articulos.clear();
        do {
            Thread.sleep(800);
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            Thread.sleep(800);
//            waitAjaxToFinishAsync();
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Paso los articulos de cuidado del cuerpo a excel")
    public void pasoLosArticulosDeCuidadoDelCuerpoAExcel() throws IOException {
        excelSheet(articulos,"Cuidado cuerpo");
    }

    @Given("Entro a la pagina de cuidado solar")
    public void entroALaPaginaDeCuidadoSolar(String url) {
        Session.getInstance().getBrowser().get(url);
    }

    @When("Extraigo los precios de cuidado solar")
    public void extraigoLosPreciosDeCuidadoSolar() throws InterruptedException {
        articulos.clear();
        do {
            farmaciaPage.addItemsToArray(articulos);
            farmaciaPage.nextPageBtn.doubleClickAction();
            farmaciaPage.nextPageBtn.doubleClickAction();
            waitAjaxToFinishAsync();
        }while (!farmaciaPage.nextPageBtn.hasAttributeValue("class","pgEmpty"));

        //Agrego los items de la ultima pagina
        farmaciaPage.addItemsToArray(articulos);
    }

    @Then("Paso los articulos de cuidado solar a excel")
    public void pasoLosArticulosDeCuidadoSolarAExcel() throws IOException {
        excelSheet(articulos,"Cuidado solar");
    }
}
