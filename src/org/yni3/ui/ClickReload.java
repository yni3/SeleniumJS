package org.yni3.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.yni3.data.FString;
import org.yni3.log.Log;
import org.yni3.selenium.SeleniumHolder;

/**
 *
 * @author yni3
 */
public class ClickReload implements ActionListener {

    String path;

    public ClickReload(String p) {
        path = p;
    }
    
    private void exec(){
        FString script = null;
        try {
            script = FString.create(path);
        } catch (Exception ex) {
            Log.warn(ex);
        }
        SeleniumHolder.init(SeleniumHolder.BROWSER_TYPE.FIREFOX);
        if (script != null) {
            SeleniumHolder.getIncetance().executeScript(script);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        exec();
    }
}
