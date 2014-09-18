package org.yni3.selenium;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author yni3
 */
public class SeleniumHelper {

    WebDriver driver;
    final static int default_timeout = 1;
    final static int long_timeout = 10;
    int currentTimeOut = default_timeout;
        
    public SeleniumHelper(WebDriver wd) {
        driver = wd;
        WebDriver.Timeouts t = wd.manage().timeouts();
        driver.manage().timeouts().implicitlyWait(default_timeout, TimeUnit.SECONDS);
    }

    public void setFindElementTimeoutSec(int sec) {
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
        currentTimeOut = sec;
    }
    
    public void waitLoad(){
        driver.manage().timeouts().implicitlyWait(long_timeout, TimeUnit.SECONDS);
        driver.findElement(By.tagName("html"));
        driver.manage().timeouts().implicitlyWait(currentTimeOut, TimeUnit.SECONDS);
    }

}
