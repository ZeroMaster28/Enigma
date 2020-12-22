package view.frames;

import javax.swing.*;

/**
 * Frame that shows info about the application
 */
public class HelpFrame extends JFrame {

    public HelpFrame() {
        super( "Help" );
        setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        setSize(500, 200);
        setLocation(50,50);
        setResizable(false);
        JLabel label = new JLabel();
        label.setText("<html>  This is a project for Applied Java subject/AGH UST classes. All rights reserved :) <br/> " +
            "You can select Rotors (particular permutations of letters) in the way you want \n   " +
            "(order and starting point). Rotors, thanks to movement system, create additional \n   " +
            "Caesar cipher between each other. Remember to restart machine " +
            "when you want to start something new or at least save current position. \n  " +
            "Enjoy!</html>");
        this.add(label);
    }
}
