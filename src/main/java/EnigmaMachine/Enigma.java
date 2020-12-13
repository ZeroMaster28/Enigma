package EnigmaMachine;

import View.Elements.RotorGraphics;
import util.UtilityHelper;

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

    public static String[] ROTORS={ROTOR1,ROTOR2,ROTOR3,ROTOR4,ROTOR5,ROTOR6,ROTOR7};

    private static final String REFLECTOR_MODEL_BD="AE,BN,CK,DQ,FU,GY,HW,IJ,LO,MP,RX,SZ,TV";
    private static final String REFLECTOR_MODEL_CD="AR,BD,CO,EJ,FN,GT,HK,IV,LM,PW,QZ,SX,UY";
    private static int numberOfRotors=0;

    public Rotor[] rotors;
    public Reflector CurrentReflector;
    private Integer[] startPosition;
    private TextGenerator textGenerator;

    public Enigma(Reflector reflector,Rotor...rotors)
    {
        this.textGenerator=new TextGenerator();
        this.CurrentReflector=reflector;
        this.rotors=rotors;
        this.startPosition=new Integer[rotors.length];
        for(int i=0;i<startPosition.length;i++)
        {
            startPosition[i]=new Integer(0);
        }
        numberOfRotors=rotors.length;
    }

    public Enigma() //setting (000) BD,III,II,I
    {
        this(new Reflector(REFLECTOR_MODEL_BD),new Rotor(ROTOR1), new Rotor(ROTOR2), new Rotor(ROTOR3));

    }
    public void setEnigma(Integer...tab)
    {
        if(tab==null) return;

        startPosition=tab;
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].setPosition(tab[i]);
        }

    }
    public int encrypt(int n)
    {
        if(rotors.length==0) return CurrentReflector.reflect(n);

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
        textGenerator.writeDown(UtilityHelper.intToChar(temp));
        return temp;
    }
    public void restartMachine()
    {
        textGenerator.reset();
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].setPosition(startPosition[i]);
        }
    }

    public void draw(Graphics2D g2d)
    {
        for (int i = 0; i < rotors.length; i++) {
            rotors[i].draw(g2d);
        }
        CurrentReflector.draw(g2d);

    }


    public void changeRotors(String...rotors)
    {

        Rotor[] rotor=new Rotor[rotors.length];
        RotorGraphics.numberOfRotors=0;

        for(int i=0;i<rotors.length;i++)
        {
            rotor[i]=new Rotor(rotors[i]);
        }
        this.rotors=rotor;

        startPosition=new Integer[rotors.length];
        this.startPosition=new Integer[rotors.length];
        for(int i=0;i<startPosition.length;i++)
        {
            startPosition[i]=new Integer(0);
        }
        numberOfRotors=rotors.length;
    }

    public void changeReflector(int i)
    {
        Reflector refl;
        if(i==1) refl=new Reflector(REFLECTOR_MODEL_BD);
        else refl=new Reflector(REFLECTOR_MODEL_CD);

        this.CurrentReflector=refl;
    }

    public static int getNumberOfRotors()
    {

        return numberOfRotors;

    }

    public void endWork()
    {
        textGenerator.submit();
        System.exit(0);
    }

}
