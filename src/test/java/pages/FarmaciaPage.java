package pages;

import org.openqa.selenium.JavascriptExecutor;
import singletonSession.Session;

public class FarmaciaPage extends SuperPage implements IPageInteface  {

    @Override
    public boolean verifyIfThereIsAnyPromo(int i) throws InterruptedException {
        Thread.sleep(200);
        JavascriptExecutor js = (JavascriptExecutor) Session.getInstance().getBrowser();
        return (Boolean) js.executeScript("return document.querySelector('li:nth-child('+arguments[0]+') > div > div > a.product-card-information > div.hightlight').hasChildNodes();",i);
    }
}
