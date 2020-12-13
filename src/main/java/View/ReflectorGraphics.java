package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ReflectorGraphics extends JPanel {

    private static final Font DEFAULT_FONT= new Font("TimesNewRoman", Font.BOLD, 15);
    private static final Color DEFAULT_COLOR=new Color(30,30,30);
    private static BufferedImage reflectorImage;
    static{
        File imageFile1=new File("reflector.png");
        try {
            reflectorImage= ImageIO.read(imageFile1);
        } catch (IOException e) {
            System.err.println("Image could not be loaded.");
            e.printStackTrace();
        }
    }

    private int x,y;
    private String name;

    public ReflectorGraphics(String name, int x,int y)
    {
       this.x=x;
       this.y=y;
       this.name=name;
    }

    public void paintComponent(Graphics g)
    {

            Graphics2D g2d= (Graphics2D) g;
            g2d.setFont(DEFAULT_FONT);
            g2d.setColor(DEFAULT_COLOR);
            g2d.drawImage(reflectorImage,x,y,this);
            g2d.drawString(name, x+5, y+25);
    }

}
