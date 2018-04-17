package BasicComponents;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public final class UIBorder {
    private static Border instance = BorderFactory.createLineBorder(Color.BLACK);
    private UIBorder() {}
    public static Border getInstance() {
        return instance;
    }
}
