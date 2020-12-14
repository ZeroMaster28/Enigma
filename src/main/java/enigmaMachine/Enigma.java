package enigmaMachine;

import view.components.RotorGraphics;
import util.UtilityHelper;

import java.awt.*;
import java.util.Arrays;

/** Enigma machine implementation */
public class Enigma {

    private static final String ROTOR1= "EKMFLGDQVZNTOWYHXUSPAIBRCJr";
    private static final String ROTOR2= "AJDKSIRUXBLHWTMCQGZNPYFVOEf";
    private static final String ROTOR3= "BDFHJLCPRTXVZNYEIWGAKMUSQOw";
    private static final String ROTOR4= "ESOVPZJAYQUIRHXLNFTGKDCMWBk";
    private static final String ROTOR5= "VZBRGITYUPSDNHLXAWMJQOFECKa";
    private static final String ROTOR6= "JPGVOUMFYQBENHZRDKASXLICTWn";
    private static final String ROTOR7= "NZJHGRCXMYSWBOUFAIVLPEKQDTn";
    private static final String ROTOR8= "FKQHTLXOCBJSPDZRAMEWNIUYGVn";

    public static String[] ROTORS={ROTOR1, ROTOR2, ROTOR3, ROTOR4, ROTOR5, ROTOR6, ROTOR7};

    private static final String REFLECTOR_MODEL_BD="AE,BN,CK,DQ,FU,GY,HW,IJ,LO,MP,RX,SZ,TV";
    private static final String REFLECTOR_MODEL_CD="AR,BD,CO,EJ,FN,GT,HK,IV,LM,PW,QZ,SX,UY";

    private static int numberOfRotors = 0;

    public Rotor[] rotors;
    public Reflector CurrentReflector;
    private Integer[] startPosition;
    private final OutputTextGenerator outputTextGenerator;

    public Enigma(Reflector reflector, Rotor...rotors) {
        this.outputTextGenerator =new OutputTextGenerator();
        this.CurrentReflector=reflector;
        this.rotors=rotors;
        this.startPosition=new Integer[rotors.length];
        Arrays.fill(startPosition, 0);
        numberOfRotors=rotors.length;
    }

    public Enigma() {
        //setting the initial machine for (000) BD,III,II,I
        this(new Reflector(REFLECTOR_MODEL_BD), new Rotor(ROTOR1), new Rotor(ROTOR2), new Rotor(ROTOR3));

    }

    /** Sets starting position for the machine */
    public void setEnigma(Integer...tab) {
        if(tab==null) {
            return;
        }
        startPosition = tab;
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].setPosition(tab[i]);
        }
    }

    /** Encrypts number that corresponds to the text's single character */
    public int encrypt(int n) {
        if(rotors.length==0) {
            return CurrentReflector.reflect(n);
        }
        rotors[0].turnover();
        for (int i = 0; i < rotors.length-1; i++) {
            if(rotors[i].getNotch()==rotors[i].getPosition())rotors[i+1].turnover();
            else break;
        }
        int temp = n;
        for (int i = 0; i < rotors.length; i++) {
            temp=rotors[i].work(temp,false);
        }
        temp=CurrentReflector.reflect(temp);
        for (int i = 0; i < rotors.length; i++) {
            temp=rotors[rotors.length-1-i].work(temp,true);
        }
        outputTextGenerator.writeDown(UtilityHelper.intToChar(temp));
        return temp;
    }

    /** Sets the machine to its starting position */
    public void restartMachine() {
        outputTextGenerator.reset();
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].setPosition(startPosition[i]);
        }
    }

    /** Drawing current state of rotors while they're changing */
    public void draw(Graphics2D g2d) {
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].draw(g2d);
        }
        CurrentReflector.draw(g2d);
    }

    /** Applies new set of rotors */
    public void changeRotors(String...rotors) {
        Rotor[] rotor = new Rotor[rotors.length];
        RotorGraphics.numberOfRotors = 0;
        for(int i=0; i<rotors.length; i++) {
            rotor[i] = new Rotor(rotors[i]);
        }
        this.rotors=rotor;
        startPosition=new Integer[rotors.length];
        this.startPosition=new Integer[rotors.length];
        for(int i=0; i<startPosition.length; i++) {
            startPosition[i] = new Integer(0);
        }
        numberOfRotors=rotors.length;
    }

    /**
     * Applies one of two accessible reflectors based on number
     * @param i if <code>1</code> then BD reflector is applied, CD otherwise
     */
    public void changeReflector(int i) {
        Reflector reflector;
        if(i == 1) {
            reflector = new Reflector(REFLECTOR_MODEL_BD);
        }
        else {
            reflector = new Reflector(REFLECTOR_MODEL_CD);
        }
        this.CurrentReflector = reflector;
    }

    public static int getNumberOfRotors() {
        return numberOfRotors;
    }

    /** Finishes output generation as well as the whole application workflow */
    public void endWork() {
        outputTextGenerator.submit();
        System.exit(0);
    }
}
