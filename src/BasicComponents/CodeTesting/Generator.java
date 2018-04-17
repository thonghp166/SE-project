package BasicComponents.CodeTesting;

import com.sun.scenario.effect.impl.sw.java.JSWBlend_COLOR_BURNPeer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Generator extends JFrame {
    private JButton button_add;
    private JButton button_save;
    private List<JTextField> textFieldList = new ArrayList<JTextField>();
    private List<String> headers = new ArrayList<String>();
    private JPanel panel_templates;
    final private String[] formatTypes = {"Ngày tháng năm", "Số", "Rỗng", "Tự do..."};
    private List<JComboBox> comboBoxes = new ArrayList<JComboBox>();
    private int numberOfTemplates = 0;

    public Generator() {
        super();
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(400, 800));
        panel_templates = new JPanel();
        panel_templates.setLayout( new GridLayout(0,2,3,3));
        setup();
        this.add(panel_templates, BorderLayout.CENTER);
    }

    private void setup() {
        button_add = new JButton("add");
        button_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField textField = new JTextField();
                JOptionPane.showMessageDialog(null, textField);
                headers.add(textField.getText());
                textFieldList.add(textField);
                JComboBox<String> comboBox = new JComboBox(formatTypes);
                comboBoxes.add(comboBox);
                panel_templates.add(textField);
                panel_templates.add(comboBox);
                panel_templates.updateUI();
                numberOfTemplates++;
                System.out.println(headers.size() + " " + comboBoxes.size());
                update();
            }
        });

        button_save = new JButton("Save");
        this.add(button_add, BorderLayout.NORTH);
    }

    private void update() {
        repaint();
    }

}
