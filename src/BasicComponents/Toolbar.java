package BasicComponents;

import javax.swing.*;

/**
 * Toolbar for UI
 *
 * @author Ha Tuan Phong
 */
public class Toolbar extends JToolBar {
    /* toolbar instance */
    private static Toolbar instance = new Toolbar();
    /* buttons */
    private JButton button_save;
    private JButton button_clear;

    /**
     * Private constructor
     */
    private Toolbar() {
        super();
        setup();
    }

    /**
     * get instance
     *
     * @return instance
     */
    public static Toolbar getInstance() {
        return instance;
    }

    /**
     * set up components
     */
    private void setup() {
        button_save = new JButton(new ImageIcon("imageIcon/saveIcon.png"));
        button_save.setText("Lưu");
        button_clear = new JButton(new ImageIcon("imageIcon/clearIcon.png"));
        button_clear.setText("Xóa");
        this.add(button_save);
        this.add(button_clear);
    }
}
