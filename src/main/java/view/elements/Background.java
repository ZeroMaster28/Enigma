package view.elements;

import util.ResourcePathHandler;

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
        File imageFile = new File(ResourcePathHandler.getImagePath("background.jpg"));
        try {
             background = ImageIO.read(imageFile);
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
