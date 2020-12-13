package view.components;


import util.ResourcePathHandler;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Image component representing Lamp
 */
public class Lamp {

    /** Whether light is on or off */
    private boolean visibility = false;

    /** Character that is written on the lamp */
    private final char letter;

    /** Px coordinates for the component */
    private int x, y;

    /** Images for when the lamp is on or off */
    BufferedImage imageOn;
    BufferedImage imageOff;

    public Lamp(String lightOnFilename, String lightOffFilename, char letter, int x , int y ) {
        this.letter = letter;
        setCoordinates(x,y);
        setImage(lightOnFilename,true);
        setImage(lightOffFilename,false);
        BufferedImage image= visibility?imageOn:imageOff;

    }

    /**
     * Sets image for one of two lamp options
     * @param localization image path
     * @param visible is lamp on or off
     */
    public void setImage(String localization, boolean visible)
    {
        File imageFile = new File(ResourcePathHandler.getImagePath(localization));
        try {
             if(visible) {
                 imageOn = ImageIO.read(imageFile);
             }
             if(!visible) {
                 imageOff = ImageIO.read(imageFile);
             }
        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }

    /** Sets coordinates of the component */
    public void setCoordinates(int x, int y) {
        if(x >= 0 && y>= 0) {
            this.x = x;
            this.y = y;
        }
        else{
            this.x=0;
            this.y=0;
        }
    }

    public boolean isVisible() {
        return visibility;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /** Changes state of the lamp i.e switch it on or off*/
    public void changeState() {
        visibility =! (visibility);
    }
}
