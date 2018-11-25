package Frames;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

import EnigmaMachine.Enigma;

public class MyFrame extends JFrame implements KeyListener {


    private boolean blocked=false;
    private Lamps lamps;
    private int lastKeyCode=0;
    private int keyAfterEncryption=0;
    private Enigma enigma=new Enigma();

    public MyFrame() {
        //setting the main frame
        super("Enigma 1.0");
        setSize(new Dimension(1290, 720));
        setResizable(false);
        addKeyListener(this);

        lamps=new Lamps(enigma);
        add(lamps);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new MyMenuBar(this,enigma);

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
        if(evt.getKeyCode()==KeyEvent.VK_F2)
        {
            enigma.restartMachine();
            repaint();
            return;
        }
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


