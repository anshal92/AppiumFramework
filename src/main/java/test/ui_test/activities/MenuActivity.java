package test.ui_test.activities;

import framework.api_autoamtion.ApiUtils;
import framework.ui_automation.AppUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import java.util.List;

public class MenuActivity {
    AppiumDriver<MobileElement> driver;
    public MenuActivity(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }

    final private By addButtons = By.id("in.swiggy.android:id/add_to_cart_item_add_text");
    final private By customisationMenuAdd = By.xpath("//android.widget.TextView[@text ='ADD ITEM']");
    final private By viewCartBtn = By.xpath("//android.widget.TextView[@text = 'VIEW CART']");

    public MenuActivity tapFirstAddIcon(){
        AppUtils appUtils = new AppUtils();
        MobileElement me = appUtils.scrollToElement(addButtons, driver);

        me.click();
        if(appUtils.isVisible(customisationMenuAdd, driver)){
            driver.findElement(customisationMenuAdd).click();
        }
        return new MenuActivity(driver);
    }

    public CartActivity tapOnViewCart(){
        driver.findElement(viewCartBtn).click();
        return new CartActivity(driver);
    }
}
