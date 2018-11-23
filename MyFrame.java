package Frames;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;

public class MyFrame extends JFrame implements KeyListener {

    JSplitPane splitPane = new JSplitPane();
    private boolean blocked=false;
    private Lamps lamps;
    private Background back;
    private int lastKeyCode=0;

    public MyFrame() {
        //setting the main frame
        super("Enigma 1.0");
        setSize(new Dimension(1290, 720));
        setResizable(false);
        addKeyListener(this);
       // setBackground(Color.BLACK);


        //back= new Background();
        lamps=new Lamps();

        add(lamps);



        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




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
        if(blocked&& evt.getKeyCode()!=lastKeyCode) return;
        if(blocked && evt.getKeyCode()==lastKeyCode)
        {
            blocked=false;
            lamps.changeState(UtilityHelper.transform(lastKeyCode-65));
            repaint();
            return;
        }
        if(!blocked)
        {
           blocked=true;
            lastKeyCode=evt.getKeyCode();
            lamps.changeState(UtilityHelper.transform(lastKeyCode-65));
            repaint();
            return;
        }
    }

}


