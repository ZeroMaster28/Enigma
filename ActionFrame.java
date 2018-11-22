package Frames;



import javax.swing.*;

public class ActionFrame extends JFrame {

    public ActionFrame() {
        super("Enigma 1.0");

        JPanel panel = new MyPanel();
        add(panel);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setSize(1280,720);
        setResizable(false);

        setVisible(true);
    }
}