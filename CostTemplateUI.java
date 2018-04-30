package Template;

import javax.swing.*;
import java.awt.*;

public class CostTemplateUI extends TemplateUI {
    private JPanel panel_inner;
    private CostTemplate costTemplate;
    private JLabel[] labels_content;
    private JTextField[] textFields_estimate;
    private JTextField[] textFields_actual;

    public CostTemplateUI() {
        super();
        this.setLayout(new FlowLayout());
        panel_inner = new JPanel();
        panel_inner.setLayout(new GridLayout(0, 3, 3, 3));
        this.add(panel_inner);
    }

    public void setCostTemplate(CostTemplate costTemplate) {
        this.costTemplate = costTemplate;
        setup();
    }

    private void setup() {
        int size = costTemplate.getContents().size();
        if (size == 0) return;
        labels_content = new JLabel[size];
        textFields_estimate = new JTextField[size];
        textFields_actual = new JTextField[size];
        for (int i = 0; i < size; i++) {
            labels_content[i].setText(costTemplate.getContents().get(i).getContent());
            panel_inner.add(labels_content[i]);
            panel_inner.add(textFields_estimate[i]);
            panel_inner.add(textFields_actual[i]);
        }
    }
}
