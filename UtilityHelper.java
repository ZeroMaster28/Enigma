package Frames;

public class UtilityHelper {
    private UtilityHelper(){};

    static int transform(int i) //transforms received keyboard value to draw them on display keyboard
    {
        switch(i)
        {
            case 0: return 10;
            case 1: return 23;
            case 2: return 21;
            case 3: return 12;
            case 4: return 2;
            case 5: return 13;
            case 6: return 14;
            case 7: return 15;
            case 8: return 7;
            case 9: return 16;
            case 10: return 17;
            case 11: return 18;
            case 12: return 25;
            case 13: return 24;
            case 14: return 8;
            case 15: return 9;
            case 16: return 0;
            case 17: return 3;
            case 18: return 11;
            case 19: return 4;
            case 20: return 6;
            case 21: return 22;
            case 22: return 1;
            case 23: return 20;
            case 24: return 5;
            case 25: return 19;
            default: return -1;
        }
    }
}
