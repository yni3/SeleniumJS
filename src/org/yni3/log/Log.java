package org.yni3.log;

import org.yni3.selenium.SeleniumHolder;

/**
 *
 * @author yni3
 */
public class Log {

    private static void appExit(int status_code) {
        SeleniumHolder.close();
        System.exit(status_code);
    }

    public static void close() {
        appExit(0);
    }

    public static void debug(String s) {
        System.out.println(s);
    }

    public static void warn(String s) {
        System.out.println(s);
    }

    public static void warn(Throwable ex) {
        ex.printStackTrace();
    }

    public static void error(String s) {
        System.out.println(s);
        appExit(1);
    }

    public static void error(Throwable ex) {
        ex.printStackTrace();
        appExit(1);
    }
}
