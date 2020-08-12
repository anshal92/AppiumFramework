package test.ui_test.activities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

public class PaymentActivity {
    AppiumDriver<MobileElement> driver;
    public PaymentActivity(AppiumDriver<MobileElement> driver){
        this.driver = driver;
    }
    private final By amazonPaylink = By.xpath("//android.view.ViewGroup[@content-desc = 'PhonePe']");
    private final By payUsingAmazonBtn = By.xpath("//android.view.ViewGroup[@content-desc = 'Pay using PhonePe']");

    public PaymentActivity tapOnAmazonPayLink(){
        try{
            Thread.sleep(1300);
        } catch (Exception e){
            System.out.println(e);
        }
        driver.findElement(amazonPaylink).click();
        return new PaymentActivity(driver);
    }

    public boolean isPayUsingAmazonPayVisibleAndTapable(){
        MobileElement me = driver.findElement(payUsingAmazonBtn);
        return me.isDisplayed() && me.isEnabled();
    }
}
