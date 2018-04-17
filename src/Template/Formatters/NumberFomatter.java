package Template.Formatters;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public final class NumberFomatter extends MaskFormatter{
    private static volatile NumberFomatter instance = null;
    private NumberFomatter() {
        super();
        try {
            this.setMask("###.###.###.###.###.###");
            this.setPlaceholderCharacter('_');
            //this.setPlaceholderCharacter('0');
        } catch (ParseException pe) {
            pe.printStackTrace();
        }
    }

    public static NumberFomatter getInstance() {
        if (instance == null) {
            synchronized (NumberFomatter.class) {
                if (instance == null)
                    instance = new NumberFomatter();
            }
        }
        return instance;
    }
}
