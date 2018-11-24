package Frames;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.*;
import java.awt.*;

class RotorGraphics extends JPanel {
    private static final int TRANSLATION_OF_X = 0;
    private static final int TRANSLATION_OF_Y = 0;

    private int x, y;
    static BufferedImage image;

    static {
        File imageFile = new File("rotor.png");
        try {
          image= ImageIO.read(imageFile);


        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }
    private RotorGraphics(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    public static RotorGraphics[] getInstance()
    {
        RotorGraphics[] rtr= new RotorGraphics[3];
        for(int i=0;i<3;i++) rtr[i]=new RotorGraphics(200+50*i,30);

        return rtr;
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2d= (Graphics2D) g;
        g2d.drawImage(image,TRANSLATION_OF_X+x,TRANSLATION_OF_Y+y,this);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

}
