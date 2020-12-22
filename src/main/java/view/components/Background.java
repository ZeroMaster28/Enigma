package view.components;

import util.ImageLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Image component for the background
 */
public class Background extends JPanel {

    private BufferedImage background;


    public Background() {
        try {
             background = ImageLoader.getInstance().loadImage("background.jpg");
        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(background,0,0,this);
    }
}
