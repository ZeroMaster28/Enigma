package view.frames;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import enigmaMachine.Enigma;

import view.components.Lamps;
import util.UtilityHelper;

/**
 * Application main frame
 */
public class MainFrame extends JFrame implements KeyListener {

    /** Frame resolution */
    private static final int FRAME_SIZE_X = 1290, FRAME_SIZE_Y = 720;

    /** Lamps layout */
    private final Lamps lamps;

    /** The actual enigma implementation */
    private final Enigma enigma;

    /** Menu bar */
    private final MyMenuBar menuBar;

    /** Is frame blocked for receiving new inputs */
    private boolean blocked = false;

    /** States of the encrypted characters */
    private int lastKeyCode = 0;
    private int keyAfterEncryption = 0;


    public MainFrame() {
        //setting the main frame crucial attributes
        super("Enigma 1.0");
        enigma = new Enigma();
        lamps = new Lamps(enigma);
        menuBar = new MyMenuBar(this, enigma);
        add(lamps);

        //setting the main frame visualisation
        setSize(new Dimension(FRAME_SIZE_X, FRAME_SIZE_Y));
        setResizable(false);
        addKeyListener(this);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               // enigma.endWork();
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void keyPressed(KeyEvent evt) {
        if(!blocked) {
            workWithLight(evt);
        }
    }


    public void keyReleased(KeyEvent evt) {
        if(blocked) {
          workWithLight(evt);
        }
    }

    public void keyTyped(KeyEvent evt) {
        //
    }

    public void workWithLight(KeyEvent evt) {
        if(evt.getKeyCode() < KeyEvent.VK_A || evt.getKeyCode() > KeyEvent.VK_Z) {
            return;
        }
        if(blocked && evt.getKeyCode() != lastKeyCode) {
            return;
        }
        if(blocked && evt.getKeyCode() == lastKeyCode) {
            blocked = false;
            lamps.changeState(UtilityHelper.transform(keyAfterEncryption));
            repaint();
            return;
        }
        if(!blocked) {
            blocked=true;
            lastKeyCode=evt.getKeyCode();
            keyAfterEncryption=enigma.encrypt(lastKeyCode - 65);
            lamps.changeState(UtilityHelper.transform(keyAfterEncryption));
            repaint();
        }
    }
}
