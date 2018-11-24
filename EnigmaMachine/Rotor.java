package EnigmaMachine;
import Frames.UtilityHelper;


public class Rotor {
    private char[] permutation;
    private int notch;
    private int position=0;

    Rotor(String string)
    {
        this.permutation=new char[26];
        for (int i = 0; i < permutation.length; i++) {
            permutation[i] = string.charAt(i);
        }
        notch=UtilityHelper.charToInt(string.charAt(26));
    }
    public void setPosition(int n)
    {
        if(n>=0&&n<26) position=n;
        else;
    }
    public int getPosition()
    {
        return position;
    }
    public void turnover()
    {
        position=(position+1)%26;
    }
    public int getNotch()
    {
        return notch;
    }

    public int work(int i)
    {
        int n=(i+(26-position))%26;
        char c=permutation[n];
        n=UtilityHelper.charToInt(c);
        n=(i+(26-position))%26;
        return n;
    }
}
