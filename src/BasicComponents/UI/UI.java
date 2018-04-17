package BasicComponents.UI;

import BasicComponents.Form.Form;
import BasicComponents.SearchBox.SearchBox;
import BasicComponents.Table.Table;
import Template.UserTemplateGenerator;
import javafx.scene.control.Tab;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteOrder;

public class UI extends JFrame{
    private JButton button_Save;
    private JButton button_Clear;
    private JButton button_Edit;
    private JButton button_Excel;
    private JButton button_Template;
    private Form form;
    private Table table;
    private SearchBox searchBox;

    private JPanel toolBarPanel;
    private JToolBar toolBar;
    private JMenuBar menuBar;

    public UI(String name) {
        super(name);
        this.setLayout(new BorderLayout());
        this.setExtendedState(MAXIMIZED_BOTH);
    }

    public void addComponents() {
        setButtons();
        this.add(toolBarPanel, BorderLayout.NORTH);
        this.add(searchBox, BorderLayout.WEST);
        this.add(form, BorderLayout.EAST);
        this.add(table.getTablePanel(), BorderLayout.CENTER);
    }
    public void setTable(String[] columnHeaders, String title) {
        table = new Table(columnHeaders, title);
    }
    public void setForm(String[] columnHeaders) {
        form = new Form(columnHeaders);
    }

    public void setSearchBox() {
        searchBox = new SearchBox();
    }

    private String[] getUserInputs() {
        return form.getInputs();
    }

    private void saveRecord() {
        table.addNewRecord(getUserInputs());
        form.clearInputs();
    }

    private void clearUserInputs() {
        form.clearInputs();
    }

    private void exportExcel() {

    }

    private void editRecord() {

    }
    private void setButtons() {
        button_Save = new JButton("Save");
        button_Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveRecord();
            }
        });

        button_Clear = new JButton("Clear");
        button_Clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearUserInputs();
            }
        });

        button_Edit = new JButton("Edit");
        button_Edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editRecord();
            }
        });

        button_Excel = new JButton("Excel");
        button_Excel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exportExcel();
            }
        });

        button_Template = new JButton("Tạo biểu mẫu");
        button_Template.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserTemplateGenerator userTemplateGenerator = new UserTemplateGenerator();
                userTemplateGenerator.setVisible(true);
            }
        });
        toolBar = new JToolBar();
        toolBar.add(button_Save);
        toolBar.add(button_Clear);
        toolBar.add(button_Edit);
        toolBar.add(button_Excel);
        toolBar.add(button_Template);
        Border border = BorderFactory.createLineBorder(Color.GRAY);
        TitledBorder titledBorder = new TitledBorder(border, "Tác vụ");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        toolBarPanel = new JPanel();
        toolBarPanel.setLayout(new FlowLayout());
        toolBarPanel.setBorder(titledBorder);
        toolBarPanel.add(toolBar);
        toolBar.setFloatable(false);
    }
}
