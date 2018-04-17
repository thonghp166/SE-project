package BasicComponents.Form;

import javax.swing.*;
import java.awt.*;
import java.text.Format;

public class FormTextField extends JTextField{
    private final int WIDTH = 200;
    private final int HEIGHT = 30;
    public FormTextField(){
        super();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
