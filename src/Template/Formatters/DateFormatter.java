package Template.Formatters;

import javax.swing.text.MaskFormatter;

public final class DateFormatter extends MaskFormatter{
    private static volatile DateFormatter instance = null;

    private DateFormatter() {
        super();
        try {
            this.setMask("##/##/####");
            this.setPlaceholderCharacter('0');
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DateFormatter getInstance() {
        if (instance == null) {
            synchronized (DateFormatter.class) {
                if(instance == null) {
                    instance = new DateFormatter();
                }
            }
        }
        return instance;
    }
}
