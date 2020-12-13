package view.frames;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import enigmaMachine.Enigma;

import view.elements.Lamps;
import util.UtilityHelper;


public class MainFrame extends JFrame implements KeyListener {

    private final Lamps lamps;
    private final Enigma enigma;
    private final MyMenuBar menuBar;

    private boolean blocked = false;
    private int lastKeyCode = 0;
    private int keyAfterEncryption = 0;


    public MainFrame() {
        //setting the main frame crucial attributes
        super("Enigma 1.0");
        enigma = new Enigma();
        lamps = new Lamps(enigma);
        menuBar = new MyMenuBar(this,enigma);
        add(lamps);

        //setting the main frame visualisation
        setSize(new Dimension(1290, 720));
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

    }

    public void workWithLight(KeyEvent evt)
    {
        if(evt.getKeyCode()<KeyEvent.VK_A || evt.getKeyCode()>KeyEvent.VK_Z ) {
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
            keyAfterEncryption=enigma.encrypt(lastKeyCode-65);
            lamps.changeState(UtilityHelper.transform(keyAfterEncryption));
            repaint();
        }
    }
}
