package View;

import EnigmaMachine.Enigma;
import util.UtilityHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class SettingsFrame extends JFrame implements KeyListener {

    static Enigma enigma;
    static private JFrame mainFrame;

    private JLabel[] rotorNumber=new JLabel[7];
    private JLabel rotorIdentifier=new JLabel("Rotor"),rotorStartingPoint=new JLabel("Start  ");
    private JLabel reflectorIdentifier=new JLabel("Reflector");

    private static int[] identifiers= new int[15];
    static
    {
    SettingsFrame.nullifyIdentifiers();
    }
    private static JComboBox<String>[] rotorsToChoose = new JComboBox[7];
    static
    {
        for (int i = 0; i < rotorsToChoose.length; i++) {
            rotorsToChoose[i]=new JComboBox<String>();
            Dimension d=rotorsToChoose[i].getPreferredSize();
            rotorsToChoose[i].setPreferredSize(new Dimension(50,d.height));

            rotorsToChoose[i].addItem("none");
            rotorsToChoose[i].addItem("EKMFLGDQVZNTOWYHXUSPAIBRCJ_r");
            rotorsToChoose[i].addItem("AJDKSIRUXBLHWTMCQGZNPYFVOE_f");
            rotorsToChoose[i].addItem("BDFHJLCPRTXVZNYEIWGAKMUSQO_w");
            rotorsToChoose[i].addItem("ESOVPZJAYQUIRHXLNFTGKDCMWB_k");
            rotorsToChoose[i].addItem("VZBRGITYUPSDNHLXAWMJQOFECK_a");
            rotorsToChoose[i].addItem("JPGVOUMFYQBENHZRDKASXLICTW_n");
            rotorsToChoose[i].addItem("NZJHGRCXMYSWBOUFAIVLPEKQDT_n");

            final int j=i;
            rotorsToChoose[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    SettingsFrame.actionPerformed(actionEvent,j);
                    if(enigma!=null)SettingsFrame.refactorEnigmaRotor();
                }
            });
        }


    }
    private static JComboBox<String>[] startingToChoose= new JComboBox[7];
    static
    {
        for (int i = 0; i < startingToChoose.length; i++) {
            final int s=i;
            startingToChoose[i]=new JComboBox<String>();
            startingToChoose[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    SettingsFrame.actionPerformed(actionEvent,s+7);
                    if(enigma!=null)SettingsFrame.refactorEnigmaStart();
                }
            });
            for(int j=0;j<26;j++)
            {
                startingToChoose[i].addItem(""+UtilityHelper.intToChar(j));
            }
        }
    }
    private static JComboBox<String> reflectorToChoose=new JComboBox();
    static
    {
       reflectorToChoose.addItem("(BD model): AE,BN,CK,DQ,FU,GY,HW,IJ,LO,MP,RX,SZ,TV");
       reflectorToChoose.addItem("(CD model): AR,BD,CO,EJ,FN,GT,HK,IV,LM,PW,QZ,SX,UY");
       reflectorToChoose.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
               SettingsFrame.actionPerformed(actionEvent,14);
               if(enigma!=null)SettingsFrame.refactorEnigmaReflector();
           }
       });
    }
    public SettingsFrame(Enigma enigma, JFrame mainFrame)
    {
        super( "Settings" );
        setDefaultCloseOperation(this.HIDE_ON_CLOSE);
        setSize(450, 300);
        setLocation(50,50);
        setResizable(false);
        this.setBackground(Color.BLACK);


        //adding enigma
        SettingsFrame.enigma=enigma;
        this.mainFrame=mainFrame;
        //


        GridBagLayout layout = new GridBagLayout();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor=GridBagConstraints.CENTER;
        gbc.fill=GridBagConstraints.HORIZONTAL;


        this.setLayout(layout);

        for (int i = 0; i < rotorNumber.length; i++) {
            rotorNumber[i]=new JLabel(""+(i+1)+"  ");
        }


        rotorIdentifier.setHorizontalAlignment(JTextField.CENTER);
        rotorStartingPoint.setHorizontalAlignment(JTextField.CENTER);
        reflectorIdentifier.setHorizontalAlignment(JTextField.CENTER);

        gbc.gridy=0;
        gbc.gridx=20;
        this.add(rotorIdentifier,gbc);
        gbc.gridx=21;
        this.add(rotorStartingPoint,gbc);


        for (int i = 0; i < rotorsToChoose.length; i++) {
            gbc.gridy =10+ 10*i;

            gbc.gridx=0;
            this.add(rotorNumber[i], gbc);
            gbc.gridx = 20;
            this.add(rotorsToChoose[i],gbc);
            gbc.gridx=21;
            this.add(startingToChoose[i],gbc);
        }

        gbc.gridx=20;
        gbc.gridy=110;
        this.add(reflectorIdentifier,gbc);
        gbc.gridy=115;
        this.add(reflectorToChoose,gbc);

        addKeyListener(this);
        setFocusable(true);
        //setFocusTraversalKeysEnabled(false);
    }

    static public void actionPerformed(ActionEvent e, int i)
    {
        JComboBox jcombobx=(JComboBox) e.getSource();
        int n= jcombobx.getSelectedIndex();
        identifiers[i]=n+1;
    }

    public static void refactorEnigmaRotor()
    {
        ArrayList<String> rotors=new ArrayList<>();
        for(int i=0;i<7;i++)
        {
            if(identifiers[i]>1) rotors.add(Enigma.ROTORS[identifiers[i]-2]);
        }

        String[] array=new String[rotors.size()];
        array=rotors.toArray(array);
        enigma.changeRotors(array);
        refactorEnigmaStart();

    }

    public static void refactorEnigmaStart()
    {
        ArrayList<Integer> position=new ArrayList<>();
        for(int i=7;i<14;i++)
        {
            if(identifiers[i]>=0) position.add(identifiers[i]-1);
        }
        if(position.size()>0) {
            Integer[] array = new Integer[position.size()];
            array = position.toArray(array);
            enigma.setEnigma(array);
        }
        mainFrame.repaint();

    }

    public static void refactorEnigmaReflector()
    {
        if(identifiers[14]==1) enigma.changeReflector(1);
        if(identifiers[14]==2) enigma.changeReflector(2);
        mainFrame.repaint();
    }

    public static void nullifyIdentifiers()
    { //should make indetifiers array all -1

        for(int i=0;i<15;i++) identifiers[i]=-1;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        if(keyEvent.getKeyCode()==KeyEvent.VK_ESCAPE) {
            enigma.endWork();
            System.exit(0);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}