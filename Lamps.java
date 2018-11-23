package Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Lamps extends JPanel {

    private static final char[] SET_OF_CHARS = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G'};
    private static final String[] FILES={"x1.jpg","x2.jpg"};

    private int size= 26;
    private Lamp[] lamps;

    public Lamps()
    {
        add(new Background());
        setOpaque(false);
        Lamp[] lamps=new Lamp[26];

        for(int i=0;i<10;i++)
        {
            lamps[i]=new Lamp("x1.jpg","x.jpg",'x',10+120*i,20);

        }
        for(int i=10;i<19;i++)
        {
            lamps[i]=new Lamp("x1.jpg","x.jpg",'x',60+120*(i-10),150);

        }
        for(int i=19;i<26;i++)
        {
            lamps[i]=new Lamp("x1.jpg","x.jpg",'x',180+120*(i-19),280);

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
