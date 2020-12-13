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

/**
 * Class represents information about rotor's single image and its layout
 */
public class RotorGraphics extends JPanel {

    /** Initial translation for the first rotor image */
    public static final int TRANSLATION_OF_X = 60;
    public static final int TRANSLATION_OF_Y = 20;

    /** Relative positions of font on rotor's image */
    private static final int FONT_X = 10;
    private static final int FONT_Y = -5;

    /** Px distance between rotors images */
    private static final int DISTANCE = 50;

    /** Font default options */
    private static final Font DEFAULT_FONT=new Font("Arial", Font.BOLD, 20);
    private static final Color FONT_COLOR= new Color(30,30,30);

    /** Rotors that are created so far */
    static public int numberOfRotors = Enigma.getNumberOfRotors();

    /** Component image */
    static BufferedImage image;

    static {
        File imageFile = new File(ResourcePathHandler.getImagePath("rotor.png"));
        try {
          image = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }

    /** Number of currently created rotor in the layout */
    private int numberOfRotor;

    /** Currently observed rotor within the layout */
    private int position;

    public RotorGraphics(int position) {
        ++numberOfRotors;
        numberOfRotor = numberOfRotors;
        this.position=position;
    }

    /** Paints single rotor component */
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setFont(DEFAULT_FONT);
        g2d.setColor(FONT_COLOR);
        g2d.drawImage(image, TRANSLATION_OF_X + DISTANCE*numberOfRotor, TRANSLATION_OF_Y , this);
        for(int i=0; i<3; i++) {
            g2d.drawString(""+ UtilityHelper.intToChar((position+1-i)%26),
                    TRANSLATION_OF_X + DISTANCE*numberOfRotor+FONT_X, TRANSLATION_OF_Y +30*(3-i)+FONT_Y);
        }
    }

    /** Changes rotor that is currently observed within the layout */
    public void changePosition(int n) {
        position = n;
    }
}
