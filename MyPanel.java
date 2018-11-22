package Frames;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;

public class MyPanel extends JPanel {
    public MyPanel() {

        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        String[] letters=new String[]{"Q","W","E","R","T","Y","U","I","O","P"};

        for(int i=0;i<10;i++)
        {
            Ellipse2D circle = new Ellipse2D.Double(10, 10, 100, 100);
            g2d.draw(circle);
            g2d.setColor(Color.GRAY);
            g2d.fillOval(10+110*i,10,100,100);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
            g2d.drawString(letters[i], 50+110*i, 50);
        }
    }
}