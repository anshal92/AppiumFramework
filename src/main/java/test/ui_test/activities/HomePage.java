package test.ui_test.activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class HomePage{
    AppiumDriver<MobileElement> driver;
    public HomePage(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }

    final private By mapIconInHome = By.id("in.swiggy.android:id/location_pin_icon_imageview");

    final private By homePageRestaurantImages = By.xpath("//androidx.recyclerview.widget.RecyclerView[@index='0']/android.view.ViewGroup[@index='0']/android.widget.ImageView[@index='0']");

    public AddressPage clickMapIcon(){
        driver.findElement(mapIconInHome).click();
        return new AddressPage(driver);
    }

    public BestInSafetyAndListingActivity tapRestaurantIcon(){
        List<MobileElement> icons = driver.findElements(homePageRestaurantImages);
        //Restaurant Icon is an image and does not have identifier hence using first element directly
        icons.get(0).click();
        return new BestInSafetyAndListingActivity(driver);
    }
}
