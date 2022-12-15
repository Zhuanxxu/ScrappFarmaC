package pages;

import controlSelenium.Button;
import controlSelenium.Control;
import controlSelenium.Label;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static utils.ByUtils.getXpath;

public class SuperPage implements IPageInteface{

    public Label articlesArrayLabel = new Label(By.xpath("//article[@id=\"main-content\"]//div[@id]/*/ul/li//a[@class=\"product-card-information\"]"));
    public Label firstItemDescription = new Label(By.xpath("(//article[@id=\"main-content\"]//div[@id]/*/ul/li//a[@class=\"product-card-information\"]//div[@class=\"product-card-name\"])[" +1 +"]"));
    public Label articlesArrayLabelWithoutNonStockItems = new Label(By.xpath("//article[@id=\"main-content\"]//div[@id]/*/ul/li//a[@class=\"product-card-information\"]//span[@class=\"best-price\"]"));
    public Button nextPageBtn = new Button(By.xpath("(//li[contains(@class,\"next\")])[2]"));
    public Label hasPromoElementLabel = new Label(By.cssSelector("li:nth-child(1) > div > div > a.product-card-information > div.hightlight"));

    public Button nextNumberPageBtn(String i){
        return new Button(By.xpath("//li[@class=\"page-number\" and contains(text(),i)]"));
    }
    @Override
    public void addItemsToArray(List<List<String>> articulos) throws InterruptedException {

        System.out.println(getTextFromDescription(1));

        for (int i = 1; i <= articlesArrayLabelWithoutNonStockItems.getControls().size(); i++) {

            List<String> articulo = new ArrayList<>();
            articulo.add(getTextFromDescription(i));
            articulo.add(getTextFromBestPrice(i));
            articulo.add(getTextFromOldPrice(i));


//            System.out.println(js.executeScript("return document.querySelector('li:nth-child('+arguments[0]+') > div > div > a.product-card-information > div.hightlight').hasChildNodes();",i).toString());
            Thread.sleep(200);
            if(verifyIfThereIsAnyPromo(i))
                articulo.add(getTextFromPromo(i));
            else
                articulo.add("");

            articulo.add(getTextFromImg(i));
            articulos.add(articulo);
        }
    }
    @Override
    public String getTextFromDescription(int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + "//div[@class=\"product-card-name\"])[" +i +"]")).getText();
    }
    @Override
    public String getTextFromBestPrice( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + "//span[@class=\"best-price\"])[" + i + "]")).getText().replace("$ ","");
    }
    @Override
    public String getTextFromOldPrice( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) +"//span[@class=\"old-price\"])[" + i +"]")).getText().replace("$ ","");
    }
    @Override
    public String getTextFromImg( int i){
        return new Control(By.xpath("(" + getXpath(articlesArrayLabel.getControl()) + "//..//span[@class=\"primary-image\"]/img)[" + i +"]")).getAttributeValue("src");
    }
    @Override
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
    @Override
    public boolean verifyIfThereIsAnyPromo(int i) throws InterruptedException {
        return false;
    }
}
