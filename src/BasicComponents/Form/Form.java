package BasicComponents.Form;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class Form extends JPanel{
    private final int gridLayout_rows = 0;
    private final int gridLayout_cols = 2;
    private final int gridLayout_hgap = 20;
    private final int gridLayout_vgap = 10;
    private JPanel pn_GridLayout;
    private FormLabel[] formLabels;
    private FormTextField[] formTextFields;
    private JScrollPane scrollPane;

    public Form(String[] columnHeaders) {
        super();
        this.setLayout(new FlowLayout());
        pn_GridLayout = new JPanel();
        pn_GridLayout.setLayout(new GridLayout(gridLayout_rows, gridLayout_cols, gridLayout_hgap, gridLayout_vgap));
        int num = columnHeaders.length;
        formLabels = new FormLabel[num];
        formTextFields = new FormTextField[num];
        for(int i = 0; i < num; i++)
        {
            formLabels[i] = new FormLabel(columnHeaders[i], SwingConstants.RIGHT);
            formTextFields[i] = new FormTextField();
            formTextFields[i].setText("");
            pn_GridLayout.add(formLabels[i]);
            pn_GridLayout.add(formTextFields[i]);
        }
        this.add(pn_GridLayout);

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        TitledBorder titledBorder = new TitledBorder(border, "Biểu mẫu");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        this.setBorder(titledBorder);
        scrollPane = new JScrollPane(this, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    }

    public void clearInputs() {
        for (int i = 0; i < formLabels.length; i++) {
            formTextFields[i].setText("");
            formTextFields[i].updateUI();
        }
    }
    public String[] getInputs()
    {
        String[] inputs = new String[formTextFields.length];
        for(int i = 0; i < formTextFields.length; i++)
        {
            inputs[i] = formTextFields[i].getText();
            System.out.println(i+ ": '" + inputs[i] + "'");
        }
        return inputs;
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
    }
}
