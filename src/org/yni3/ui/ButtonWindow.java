package org.yni3.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import org.yni3.log.Log;

/**
 * デバックモードで繰り返し実行する際に、便利なリトライボタンwindowを作る。
 *
 * @author yni3
 */
public class ButtonWindow {

    private JFrame frame;
    private JButton b1;

    public ButtonWindow(String fPath) {
        //Frame 型変数を宣言
        frame = new JFrame("Window Test");//Frame型変数に初期値をセット
        frame.setSize(180, 90);//サイズを指定す
        b1 = new JButton("reload");
        ClickReload ac = new ClickReload(fPath);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * ウィンドウが閉じるときに呼ばれる
             */
            @Override
            public void windowClosing(WindowEvent e) {
                Log.debug("window close");
                Log.close();
            }
        });
        b1.addActionListener(ac);
        frame.add(b1);
        frame.setVisible(true);//可視化する
    }
}
