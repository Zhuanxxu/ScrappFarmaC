package controlSelenium;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import singletonSession.Session;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Control {
    protected By locator;
    protected WebElement control;
    protected String controlName; //reflection
    protected List<WebElement> controls = new ArrayList<>();
    protected WebDriverWait waitInstance = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));


    //********************              CONSTRUCTORS              ********************
    public Control(By locator) {
        this.locator = locator;
    }

    public Control(By locator, String controlName) {
        this.locator = locator;
        this.controlName = controlName;
    }

    //    @Step("{0}")
    public void step(String action) {
    }

    protected void findControl() {
        control = Session.getInstance().getBrowser().findElement(this.locator);
        controls = Session.getInstance().getBrowser().findElements(this.locator);
    }

    public void click() {
        this.findControl();
        this.step("click on CONTROL " + controlName);
        control.click();
    }


    //********************              GETS              ********************
    public String getText() {
        this.findControl();
        this.step("Get Text from " + controlName + ", the value is: " + control.getText());
        return control.getText();
    }

    public String getAttributeValue(String attribute) {
        this.findControl();
        this.step("Get attribute vale from " + controlName);
        return this.control.getAttribute(attribute);
    }

    public List<WebElement> getControls() {
        this.findControl();
        this.step("Get list of webelements from " + controlName);
        return controls;
    }

    public String getCssAttributeValue(String style) {
        this.findControl();
        this.step("Get css attribute " + style + "from " + controlName);
        return this.control.getCssValue(style);
    }

    public Integer getControlsQuantity() {
        try {
            this.findControl();
            this.step("Get controls quantity " + controlName);
            return this.controls.size();
        } catch (Exception e) {
            this.step("Get controls quantity " + controlName);
            return 0;
        }
    }

    public WebElement getControl() {
        this.findControl();
        return this.control;
    }

    public By getLocator() {
        return this.locator;
    }

    public WebDriverWait getWaitInstance() {
        return waitInstance;
    }

    //********************              BOOLEANS              ********************
    public boolean hasAttributeValue(String attribute, String value) {
        this.findControl();
        this.step("Control " + controlName + " has attribute value");
        return this.control.getAttribute(attribute).contains(value);
    }

    /**
     * Verifica si el control en cuestion isDisplayed
     *
     * @return boolean
     */
    public boolean isControlDisplayed() {
        try {
            this.findControl();
            this.step("Check the " + controlName + " is displayed: true");
            return control.isDisplayed();
        } catch (Exception e) {
            this.step("Check the " + controlName + " is displayed: false");
            return false;
        }
    }

    public boolean isControlEnabled() {
        try {
            this.findControl();
            this.step("Check the " + controlName + " is enabled: true");
            return control.isEnabled();
        } catch (Exception e) {
            this.step("Check the " + controlName + " is enabled: false");
            return false;
        }
    }


    //********************              ACTIONS              ********************
    public void makeRightClickAction() {
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.contextClick(this.control).perform();
        this.step("Action right click on control " + controlName);
    }

    public void hoverAction() {
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.moveToElement(this.control).perform();
        this.step("Action hover over control " + controlName);
    }

    public void sendKeysAction(Keys keys) {
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.sendKeys(keys)
                .perform();
        this.step("Action send keys to control " + controlName);
    }

    public void sendKeysAction(String text) {
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.sendKeys(text)
                .perform();
        this.step("Action send keys to control " + controlName);
    }

    public void doubleClickAction() {
        this.findControl();
        Actions action = new Actions(Session.getInstance().getBrowser());
        action.doubleClick(this.control)
                .perform();
        this.step("Action double click on control " + controlName);
    }

    public void dragAndDrop(WebElement webElementTarget) {
        this.findControl();
        Actions builder = new Actions(Session.getInstance().getBrowser());
        //Building a drag and drop action
        Action dragAndDrop = builder
                .moveToElement(this.control)
                .pause(Duration.ofSeconds(2))
                .clickAndHold(this.control)
                .moveByOffset(1, 1)
                .pause(Duration.ofSeconds(2))
                .moveToElement(webElementTarget)
                .pause(Duration.ofSeconds(2))
                .release(webElementTarget)
                .pause(Duration.ofSeconds(2))
                .build();


        //Performing the drag and drop action
        dragAndDrop.perform();
        this.step("Action drag and drop from control " + controlName);
    }

    //********************              WAITS              ********************

    /**
     * Espera a que el control tenga dentro de un ATRIBUTO un texto especifico
     */
    public void waitClickable() {
        // todo --> factory para instanciar el wait
        waitInstance.until(ExpectedConditions.elementToBeClickable(this.locator));
    }
    public void waitClickableAndClick() {
        // todo --> factory para instanciar el wait
        waitInstance.until(ExpectedConditions.elementToBeClickable(this.locator)).click();
    }

    public void waitAttributeDomValueToChange(String attribute, String value) {
        waitInstance.until(ExpectedConditions.not(ExpectedConditions.attributeContains(this.locator, attribute, value)));
    }

    /**
     * Espera a que el control tenga dentro de un ATRIBUTO un texto especifico
     *
     * @param attribute: es por ejemplo el CLASS en un elemento HTML
     * @param value:     valor dentro del ATRIBUTO HTML
     */
    public void waitUntilElementHasHtmlAttributeValue(String attribute, String value) {
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        waitInstance.until(ExpectedConditions.attributeContains(this.locator, attribute, value));
    }

    public void waitUrlToMatchRegexExpression(String regex) {
//        WebDriverWait wait = new WebDriverWait(Session.getInstance().getBrowser(), Duration.ofSeconds(10));
        waitInstance.until(ExpectedConditions.urlMatches("\\D*\\d{10}"));
    }

    /**
     * Espera a que el control tenga dentro de un ATRIBUTO un texto especifico
     */
    public void waitInvisibility() {
        waitInstance.until(ExpectedConditions.invisibilityOfElementLocated(this.locator));
    }

    public void waitIFrameToBeSwitchable() {
        waitInstance.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe")));
    }

    public void waitVisibility() {
        waitInstance.until(ExpectedConditions.visibilityOfElementLocated(this.locator));
    }
    public void waitVisibilityAndClick() {
        waitInstance.until(ExpectedConditions.visibilityOfElementLocated(this.locator)).click();
    }


    public void waitSelectable() {
        this.waitInstance.until(ExpectedConditions.elementToBeSelected(this.locator));
    }

    public void waitPresence() {
        this.waitInstance.until(ExpectedConditions.presenceOfElementLocated(this.locator));
    }
    /** Settea el attributo de la clase controls (que es un array de webelements)
     * y lo devuelve
     * @return array de WebElements
     */


}
