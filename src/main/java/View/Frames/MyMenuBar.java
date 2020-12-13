package View.Frames;

import EnigmaMachine.Enigma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MyMenuBar {

    Enigma enigma;
    JFrame jFrame;
    JFrame settingsFrame;
    JFrame helpFrame;

    JMenuItem exit=new JMenuItem(new AbstractAction("Exit") {
        public void actionPerformed(ActionEvent e) {
           enigma.endWork();
        }
    });

    JMenuItem reset=new JMenuItem(new AbstractAction("Reset") {
        public void actionPerformed(ActionEvent e) { enigma.restartMachine(); jFrame.repaint(); }
    });

    JMenuItem help=new JMenuItem(new AbstractAction("Help") {
        public void actionPerformed(ActionEvent e) {

            helpFrame.setVisible(true);

        }
    });

    JMenuItem settings=new JMenuItem(new AbstractAction("Set Rotors") {
        public void actionPerformed(ActionEvent e) {

            settingsFrame.setVisible(true);
        }
    });


    public MyMenuBar(JFrame jframe,Enigma enigma)
    {
        this.enigma=enigma;
        this.jFrame=jframe;
        this.settingsFrame=new SettingsFrame(this.enigma, this.jFrame);
        this.helpFrame=new HelpFrame();

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        menu.add(reset);
        menu.add(settings);
        menu.add(help);
        menu.add(exit);

        reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));

        menubar.add(menu);
        jframe.setJMenuBar(menubar);
    }
}
