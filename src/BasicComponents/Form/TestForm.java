package BasicComponents.Form;

import javax.swing.*;
import java.awt.*;

public class TestForm {
    private static String[] labels = {"Hà Nội", "Hồ Chí Minh", "Hải Phòng", "Nghệ An", "Thanh Hóa"};
    private static Form form = new Form(labels);

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(new Dimension(700, 800));
        frame.add(form.getScrollPane());
        frame.setVisible(true);
    }
}
