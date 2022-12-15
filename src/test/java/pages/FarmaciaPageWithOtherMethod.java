package pages;

import org.openqa.selenium.JavascriptExecutor;
import singletonSession.Session;

import java.util.List;

public class FarmaciaPageWithOtherMethod extends SuperPage implements IPageInteface{
    int oddNumberInArray;
    @Override
    public boolean verifyIfThereIsAnyPromo(int i){
        oddNumberInArray = 2*(i-1)+1;
        JavascriptExecutor js = (JavascriptExecutor) Session.getInstance().getBrowser();
        return (Boolean) js.executeScript("return document.querySelector('li:nth-of-type('+arguments[0]+') > div > div > a.product-card-information > div.hightlight').hasChildNodes();",oddNumberInArray);
    }
}
