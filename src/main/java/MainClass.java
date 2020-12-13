import view.frames.MainFrame;

import java.awt.EventQueue;

public class MainClass {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
