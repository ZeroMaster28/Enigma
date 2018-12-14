import EnigmaMachine.Enigma;



import ProjectPackage1.UtilityHelper;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class Tests {

    static Enigma[] enigma;
    static String plainText="";
    static final int NUMBER_OF_ITERATIONS=8;
    static int[] numbers_to_encrypt;
    static final int LETTER_TO_ENCRYPT=0; // 0,...,25
    static final int which=0; //0,..,3

    @BeforeClass
    public static void setUp()
    {
        enigma=new Enigma[4];

        enigma[1]=new Enigma();
        enigma[2]=new Enigma();
        enigma[3]=new Enigma();
        enigma[0]=new Enigma();

        //enigma I is basic, 3 simple rotors and BD reflector
        //enigma II has all 7 rotors
        enigma[1].changeRotors(Enigma.ROTORS);
        //enigma III has basic rotors but CD reflector
        enigma[2].changeReflector(0);
        //enigma IV has no rotors but still BD reflector
        //enigma[3].changeRotors("");

        generateLongerPlainText(NUMBER_OF_ITERATIONS);
        numbers_to_encrypt=generateLongerNumbers(new int[]{1,3,4,7,5,2,5,14,24,1,17,25,25});
    }
    @Test
    public void shouldEncrypt()
    {
        int whichEnigma=which;

       int[] tab= new int[numbers_to_encrypt.length*NUMBER_OF_ITERATIONS];

       //encrypting
       for(int i=0;i<tab.length;i++)
       {
           tab[i]=enigma[whichEnigma].encrypt(numbers_to_encrypt[i%numbers_to_encrypt.length]);
       }
       enigma[whichEnigma].restartMachine();

       //decrypting
        for(int i=0;i<tab.length;i++)
        {
            tab[i]=enigma[whichEnigma].encrypt(tab[i]);
        }

        for(int i=0;i<tab.length;i++)
        {
            assertEquals(numbers_to_encrypt[i%numbers_to_encrypt.length],tab[i]);
        }
        enigma[whichEnigma].restartMachine();
    }

    @Test
    public void shouldDrawSameWays()
    {
        String expectedWay="a->j->b->d->q->y->v->m";
        StringBuilder builder= new StringBuilder();

        while(true) {
            Enigma _enigma = enigma[which];
            builder.append(UtilityHelper.intToChar(LETTER_TO_ENCRYPT));

            if (_enigma.rotors.length == 0) {
                builder.append("->" + UtilityHelper.intToChar(_enigma.CurrentReflector.reflect(LETTER_TO_ENCRYPT)));
                break;
            }

            _enigma.rotors[0].turnover();

            for (int i = 0; i < _enigma.rotors.length - 1; i++) {
                if (_enigma.rotors[i].getNotch() == _enigma.rotors[i].getPosition()) _enigma.rotors[i + 1].turnover();
                else break;
            }

            int temp = LETTER_TO_ENCRYPT;

            for (int i = 0; i < _enigma.rotors.length; i++) {
                temp = _enigma.rotors[i].work(temp, false);
                builder.append("->"+UtilityHelper.intToChar(temp));
            }

            temp = _enigma.CurrentReflector.reflect(temp);
            builder.append("->"+UtilityHelper.intToChar(temp));

            for (int i = 0; i < _enigma.rotors.length; i++) {
                temp = _enigma.rotors[_enigma.rotors.length - 1 - i].work(temp, true);
                builder.append("->"+UtilityHelper.intToChar(temp));
            }
            break;
        }
        String text=builder.toString();
        System.out.println("Expected: "+expectedWay);
        System.out.println("Got: "+text);

        assertTrue(expectedWay.equals(text));

    }

    public static void generateLongerPlainText(int n)
    {
        String text="ABCDE";
        StringBuilder builder= new StringBuilder();
        for(int i=0;i<n;i++)
        {
            builder.append(text);
        }
        plainText+=builder.toString();
    }

    public static int[] generateLongerNumbers(int[] tab)
    {
        int[] array= new int[NUMBER_OF_ITERATIONS*tab.length];
        for(int i=0;i<array.length;i++)
        {
            array[i]=tab[i%tab.length];
        }
        return array;
    }

}
