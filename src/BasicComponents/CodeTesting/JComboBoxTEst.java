package BasicComponents.CodeTesting;

import javax.swing.*;
import java.awt.*;

public class JComboBoxTEst {
    public static void main(String[] args) {
        String[] types = {"Ngày tháng năm", "Số", "Rỗng", "Tự do..."};
        JComboBox<String> comboBox = new JComboBox(types);
        JFrame frame = new JFrame("Test");
        frame.setLayout(new BorderLayout());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.add(new JLabel("Test"), BorderLayout.NORTH);
        JPanel panel = new JPanel();
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new FlowLayout());
        panel.setLayout(new GridLayout(0,2,3,3));
        panel.add(new JTextField());
        panel.add(comboBox);
        panel.add(new JTextField());
        panel.add(new JComboBox<String>(types));
        outerPanel.add(panel);
        frame.add(outerPanel, BorderLayout.WEST);
        frame.setVisible(true);
        System.out.println(comboBox.getSelectedItem());

    }
}
