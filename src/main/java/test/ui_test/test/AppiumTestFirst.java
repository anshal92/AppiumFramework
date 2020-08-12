package test.ui_test.test;

import framework.GetAppiumData;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.ui_test.activities.HomePage;

import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class AppiumTestFirst {

    AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws Exception{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        GetAppiumData gad = new GetAppiumData();
        Properties props = gad.getProperties();

        desiredCapabilities.setCapability("deviceName",props.getProperty("app.deviceName"));
        desiredCapabilities.setCapability("udid",props.getProperty("app.udid"));
        desiredCapabilities.setCapability("platformName", props.getProperty("app.platformName"));
        desiredCapabilities.setCapability("platformVersion", props.getProperty("app.platformVersion"));
        desiredCapabilities.setCapability("appPackage", props.getProperty("app.appPackage"));
        desiredCapabilities.setCapability("appActivity", props.getProperty("app.appActivity"));
        desiredCapabilities.setCapability("noReset", props.getProperty("app.noReset"));

        driver = new AppiumDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void swiggyFlow(){
        HomePage hp = new HomePage(driver);
        Boolean toAssert = hp.clickMapIcon()
                .clickMyLocation()
                .tapRestaurantIcon()
                .TapBestInSafetyText()
                .tapBestInSafetyIcon()
                .tapFirstAddIcon()
                .tapOnViewCart()
                .tapOnProceedToPay()
                .tapOnAmazonPayLink()
                .isPayUsingAmazonPayVisibleAndTapable();

        Assert.assertTrue(toAssert);
    }
}
