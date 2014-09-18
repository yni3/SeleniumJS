package org.yni3.selenium;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.WrappedException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.yni3.data.FString;
import org.yni3.log.Log;

/**
 *
 * @author yni3
 */
public class SeleniumHolder {
    
    private static SeleniumHolder self = null;
    
    public enum BROWSER_TYPE {
        
        FIREFOX,
        CHROME,
    };
    
    public static SeleniumHolder init(BROWSER_TYPE type) {
        if (self == null) {
            self = new SeleniumHolder(type);
        }
        return self;
    }
    
    public static SeleniumHolder getIncetance() {
        if (self == null) {
            self = new SeleniumHolder(BROWSER_TYPE.FIREFOX);
        }
        return self;
    }
    
    public static void close() {
        if (self != null) {
            self.driver.quit();
            self = null;
        }
    }
    private WebDriver driver = null;
    
    private SeleniumHolder(BROWSER_TYPE type) {
        switch (type) {
            case FIREFOX: {
                //ProfilesIni allProfiles = new ProfilesIni();
                //FirefoxProfile profile = allProfiles.getProfile(""); //カスタムプロファイルの指定　これを行わなかったときは、webDriver用の空のプロファイルが用いられる。
                driver = new FirefoxDriver();
                break;
            }
            case CHROME:
            default:
                driver = new ChromeDriver();
                break;
        }
    }
    
    public void executeScript(FString fs) {
        final Context cx = Context.enter();
        try {
            final Scriptable scope = cx.initStandardObjects();
            //binding
            {
                Object wrappedOut = Context.javaToJS(System.out, scope);
                ScriptableObject.putProperty(scope, "", wrappedOut);
            }
            {
                Object wrappedOut = Context.javaToJS(driver, scope);
                ScriptableObject.putProperty(scope, "driver", wrappedOut);
            }
            {
                final By b = new ByObject();
                Object wrappedOut = Context.javaToJS(b, scope);
                ScriptableObject.putProperty(scope, "By", wrappedOut);
            }
            {
                final SeleniumHelper sl = new SeleniumHelper(driver);
                Object wrappedOut = Context.javaToJS(sl, scope);
                ScriptableObject.putProperty(scope, "helper", wrappedOut);
            }
            cx.evaluateString(scope, fs.toString(), fs.getFilename(), 1, null);
            
        } catch (WrappedException ex) {
            Throwable ta = ex.getWrappedException();
            Log.warn(ex.getClass().getCanonicalName());
            Log.warn(ex.getMessage());
            Log.warn(ex);
            if (ta instanceof UnreachableBrowserException) {
                //ブラウザとの接続が切れている
                Log.error(ta);
            }
        } finally {
            // Exit from the context.
            Context.exit();
        }
    }
}
