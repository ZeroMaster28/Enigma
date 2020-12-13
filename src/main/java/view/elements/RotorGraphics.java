package view.elements;


import enigmaMachine.Enigma;
import util.ResourcePathHandler;
import util.UtilityHelper;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;


public class RotorGraphics extends JPanel {

    //positions beneath are relative
    //positions of rotor's image
    public static final int TRANSLATION_OF_X = 60;
    public static final int TRANSLATION_OF_Y = 20;
    //positions of font on rotor's image
    private static final int FONT_X=10;
    private static final int FONT_Y=-5;
    //distance between rotor images
    private static final int DISTANCE=50;

    private static final Font DEFAULT_FONT=new Font("Arial", Font.BOLD, 20);
    private static final Color FONT_COLOR= new Color(30,30,30);

    //rotors created so far
    static public int numberOfRotors= Enigma.getNumberOfRotors();
    private int numberOfRotor;
    private int position;

    static BufferedImage image;


    static {
        File imageFile = new File(ResourcePathHandler.getImagePath("rotor.png"));
        try {
          image= ImageIO.read(imageFile);


        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }
    public RotorGraphics(int position)
    {

        ++numberOfRotors;
        numberOfRotor=numberOfRotors;
        this.position=position;
    }


    public void paintComponent(Graphics g)
    {
        Graphics2D g2d= (Graphics2D) g;
        g2d.setFont(DEFAULT_FONT);
        g2d.setColor(FONT_COLOR);

        g2d.drawImage(image, TRANSLATION_OF_X + DISTANCE*numberOfRotor, TRANSLATION_OF_Y , this);

        for(int i=0;i<3;i++) {

            g2d.drawString(""+ UtilityHelper.intToChar((position+1-i)%26), TRANSLATION_OF_X +DISTANCE*numberOfRotor+FONT_X, TRANSLATION_OF_Y +30*(3-i)+FONT_Y);
        }
    }

    public void changePosition(int n)
    {
        position=n;
    }

}
