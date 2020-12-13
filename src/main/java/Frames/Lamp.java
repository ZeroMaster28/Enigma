package Frames;


import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/*
This is the class for lamp image in our enigma machine.
 */

public class Lamp {

    private boolean visibility=false; //whether light is on or off
    private char letter; //letter on the lamp

    //coordinates
    private int x, y;

    //images
    BufferedImage imageOn;
    BufferedImage imageOff;

    public Lamp(String localization1, String localization2, char letter, int x , int y )
    {

        setCoordinates(x,y);
        this.letter=letter;
        setImage(localization1,true);
        setImage(localization2,false);

        BufferedImage image= visibility?imageOn:imageOff;

    }

    public void setImage(String localization, boolean visible)
    {
        File imageFile = new File(localization);
        try {
             if(visible) imageOn= ImageIO.read(imageFile);
             if(!visible) imageOff=ImageIO.read(imageFile);

        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }

    public void setCoordinates(int x, int y)
    {
        if(x>=0&&y>=0) {
            this.x = x;
            this.y = y;
        }
        else{
            this.x=0;
            this.y=0;
        }
    }

    public boolean isVisible()
    {
        return visibility;
    }

    public int getX()
    {
        return this.x;
    }
    public int getY()
    {
        return this.y;
    }

    public void changeState()
    {
        visibility=!(visibility);
    }
}
