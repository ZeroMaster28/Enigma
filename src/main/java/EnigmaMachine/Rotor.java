package EnigmaMachine;

import util.UtilityHelper;
import View.Elements.RotorGraphics;

import java.awt.*;

public class Rotor {

    private RotorGraphics rotorGraphics;
    private int[] permutation;
    private int[] reversedPerm;
    private int notch;
    private int position=0;

    public Rotor(String string)
    {
        rotorGraphics= new RotorGraphics(0);

        permutation=new int[26];
        for (int i = 0; i < 26; i++) {
            permutation[i] = UtilityHelper.charToInt(string.charAt(i));
        }
        notch=UtilityHelper.charToInt(string.charAt(26));

        reversedPerm=new int[26];
        for (int i = 0; i <26; i++)
        {
            reversedPerm[permutation[i]]=i;
        }
    }
    public void setPosition(int n)
    {
        if(n>=0&&n<26)
        {
            position = n;
            rotorGraphics.changePosition(n);
        }
        else;
    }
    public int getPosition()
    {
        return position;
    }
    public void turnover()
    {
        position=(position+1)%26;
        rotorGraphics.changePosition(position);
    }
    public int getNotch()
    {
        return notch;
    }

    public int work(int i,boolean bool) //direction bool: true-reverse/ false-normal
    {
        int n=(i+position)%26;
        if(bool==false)n=permutation[n];
        if(bool==true) n=reversedPerm[n];

        if(n-position<0) n=26+n-position;
        else {
            n=n-position;
        }


        return n;
    }

    public void draw(Graphics2D g2d)
    {
     rotorGraphics.paintComponent(g2d);
    }
}
