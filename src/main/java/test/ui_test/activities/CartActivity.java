package test.ui_test.activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class CartActivity {
    AppiumDriver<MobileElement> driver;
    public CartActivity(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }
    private final By proceedToPay = By.xpath("//android.widget.TextView[@text = 'PROCEED TO PAY']");

    public PaymentActivity tapOnProceedToPay(){
        driver.findElement(proceedToPay).click();
        return new PaymentActivity(driver);
    }
}
