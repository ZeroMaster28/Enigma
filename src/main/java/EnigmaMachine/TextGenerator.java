package EnigmaMachine;


import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TextGenerator {
    private String TEXT_NAME;
    StringBuilder builder=new StringBuilder();
   PrintWriter text;
    public TextGenerator()
    {
       open();
    }

    public void writeDown(char c)
    {
        builder.append(c);
    }

    public void submit()
    {
        text.print(builder.toString());
        text.close();
    }

    public void reset()
    {
        this.save();
        builder=new StringBuilder();
    }
    public void open()
    {
        TEXT_NAME = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Timestamp(System.currentTimeMillis()));

        try {
            this.text = new PrintWriter(TEXT_NAME+".txt");
        } catch(Exception ex)
        {

        }
    }
    public void save()
    {
        text.print(builder.toString());
        text.close();

        this.open();
    }
}
