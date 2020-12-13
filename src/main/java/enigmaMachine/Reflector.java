package enigmaMachine;

import view.elements.ReflectorGraphics;
import view.elements.RotorGraphics;
import util.UtilityHelper;

import java.awt.*;

public class Reflector {
    private int[] reflection;
    private ReflectorGraphics reflectorGraphics;

    public Reflector(String string)
    {   reflection=new int[26];

        String[] parts= string.split(",");
        String str=parts[0].equals("AE")?"BD":"CD";
        reflectorGraphics=new ReflectorGraphics(str, RotorGraphics.TRANSLATION_OF_X+30*(13),RotorGraphics.TRANSLATION_OF_Y );

        for(int i=0;i<13;i++)
        {
            reflection[UtilityHelper.charToInt(parts[i].charAt(0))]=UtilityHelper.charToInt(parts[i].charAt(1));
            reflection[UtilityHelper.charToInt(parts[i].charAt(1))]=UtilityHelper.charToInt(parts[i].charAt(0));
        }


    }

    public int reflect(int i)
    {
        return reflection[i];
    }

    public void draw(Graphics2D g2d)
    {
        reflectorGraphics.paintComponent(g2d);
    }
}
