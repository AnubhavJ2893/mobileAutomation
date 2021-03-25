package com.threepillar.appium.android;

import com.threepillar.appium.AndroidCoreTest;
import com.threepillar.appium.pages.android.APIDemoDragDropPage;
import com.threepillar.appium.pages.android.APIDemoHomePage;
import com.threepillar.appium.pages.android.APIDemoLongPressPage;
import com.threepillar.appium.pages.android.APIDemoTabsPage;
import io.appium.java_client.MobileElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class APIDemoTest extends AndroidCoreTest {

    @Test(priority = 1)
    public void testScroll() {
        softAssert.assertTrue(apiDemoHomePage.verifyViewOptionPresent(), "Verify 'Views' option is displayed'");
        apiDemoHomePage.clickView();
        APIDemoTabsPage apiDemoTabsPage = apiDemoHomePage.openTabsPage();
        softAssert.assertNotNull(apiDemoTabsPage, "Verify 'Content By Id' option is displayed'");

        softAssert.assertAll();

        closeApp();
    }

    @Test(priority = 2)
    public void testDragDrop() {

        if(isAppClosed)
            getDriver().launchApp();

        softAssert.assertTrue(apiDemoHomePage.verifyViewOptionPresent(), "Verify 'Views' option is displayed'");
        apiDemoHomePage.clickView();
        APIDemoDragDropPage apiDemoDragDropPage = apiDemoHomePage.clickDragDropOption();
        apiDemoDragDropPage.performDragdrop();
        softAssert.assertNotNull(apiDemoDragDropPage.returnDragText(), "Verify text is not null");
        softAssert.assertTrue(apiDemoDragDropPage.returnDragText().contains("app:id/drag_dot_1"), "Verify text contains 'app:id/drag_dot_1'");

        softAssert.assertAll();
        closeApp();
    }

    @Test(priority = 3)
    public void testLongPress() {

        if(isAppClosed)
            getDriver().launchApp();

        softAssert.assertTrue(apiDemoHomePage.verifyViewOptionPresent(), "Verify 'Views' option is displayed'");
        apiDemoHomePage.clickView();
        APIDemoLongPressPage apiDemoLongPressPage = apiDemoHomePage.clickExpandableListOption();
        apiDemoLongPressPage.clickCustomAdapterOption();
        apiDemoLongPressPage.performLongPress();
        apiDemoLongPressPage.verifyLongPressSuccessful();
        softAssert.assertTrue(apiDemoLongPressPage.verifySampleActionNotDisplayed(), "Verify the pop up option is not displayed now");

        softAssert.assertAll();
        closeApp();
    }
}
