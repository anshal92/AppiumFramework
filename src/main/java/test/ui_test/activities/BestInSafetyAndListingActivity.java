package test.ui_test.activities;

import framework.ui_automation.AppUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class BestInSafetyAndListingActivity {
    AppiumDriver<MobileElement> driver;
    public BestInSafetyAndListingActivity(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }
    final private By bestInSafetyText = By.xpath("//android.widget.TextView[@index ='2' and @text='Best In Safety']");

    final private By bestInSafetyIcons = By.id("in.swiggy.android:id/iv_restaurant_tag");

    public BestInSafetyAndListingActivity TapBestInSafetyText(){
        driver.findElement(bestInSafetyText).click();
        return new BestInSafetyAndListingActivity(driver);
    }

    public MenuActivity tapBestInSafetyIcon(){
        List<MobileElement> bisIcon = driver.findElements(bestInSafetyIcons);
        System.out.println("The size of the best safety icon present ="+bisIcon.size());
        //Using first restaurant
        AppUtils appUtils = new AppUtils();
        appUtils.clickIfVisible(bisIcon.get(0), driver);
        return new MenuActivity(driver);
    }
}
