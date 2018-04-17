package BasicComponents.CodeTesting;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.text.NumberFormat;

public class FormattedIntegerTextField extends JFormattedTextField {
    public FormattedIntegerTextField() {
        super();
        NumberFormat format = NumberFormat.getIntegerInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(0);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        this.setFormatter(formatter);
    }
}

