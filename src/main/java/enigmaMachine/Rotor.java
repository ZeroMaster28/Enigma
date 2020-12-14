package enigmaMachine;

import util.UtilityHelper;
import view.components.RotorGraphics;

import java.awt.*;

/**
 * Class represents rotor i.e permutation for the signal of
 * enigma machine's input
 */
public class Rotor {

    /** Graphics for the given rotor */
    private final RotorGraphics rotorGraphics;

    /** Permutation represented by the given rotor */
    private final int[] permutation;

    /** Reversed permutation in case when signal is reflected and goes through the rotor once again */
    private final int[] reversedPerm;

    /** Rotor's notch */
    private final int notch;

    /** Rotor's current position */
    private int position = 0;


    public Rotor(String string) {
        rotorGraphics= new RotorGraphics(0);
        permutation = new int[26];
        for (int i = 0; i < 26; i++) {
            permutation[i] = UtilityHelper.charToInt(string.charAt(i));
        }
        notch = UtilityHelper.charToInt(string.charAt(26));
        reversedPerm=new int[26];
        for (int i = 0; i <26; i++) {
            reversedPerm[permutation[i]]=i;
        }
    }

    public void setPosition(int n) {
        if(n >= 0 && n < 26) {
            position = n;
            rotorGraphics.changePosition(n);
        }
    }
    public int getPosition() {
        return position;
    }

    /** Changes rotor position to its next state */
    public void turnover() {
        position = (position + 1) % 26;
        rotorGraphics.changePosition(position);
    }

    public int getNotch() {
        return notch;
    }

    /**
     * Transforms number via rotor permutation and due to signal direction
     * @param i number to be transformed
     * @param isReversed is signal direction reversed
     * @return
     */
    public int work(int i, boolean isReversed) {
        int n = (i + position) % 26;
        if(!isReversed) {
            n = permutation[n];
        }
        if(isReversed) {
            n = reversedPerm[n];
        }
        if(n-position < 0) {
            n = 26 + n - position;
        }
        else {
            n= n - position;
        }
        return n;
    }

    /**
     * Draws rotor due to its current state on the given layout
     * @param g2d layout
     */
    public void draw(Graphics2D g2d) {
     rotorGraphics.paintComponent(g2d);
    }
}
