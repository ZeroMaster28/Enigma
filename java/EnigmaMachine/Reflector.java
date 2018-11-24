package EnigmaMachine;

import Frames.UtilityHelper;

public class Reflector {
    private int[] reflection;

    public Reflector(String string)
    {   reflection=new int[26];

        String[] parts= string.split(",");
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
}
