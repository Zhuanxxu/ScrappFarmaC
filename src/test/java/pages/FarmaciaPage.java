package pages;

import controlSelenium.Button;
import controlSelenium.Control;
import controlSelenium.Label;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import singletonSession.Session;

import java.util.ArrayList;
import java.util.List;

import static utils.ByUtils.getXpath;

public class FarmaciaPage {
    public Label articlesArrayLabel = new Label(By.xpath("//article[@id=\"main-content\"]//div[@id]/*/ul/li//a[@class=\"product-card-information\"]"));
    public Button nextPageBtn = new Button(By.xpath("(//li[contains(@class,\"next\")])[2]"));

    public void addItemsToArray(List<List<String>> articulos){
        JavascriptExecutor js = (JavascriptExecutor) Session.getInstance().getBrowser();
        for (int i = 1; i < articlesArrayLabel.getControls().size(); i++) {

                List<String> articulo = new ArrayList<>();
            articulo.add(getTextFromDescription(i));
            articulo.add(getTextFromBestPrice(i));
            articulo.add(getTextFromOldPrice(i));


//            System.out.println(js.executeScript("return document.querySelector('li:nth-child('+arguments[0]+') > div > div > a.product-card-information > div.hightlight').hasChildNodes();",i).toString());
            if((Boolean) js.executeScript("return document.querySelector('li:nth-child('+arguments[0]+') > div > div > a.product-card-information > div.hightlight').hasChildNodes();",i))
                articulo.add(getTextFromPromo(i));
            else
                articulo.add("");

            articulo.add(getTextFromImg(i));
            articulos.add(articulo);
            }
    }
    public String getTextFromDescription( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + "//div[@class=\"product-card-name\"])[" +i +"]")).getText();
    }
    public String getTextFromBestPrice( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + "//span[@class=\"best-price\"])[" + i + "]")).getText().replace("$ ","");
    }
    public String getTextFromOldPrice( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) +"//span[@class=\"old-price\"])[" + i +"]")).getText();
    }
    public String getTextFromImg( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + "//..//span[@class=\"primary-image\"]/img)[" + i +"]")).getAttributeValue("src");
    }
    public String getTextFromPromo(int i){


        try{
            Control controlToPromo = new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + ")[" + i + "]/div/div/div[contains(@class,\"lines-fl\")]"));
            if(controlToPromo.hasAttributeValue("class","off")){
                Control discountNumber =  new Control(By.xpath(getXpath(controlToPromo.getControl()) + "/p"));
                return discountNumber.getAttributeValue("textContent");
            } else if (controlToPromo.hasAttributeValue("class","2x1")) {
                return "2x1";
            } else {
                return "";
            }
        } catch (Exception e) {
            return "";
        }

    }
}
