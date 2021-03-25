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
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class APIDemoTabsPage {

    private AppiumDriver<MobileElement> driver;
    private WebDriverWait driverWait;

    @AndroidFindBy(accessibility = "1. Content By Id")
    private MobileElement contentByIdButton;

    public boolean verifyClickedOnTabs() {
        driverWait.until(ExpectedConditions.visibilityOf(contentByIdButton));
        return contentByIdButton.isDisplayed();
    }

    public APIDemoTabsPage(AppiumDriver<MobileElement> driver, WebDriverWait driverWait){
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if(!contentByIdButton.isDisplayed()) {
            throw new SkipException("This is not Tabs Page");
        }
    }

}
