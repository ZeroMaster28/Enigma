package Frames;

import EnigmaMachine.Enigma;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MyMenuBar {

    Enigma enigma;
    JFrame jFrame;

    JMenuItem exit=new JMenuItem(new AbstractAction("Exit") {
        public void actionPerformed(ActionEvent e) {
           System.exit(0);
        }
    });

    JMenuItem reset=new JMenuItem(new AbstractAction("Reset") {
        public void actionPerformed(ActionEvent e) { enigma.restartMachine(); jFrame.repaint(); }
    });

    JMenuItem help=new JMenuItem(new AbstractAction("Help") {
        public void actionPerformed(ActionEvent e) {

            //TO-DO some guide to enigma here

        }
    });


    public MyMenuBar(JFrame jframe,Enigma enigma)
    {
        this.enigma=enigma;
        this.jFrame=jframe;

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        menu.add(reset);
        menu.add(help);
        menu.add(exit);

        menubar.add(menu);
        jframe.setJMenuBar(menubar);


    }
}
