package enigmaMachine;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * This class generates output as the encrypted text which is saved within a single file.
 */
public class OutputTextGenerator {

    /** Directory name for the output files */
    private static final String OUTPUT_DIR_NAME = "output";

    /** Info for every encrypted text */
    private static final String TEXT_INFO = "ENCRYPTED:";

    /** Filename for application current run output */
    private static final String TEXT_FILENAME = OUTPUT_DIR_NAME + File.separator +
            new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(
            new Timestamp(System.currentTimeMillis())) + ".txt";

    /** Text to be saved */
    StringBuilder textBuilder = new StringBuilder();

    /** Output writer*/
    PrintWriter output;


    public OutputTextGenerator() {
        File outputDir = new File("output");
        if(!outputDir.exists()) {
            if(!outputDir.mkdir()) {
                throw new RuntimeException("Could not create the output directory!");
            }
        }
        startNew();
    }

    /**
     * Writes a single character to the output
     * @param c character to be saved
     */
    public void writeDown(char c) {
        textBuilder.append(c);
    }

    /** Finishes output writing */
    public void submit() {
        output.append(textBuilder.toString());
        output.close();
    }

    /** Saves current and starts new encrypted text block */
    public void reset() {
        output.append(textBuilder.toString());
        output.close();
        startNew();
        textBuilder = new StringBuilder();
    }

    /** Restarts the output generation */
    private void startNew() {
        try {
            this.output = new PrintWriter(new FileOutputStream(TEXT_FILENAME, true));
            this.output.append("\n\r");
            this.output.append(TEXT_INFO);
            this.output.append("\n\r");
        } catch(Exception ex) {
            //
        }
    }
}
