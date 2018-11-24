package EnigmaMachine;

import Frames.UtilityHelper;

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

    private Rotor Current1, Current2, Current3;
    private Reflector CurrentReflector;

    public Enigma(Rotor rotor1, Rotor rotor2, Rotor rotor3, Reflector reflector)
    {
        this.CurrentReflector=reflector;
        this.Current1=rotor1;
        this.Current2=rotor2;
        this.Current3=rotor3;

    }

    public Enigma() //setting (000) BD,III,II,I
    {
        this(new Rotor(ROTOR1),new Rotor(ROTOR2),new Rotor(ROTOR3),new Reflector(REFLECTOR_MODEL_BD));

    }

    public void setEnigma(int a, int b, int c)
    {
        Current1.setPosition(a);
        Current2.setPosition(b);
        Current3.setPosition(c);
    }

    public char encrypt(char c)
    {
        Current1.turnover();
        if(Current1.getNotch()==Current1.getPosition()) Current2.turnover();
        if(Current2.getNotch()==Current2.getPosition()) Current3.turnover();

        int temp=UtilityHelper.charToInt(c);

        temp=Current1.work(temp);
        temp=Current2.work(temp);
        temp=Current3.work(temp);

        temp=CurrentReflector.reflect(temp);

        temp=Current3.work(temp);
        temp=Current2.work(temp);
        temp=Current1.work(temp);

        char result=UtilityHelper.intToChar(temp);
        return result;
    }
}
