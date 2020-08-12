package test.ui_test.activities;

import framework.ui_automation.AppUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class AddressPage {
    AppiumDriver<MobileElement> driver;
    public AddressPage(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }

    final private By myLocation = By.xpath("//android.widget.TextView[@text = 'My Pad']");

    public HomePage clickMyLocation(){
        AppUtils appUtils = new AppUtils();
        try{
            Thread.sleep(2000);
        }catch (Exception e){
            System.out.println("Exception is -> " +e.fillInStackTrace());
        }

        appUtils.clickIfVisible(myLocation, driver);
        return new HomePage(driver);
    }
}
