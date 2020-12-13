package view.elements;

import javax.swing.*;
import java.awt.*;
import enigmaMachine.Enigma;
import util.UtilityHelper;

/**
 * Class represents layout for the lamp components
 */
public class Lamps extends JPanel {

    /** Initial layout translations */
    private static final int TRANSLATION_OF_X = 40;
    private static final int TRANSLATION_OF_Y = 150;

    /** Lamps letters font */
    private static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 80);

    /** Lamps letters color */
    private static final Color FONT_COLOR= new Color(30,30,30);

    /** Layout letters themself */
    private static final String LETTERS="QWERTYUIOPASDFGHJKLZXCVBNM";
    private static final int SIZE= 26;

    /** Enigma implementation that has impact on this layout's lamps changing states */
    private Enigma currentEnigma;

    /** Lamps of the layout */
    private Lamp[] lamps;

    /** Layout background image component */
    private Background background=new Background();


    public Lamps(Enigma currentEnigma) {
        this.currentEnigma=currentEnigma;
        this.lamps = new Lamp[26];
        for(int i=0; i<10; i++) {
            lamps[i]=new Lamp("light_on.png","light_off.png",'x',
                    TRANSLATION_OF_X+10+120*i,TRANSLATION_OF_Y+20);

        }
        for(int i=10;i<19;i++) {
            lamps[i]=new Lamp("light_on.png","light_off.png",'x',
                    TRANSLATION_OF_X+60+120*(i-10),TRANSLATION_OF_Y+150);
        }
        for(int i=19;i<26;i++) {
            lamps[i]=new Lamp("light_on.png","light_off.png",'x',
                    TRANSLATION_OF_X+180+120*(i-19),TRANSLATION_OF_Y+280);
        }
    }

    /**
     * Changes state of the n-th lamp
     * @param n lamp's number
     */
    public void changeState(int n) {
        if(n < SIZE && n >= 0) {
            lamps[n].changeState();
        }
    }

    /** Printing the lamps layout */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        background.paintComponent(g2d);
        g2d.setFont(DEFAULT_FONT);
        g2d.setColor(FONT_COLOR);

        int pom = 0;//translation for letter like W,I,O,M
        for(int i=0;i<SIZE;i++) {
            if (lamps[i].isVisible()) {
                g2d.drawImage(lamps[i].imageOn, lamps[i].getX(), lamps[i].getY(), this);
                pom= UtilityHelper.transformLetterInString(i);
                g2d.drawString(""+LETTERS.charAt(i),pom+lamps[i].getX()+25,lamps[i].getY()+75);
            }
            if (!lamps[i].isVisible()) {
                g2d.drawImage(lamps[i].imageOff, lamps[i].getX(), lamps[i].getY(), this);
                pom=UtilityHelper.transformLetterInString(i);
                g2d.drawString(""+LETTERS.charAt(i),pom+lamps[i].getX()+25,lamps[i].getY()+75);
            }
        }
        currentEnigma.draw(g2d);
    }
}
