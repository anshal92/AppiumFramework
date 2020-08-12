package test.ui_test.test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.ui_test.activities.HomePage;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumTestFirst {

    AppiumDriver<MobileElement> driver;

    @BeforeClass
    public void setUp() throws Exception{
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("deviceName","Death");
        desiredCapabilities.setCapability("udid","8BFX19V70");
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("platformVersion", "10.0");
        desiredCapabilities.setCapability("appPackage", "in.swiggy.android");
        desiredCapabilities.setCapability("appActivity", "in.swiggy.android.activities.HomeActivity");
        desiredCapabilities.setCapability("noReset", "true");

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
