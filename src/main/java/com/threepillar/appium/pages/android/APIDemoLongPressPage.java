package com.threepillar.appium.pages.android;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class APIDemoLongPressPage {

    private AppiumDriver<MobileElement> driver;
    private WebDriverWait driverWait;
    private AndroidTouchAction action;

    @AndroidFindBy(accessibility = "1. Custom Adapter")
    private MobileElement customAdapterButton;

    @AndroidFindBy(xpath = "//android.widget.ExpandableListView/android.widget.TextView[1]")
    private MobileElement peopleNameButton;

    @AndroidFindBys({@AndroidBy(xpath = "//android.widget.RelativeLayout/android.widget.TextView")})
    private List<MobileElement> sampleActionButton;

    public APIDemoLongPressPage performLongPress() {
        action.longPress(PointOption.point(peopleNameButton.getLocation().getX(), peopleNameButton.getLocation().getY()))
                .perform()
                .release();

        return this;
    }

    public APIDemoLongPressPage clickCustomAdapterOption() {
        driverWait.until(ExpectedConditions.visibilityOf(customAdapterButton));
        customAdapterButton.click();
        return this;
    }

    public APIDemoLongPressPage verifyLongPressSuccessful() {
        driverWait.until(ExpectedConditions.visibilityOf(sampleActionButton.get(0)));
        sampleActionButton.get(0).click();
        return this;
    }

    public boolean verifySampleActionNotDisplayed() {
        return sampleActionButton.isEmpty();
    }

    public APIDemoLongPressPage(AppiumDriver<MobileElement> driver, WebDriverWait driverWait){
        this.driver = driver;
        this.driverWait = driverWait;
        action = new AndroidTouchAction(driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

}
