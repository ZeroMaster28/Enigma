package EnigmaMachine;

import Frames.UtilityHelper;

import java.awt.*;

public class Enigma {

    private static final String ROTOR1= "EKMFLGDQVZNTOWYHXUSPAIBRCJr";
    private static final String ROTOR2= "AJDKSIRUXBLHWTMCQGZNPYFVOEf";
    private static final String ROTOR3= "BDFHJLCPRTXVZNYEIWGAKMUSQOw";
    private static final String ROTOR4= "ESOVPZJAYQUIRHXLNFTGKDCMWBk";
    private static final String ROTOR5= "VZBRGITYUPSDNHLXAWMJQOFECKa";
    private static final String ROTOR6= "JPGVOUMFYQBENHZRDKASXLICTWn";
    private static final String ROTOR7= "NZJHGRCXMYSWBOUFAIVLPEKQDTn";
    private static final String ROTOR8= "FKQHTLXOCBJSPDZRAMEWNIUYGVn";

    private static final String REFLECTOR_MODEL_BD="AE,BN,CK,DQ,FU,GY,HW,IJ,LO,MP,RX,SZ,TV";
    private static final String REFLECTOR_MODEL_CD="AR,BD,CO,EJ,FN,GT,HK,IV,LM,PW,QZ,SX,UY";

    private Rotor[] rotors;
    private Reflector CurrentReflector;
    private int[] startPosition;

    public Enigma(Reflector reflector,Rotor...rotors)
    {
        this.CurrentReflector=reflector;
        this.rotors=rotors;
        this.startPosition=new int[rotors.length];
    }

    public Enigma() //setting (000) BD,III,II,I
    {
        this(new Reflector(REFLECTOR_MODEL_BD),new Rotor(ROTOR1),new Rotor(ROTOR2),new Rotor(ROTOR3));

    }
    public void setEnigma(int...tab)
    {
        if(tab==null) return;

        startPosition=tab;
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].setPosition(tab[i]);
        }

    }
    public int encrypt(int n)
    {
        rotors[0].turnover();

        for (int i = 0; i < rotors.length-1; i++) {
            if(rotors[i].getNotch()==rotors[i].getPosition())rotors[i+1].turnover();
            else break;
        }

       int temp=n;

        for (int i = 0; i < rotors.length; i++) {
            temp=rotors[i].work(temp,false);
        }

        temp=CurrentReflector.reflect(temp);

        for (int i = 0; i < rotors.length; i++) {
            temp=rotors[rotors.length-1-i].work(temp,true);
        }
        return temp;
    }
    public void restartMachine()
    {
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].setPosition(startPosition[i]);
        }
    }

    public void draw(Graphics2D g2d)
    {
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].draw(g2d);
        }
    }
}
