package com.threepillar.appium.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class APIDemoDragDropPage {

    private AppiumDriver<MobileElement> driver;
    private WebDriverWait driverWait;
    private AndroidTouchAction action;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_1")
    private MobileElement dot1;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_2")
    private MobileElement dot2;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_3")
    private MobileElement dot3;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_dot_hidden")
    private MobileElement hiddenDot;

    @AndroidFindBy(id = "io.appium.android.apis:id/drag_text")
    private MobileElement dragText;


    public APIDemoDragDropPage performDragdrop() {
        action.longPress(PointOption.point(dot1.getLocation().getX(), dot1.getLocation().getY()))
                .perform()
                .waitAction()
                .moveTo(PointOption.point(hiddenDot.getCenter().getX(), hiddenDot.getCenter().getY()))
                .perform()
                .waitAction()
                .release();

        dragText.click();
        return this;
    }

    public String returnDragText() {
        driverWait.until(ExpectedConditions.visibilityOf(dragText));
        return dragText.getText();
    }

    public APIDemoDragDropPage(AppiumDriver<MobileElement> driver, WebDriverWait driverWait){
        this.driver = driver;
        this.driverWait = driverWait;
        action = new AndroidTouchAction(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
