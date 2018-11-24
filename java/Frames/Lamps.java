package Frames;

import javax.swing.*;
import java.awt.*;

public class Lamps extends JPanel {

    private static final int TRANSLATION_OF_X=40;
    private static final int TRANSLATION_OF_Y=150;

    private static final String LETTERS="QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final int SIZE= 26;

    private Lamp[] lamps;
    private Background background=new Background();
    private RotorGraphics[] rotorGraphics=RotorGraphics.getInstance();

    private Font defaultFont=new Font("Arial", Font.BOLD, 80);
    private Color FontColor= new Color(30,30,30);

    public Lamps()
    {


        Lamp[] lamps=new Lamp[26];

        for(int i=0;i<10;i++)
        {
            lamps[i]=new Lamp("a1.png","a2.png",'x',TRANSLATION_OF_X+10+120*i,TRANSLATION_OF_Y+20);

        }
        for(int i=10;i<19;i++)
        {
            lamps[i]=new Lamp("a1.png","a2.png",'x',TRANSLATION_OF_X+60+120*(i-10),TRANSLATION_OF_Y+150);

        }
        for(int i=19;i<26;i++)
        {
            lamps[i]=new Lamp("a1.png","a2.png",'x',TRANSLATION_OF_X+180+120*(i-19),TRANSLATION_OF_Y+280);

        }

        this.lamps=lamps;
    }


    void changeState(int n)
    {
        if(n<SIZE && n >=0)
        {
            lamps[n].changeState();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        background.paintComponent(g2d);
        g2d.setFont(defaultFont);
        g2d.setColor(FontColor);
        int pom=0;//translation for letter like W,I,O,M

        for(int i=0;i<SIZE;i++) {
            if (lamps[i].isVisible() == true) {
                g2d.drawImage(lamps[i].imageOn, lamps[i].getX(), lamps[i].getY(), this);
                pom=UtilityHelper.transformLetterInString(i);
                g2d.drawString(""+LETTERS.charAt(i),pom+lamps[i].getX()+25,lamps[i].getY()+75);
            }
            if (lamps[i].isVisible() == false) {
                g2d.drawImage(lamps[i].imageOff, lamps[i].getX(), lamps[i].getY(), this);
                pom=UtilityHelper.transformLetterInString(i);
                g2d.drawString(""+LETTERS.charAt(i),pom+lamps[i].getX()+25,lamps[i].getY()+75);
            }
        }
        for(int i=0;i<3;i++)
        rotorGraphics[i].paintComponent(g2d);
    }
}
