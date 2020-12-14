package view.frames;

import enigmaMachine.Enigma;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * Class represents menu bar implementation
 */
public class MyMenuBar {

    /** The actual enigma implementation */
    Enigma enigma;

    /** Application's main frame reference */
    JFrame appMainFrame;

    /** Settings frame */
    JFrame settingsFrame;

    /** Help/info frame */
    JFrame helpFrame;

    /** Exit */
    JMenuItem exit=new JMenuItem(new AbstractAction("Exit") {
        public void actionPerformed(ActionEvent e) {
           enigma.endWork();
        }
    });

    /** Restart the actual enigma */
    JMenuItem reset = new JMenuItem(new AbstractAction("Reset") {
        public void actionPerformed(ActionEvent e) {
            enigma.restartMachine(); appMainFrame.repaint();
        }
    });

    /** Show help/info frame */
    JMenuItem help=new JMenuItem(new AbstractAction("Help") {
        public void actionPerformed(ActionEvent e) {
            helpFrame.setVisible(true);
        }
    });

    /** Show settings frame */
    JMenuItem settings = new JMenuItem(new AbstractAction("Set Rotors") {
        public void actionPerformed(ActionEvent e) {
            settingsFrame.setVisible(true);
        }
    });


    public MyMenuBar(JFrame appMainFrame, Enigma enigma) {
        // initializing the attributes
        this.enigma = enigma;
        this.appMainFrame = appMainFrame;
        this.settingsFrame = new SettingsFrame(this.enigma, this.appMainFrame);
        this.helpFrame = new HelpFrame();

        // creating the actual menu bar for the main frame
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        menu.add(reset);
        menu.add(settings);
        menu.add(help);
        menu.add(exit);
        reset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2,0));
        settings.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3,0));
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
        menuBar.add(menu);
        appMainFrame.setJMenuBar(menuBar);
    }
}
