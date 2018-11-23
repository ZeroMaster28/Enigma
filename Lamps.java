package Frames;

import javax.swing.*;
import java.awt.*;

public class Lamps extends JPanel {

    private static final int TRANSLATION_OF_X=40;
    private static final int TRANSLATION_OF_Y=150;

    private static final String[] FILES={"a.png","a1.png"};

    private int size= 26;
    private Lamp[] lamps;
    private Background background=new Background();

    public Lamps()
    {

        Lamp[] lamps=new Lamp[26];

        for(int i=0;i<10;i++)
        {
            lamps[i]=new Lamp("a.png","a1.png",'x',TRANSLATION_OF_X+10+120*i,TRANSLATION_OF_Y+20);

        }
        for(int i=10;i<19;i++)
        {
            lamps[i]=new Lamp("a.png","a1.png",'x',TRANSLATION_OF_X+60+120*(i-10),TRANSLATION_OF_Y+150);

        }
        for(int i=19;i<26;i++)
        {
            lamps[i]=new Lamp("a.png","a1.png",'x',TRANSLATION_OF_X+180+120*(i-19),TRANSLATION_OF_Y+280);

        }

        this.lamps=lamps;
    }


    void changeState(int n)
    {
        if(n<size && n >=0)
        {
            lamps[n].changeState();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        background.paintComponent(g2d);

        for(int i=0;i<size;i++) {
            if (lamps[i].isVisible() == true) {
                g2d.drawImage(lamps[i].imageOn, lamps[i].getX(), lamps[i].getY(), this);
                
            }
            if (lamps[i].isVisible() == false) {
                g2d.drawImage(lamps[i].imageOff, lamps[i].getX(), lamps[i].getY(), this);
            }
        }
    }
}
