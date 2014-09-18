package org.yni3;

import java.util.LinkedList;
import org.yni3.data.FString;
import org.yni3.log.Log;
import org.yni3.selenium.SeleniumHolder;
import org.yni3.ui.ButtonWindow;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 *
 * @author yni3
 */
public class SeleniumJS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final Auguments arg = ParseAuguments(args);
        Signal.handle(new Signal("INT"), new SignalHandler() {
            @Override
            public void handle(Signal sig) {
                SeleniumHolder.close();
                Log.warn("command interrupted.");
                System.exit(1);
            }
        });
        if (arg.getOption(CommandType.NO_OPTIONS) == null) {
            Log.error("augment need");
        }
        {//スクリプト実行
            FString script = null;
            try {
                script = FString.create(arg.getOption(CommandType.NO_OPTIONS).getString());
            } catch (Exception ex) {
                Log.error(ex);
            }
            SeleniumHolder.init(SeleniumHolder.BROWSER_TYPE.FIREFOX);
            if (script != null) {
                SeleniumHolder.getIncetance().executeScript(script);
            }
        }
        if (arg.isUseGUI) {//GUIモードでは、繰り返し実行可能
            ButtonWindow bw = new ButtonWindow(arg.getOption(SeleniumJS.CommandType.NO_OPTIONS).getString());
        } else {
            SeleniumHolder.close();
        }
    }

    //=引数の処理=========================================
    static enum CommandType {

        USE_GUI,
        SILENT,
        NO_OPTIONS,
    }

    static class Option {

        public CommandType type;
        public LinkedList<String> strings = new LinkedList<>();

        public String getString() {
            if (strings.size() > 0) {
                return strings.get(0);
            } else {
                return "";
            }
        }
    }

    static class Auguments {

        public boolean isUseGUI = false;
        public boolean isSilent = false;
        public LinkedList<Option> rs = new LinkedList<>();

        public Option getOption(CommandType type) {
            for (Option op : rs) {
                if (op.type == type) {
                    return op;
                }
            }
            return null;
        }

        public Option createOption(CommandType ct) {
            Option in = getOption(ct);
            if (in != null) {
                return in;
            }
            Option op = new Option();
            rs.add(op);
            op.type = ct;
            return op;
        }
    }

    private static Auguments ParseAuguments(String[] s) {
        Auguments arg = new Auguments();
        for (String op : s) {
            switch (op) {
                case "-d":
                    arg.createOption(CommandType.USE_GUI);
                    arg.isUseGUI = true;
                    break;
                case "-s":
                    arg.createOption(CommandType.SILENT);
                    arg.isSilent = true;
                    break;
                default:
                    Option ic = arg.createOption(CommandType.NO_OPTIONS);
                    ic.strings.add(op);
                    break;
            }
        }
        return arg;
    }
}
