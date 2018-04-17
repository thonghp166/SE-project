package BasicComponents.Form;

import javax.swing.*;
import java.awt.*;

public class FormLabel extends JLabel{
    private final int WIDTH = 150;
    private final int HEIGHT = 30;
    public FormLabel(String label, int align){
        super(label, align);
        //this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
}
