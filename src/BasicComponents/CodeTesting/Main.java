package BasicComponents.CodeTesting;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FormattedDateTextField formattedDateTextField = new FormattedDateTextField();
        JOptionPane.showMessageDialog(null, formattedDateTextField);
        System.out.println(formattedDateTextField.getText());
    }
}
