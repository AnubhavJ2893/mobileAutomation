package com.threepillar.appium.pages.android;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.asserts.SoftAssert;

import java.util.List;


public class APIDemoHomePage {

    private AppiumDriver<MobileElement> driver;
    private WebDriverWait driverWait;

    @AndroidFindBy(accessibility = "Views")
    private MobileElement viewsButton;

    @AndroidFindBy(accessibility = "Tabs")
    private MobileElement tabsButton;

    @AndroidFindBy(accessibility = "Drag and Drop")
    private MobileElement dragDropButton;

    @AndroidFindBy(accessibility = "Expandable Lists")
    private MobileElement expandableListButton;

    public MobileElement scrollToAnElementByText(String text) {
        return driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector())" +
                ".scrollIntoView(new UiSelector().text(\"" + text + "\"));"));
    }

    public boolean verifyViewOptionPresent() {
        driverWait.until(ExpectedConditions.visibilityOf(viewsButton));
        return viewsButton.isDisplayed();
    }

    public APIDemoTabsPage openTabsPage() {
        MobileElement element = scrollToAnElementByText("Tabs");
        element.click();
        return new APIDemoTabsPage(driver, driverWait);
    }

    public APIDemoHomePage clickView() {
        viewsButton.click();
        return this;
    }

    public APIDemoDragDropPage clickDragDropOption() {
        dragDropButton.click();
        return new APIDemoDragDropPage(driver, driverWait);
    }

    public APIDemoLongPressPage clickExpandableListOption() {
        expandableListButton.click();
        return new APIDemoLongPressPage(driver, driverWait);
    }

    public APIDemoHomePage(AppiumDriver<MobileElement> driver, WebDriverWait driverWait){
        this.driver = driver;
        this.driverWait = driverWait;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        if(!viewsButton.isDisplayed()) {
            throw new SkipException("This is not home page");
        }
    }

}
