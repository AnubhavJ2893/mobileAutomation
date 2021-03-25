package com.threepillar.appium;

import com.threepillar.appium.pages.android.APIDemoHomePage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidCoreTest {

    private AppiumDriver<MobileElement> driver;
    public static final SoftAssert softAssert = new SoftAssert();
    public WebDriverWait driverWait;
    private AppiumDriverLocalService service;
    public APIDemoHomePage apiDemoHomePage = null;
    public boolean isAppClosed = false;

    public AppiumDriver<MobileElement> getDriver() {

        if(driver == null) {
            driver = getAppiumDriverForAndroid();

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driverWait = new WebDriverWait(driver, 30);
            return driver;
        }
        else {
            return driver;
        }
    }

    private AppiumDriver<MobileElement> getAppiumDriverForAndroid() {

        URL url = null;
        try {
            url = new URL("http://0.0.0.0:4723/wd/hub");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return new AppiumDriver<MobileElement>(url, androidAppCapabilities());
    }

    private DesiredCapabilities androidAppCapabilities() {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability("avd","Pixel_2_AVD");
        capabilities.setCapability("appPackage", "io.appium.android.apis");
        capabilities.setCapability("appActivity", "io.appium.android.apis.ApiDemos");
        capabilities.setCapability("app", "/Users/anubhav.joshi/Downloads/ApiDemos-debug.apk");
        capabilities.setCapability("autoAcceptAlerts", true);
        capabilities.setCapability("autoDismissAlerts", true);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 0);

        return capabilities;
    }

    @BeforeSuite(alwaysRun = true)
    public void startServer() {
        //Set Capabilities
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("noReset", "false");

        //Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("0.0.0.0");
        builder.usingPort(4723);
        builder.withCapabilities(cap);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL,"error");

        //Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer() {
        service.stop();
    }

    @BeforeTest
    public void setUp() {
        apiDemoHomePage = new APIDemoHomePage(getDriver(), driverWait);
    }

    @AfterTest
    private void tearDown() {
        driver.quit();
        try {
            Process p = Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeApp() {
        isAppClosed = true;
        getDriver().closeApp();
    }
}
