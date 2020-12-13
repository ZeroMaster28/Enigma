package Frames;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import EnigmaMachine.Enigma;

import ProjectPackage1.UtilityHelper;


public class MyFrame extends JFrame implements KeyListener {


    private boolean blocked=false;
    private Lamps lamps;
    private int lastKeyCode=0;
    private int keyAfterEncryption=0;
    private Enigma enigma=new Enigma();
    private MyMenuBar menuBar;

    public MyFrame() {
        //setting the main frame
        super("Enigma 1.0");
        setSize(new Dimension(1290, 720));
        setResizable(false);
        addKeyListener(this);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
               // enigma.endWork();
            }
        });
        lamps=new Lamps(enigma);
        add(lamps);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuBar=new MyMenuBar(this,enigma);

        setVisible(true);

    }

    public void keyPressed(KeyEvent evt) {
        if(blocked==false) workWithLight(evt);
    }


    public void keyReleased(KeyEvent evt) {

      if(blocked==true)workWithLight(evt);

    }

    public void keyTyped(KeyEvent evt) {

    }

    public void workWithLight(KeyEvent evt)
    {

        if(evt.getKeyCode()<KeyEvent.VK_A || evt.getKeyCode()>KeyEvent.VK_Z ) return;

        if(blocked&& evt.getKeyCode()!=lastKeyCode) return;
        if(blocked && evt.getKeyCode()==lastKeyCode)
        {
            blocked=false;
            lamps.changeState(UtilityHelper.transform(keyAfterEncryption));
            repaint();
            return;
        }
        if(!blocked)
        {
           blocked=true;
            lastKeyCode=evt.getKeyCode();
            keyAfterEncryption=enigma.encrypt(lastKeyCode-65);
            lamps.changeState(UtilityHelper.transform(keyAfterEncryption));
            repaint();
            return;
        }
    }
}


