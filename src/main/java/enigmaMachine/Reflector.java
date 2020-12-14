package enigmaMachine;

import view.components.ReflectorGraphics;
import view.components.RotorGraphics;
import util.UtilityHelper;

import java.awt.*;

/**
 * Class represents reflector i.e permutation that reflects the signal of
 * enigma machine's input
 */
public class Reflector {

    /** Permutation for the given reflector */
    private final int[] reflection;

    /** Graphics for this reflector */
    private final ReflectorGraphics reflectorGraphics;

    public Reflector(String string) {
        reflection = new int[26];
        String[] parts= string.split(",");
        String str = parts[0].equals("AE") ? "BD" : "CD";
        reflectorGraphics = new ReflectorGraphics(str, RotorGraphics.TRANSLATION_OF_X + 30 * (13),
                RotorGraphics.TRANSLATION_OF_Y);
        for(int i=0;i<13;i++) {
            reflection[UtilityHelper.charToInt(parts[i].charAt(0))] = UtilityHelper.charToInt(parts[i].charAt(1));
            reflection[UtilityHelper.charToInt(parts[i].charAt(1))] = UtilityHelper.charToInt(parts[i].charAt(0));
        }
    }

    /**
     * Transforms given number with usage of the reflector's permutation
     * @param i number to transform
     * @return transformed number
     */
    public int reflect(int i) {
        return reflection[i];
    }

    /** Paints reflector component on the given layout in case when reflector's state changes
     * @param g2d layout that contains reflector component
     */
    public void draw(Graphics2D g2d) {
        reflectorGraphics.paintComponent(g2d);
    }
}
