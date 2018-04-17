package BasicComponents.CodeTesting;

import Template.Formatters.NumberFomatter;

import javax.swing.*;
import javax.swing.text.DefaultFormatterFactory;

public class FormattedDateTextField extends JFormattedTextField{
    public FormattedDateTextField() {
        super();
        setFormatterFactory(new DefaultFormatterFactory(NumberFomatter.getInstance()));
    }
}
