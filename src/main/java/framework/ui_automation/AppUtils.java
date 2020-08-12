package framework.ui_automation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.AppiumFluentWait;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AppUtils {
    //ToDo: Revise
    public void tap(By by, AppiumDriver<MobileElement> driver){
        MobileElement element = null;

        try{
            Wait wait = new AppiumFluentWait(driver).withTimeout(Duration.of(10, ChronoUnit.SECONDS)).pollingEvery(Duration.ofSeconds(1));
            element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            element.click();
        }catch (Exception e){
            System.out.println("Error during Appium fluent wait -> "+e.fillInStackTrace());
        }
    }

    public boolean isVisible(By by, AppiumDriver<MobileElement> driver){
        try{
            return driver.findElement(by).isDisplayed();
        }catch (Exception e){
            System.out.println("Error during visibility ->"+ e.fillInStackTrace());
            return false;
        }
    }

    public boolean isVisible(MobileElement me, AppiumDriver<MobileElement> driver){
        try{
            return me.isDisplayed();
        }catch (Exception e){
            System.out.println("Error during visibility ->"+ e.fillInStackTrace());
            return false;
        }
    }

    public void clickIfVisible(By by, AppiumDriver<MobileElement> driver){
        MobileElement me = driver.findElement(by);
        Wait wait = new AppiumFluentWait(driver).withTimeout(Duration.of(10, ChronoUnit.SECONDS)).pollingEvery(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(me));
        me.click();
    }

    public void clickIfVisible(MobileElement me, AppiumDriver<MobileElement> driver){
        Wait wait = new AppiumFluentWait(driver).withTimeout(Duration.of(10, ChronoUnit.SECONDS)).pollingEvery(Duration.ofSeconds(2));
        wait.until(ExpectedConditions.visibilityOf(me));
        me.click();
    }

    public void scrollToElement(MobileElement mobileElement, AppiumDriver<MobileElement> driver){
        int x = mobileElement.getLocation().getX();
        int y = mobileElement.getLocation().getY();
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(x,y)).moveTo(PointOption.point(x+90,y)).release().perform();
    }

    public MobileElement scrollToElement(By by, AppiumDriver<MobileElement> driver){
        MobileElement me = null;
        int retryTimes = 5;
        Dimension dimension = driver.manage().window().getSize();
        int anchor = dimension.width/2;
        int startP = (dimension.height/2) - 10;
        int endP = 10;

        for(int i = 0; i< retryTimes;i++){
            try{
                new TouchAction(driver)
                        .longPress(PointOption.point(anchor, startP))
                        .moveTo(PointOption.point(anchor, endP))
                        .release()
                        .perform();
                me = driver.findElement(by);
            }catch (NoSuchElementException e){
                System.out.println("Element not found After scrolling "+i+" times");
            }
        }
        return me;
    }
}
