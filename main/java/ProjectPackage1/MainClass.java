package ProjectPackage1;

import Frames.MyFrame;

import java.awt.EventQueue;

class MainClass {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFrame();

            }
        });
    }
}
