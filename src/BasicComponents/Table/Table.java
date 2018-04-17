package BasicComponents.Table;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Table {
    private JPanel tablePanel;
    private JTable table;
    private final int DEFAULT_ROWS = 200;
    private int currentEmptyRow = 0;
    private String[][] data = null;
    private String[] columnHeaders;
    private JScrollPane scrollPane;

    public Table(String[] columnHeaders, String title) {
        data = new String[DEFAULT_ROWS][columnHeaders.length];

        table = new JTable(data, columnHeaders);
        table.setRowHeight(25);
        table.setEnabled(false);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFillsViewportHeight(true);
        for (int i = 0; i < columnHeaders.length; i++) {
            table.getColumn(columnHeaders[i]).setMinWidth(optimizedColumnWidth(columnHeaders[i]));
        }

        scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        TitledBorder titledBorder = new TitledBorder(border, title);
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        tablePanel = new JPanel();
        tablePanel.setLayout(new BorderLayout());
        tablePanel.setBorder(titledBorder);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
    }

    private boolean validInputs(String[] formInputs) {
        for (int i = 0; i < formInputs.length; i++) {
            if (formInputs[i].equals("")) return false;
        }
        return true;
    }
    public void addNewRecord(String[] formInputs) {
        if (!validInputs(formInputs)) {
            JOptionPane.showMessageDialog(null, "Đầu vào không hợp lệ");
            return;
        }
        for (int i = 0; i < formInputs.length; i++) {
            data[currentEmptyRow][i] = formInputs[i];
        }
        currentEmptyRow++;
        table.repaint();
        JOptionPane.showMessageDialog(null, "Lưu thành công");
    }

    public JTable getTable() {
        return table;
    }
    public JPanel getTablePanel() {
        return tablePanel;
    }

    private int optimizedColumnWidth(String header) {
        return header.length() * 5 + 70;
    }
}
