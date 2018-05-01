package Template;

import BasicComponents.UIBorder;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class UserTemplateGenerator
 * Author:Ha Tuan Phong
 * User use this frame to generate a new template for data recording.
 * The template is then sent to database for multi-purposes including
 * updating template for other users.... Only users with Chief priority is able to use this feature.
 */
public class UserTemplateGenerator extends JFrame {
    //Frame properties declaration
    private final String frameTitle = "Tạo biểu mẫu";
    private final int FRAME_WIDTH = 370;
    private final int FRAME_HEIGHT = 600;
    //Frame buttons declaration
    private JButton button_save;
    private JButton button_insert;
    private List<DeleteButton> buttons_delete;
    private JButton button_reset;
    //Frame components declaration
    private final String[] templateFormatTypes = {"Ngày tháng năm", "Số", "Tự do", "Custom..."};
    private List<TemplateTextField> headers;
    private List<TemplateComboBox> comboBoxes_templateFormats;
    /**
     * Layout model:
     * Always consists of two JPanel: inner and outer panel.
     * Inner panel is set with GridLayout.
     * Outer panel is set with FlowLayout.
     * This model is used in order to create an user friendly experience.
     */
    private JPanel innerPanel_templateGenerator;
    private JPanel outerPanel_templateGenerator;
    private JPanel innerPanel_delete;
    private JPanel outerPanel_delete;

    private JToolBar toolBar_templateGenerator;
    //Used for components identification
    private int key = 0;

    /**
     * Public constructor
     * initialize main frame, panels and ArrayList data types
     */
    public UserTemplateGenerator() {
        //Initialize frame
        super();
        this.setName("Tạo biểu mẫu");
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
        //Initialize data structures
        headers = new ArrayList<TemplateTextField>();
        comboBoxes_templateFormats = new ArrayList<TemplateComboBox>();
        buttons_delete = new ArrayList<DeleteButton>();
        //Initialize panels
        innerPanel_templateGenerator = new JPanel();
        innerPanel_templateGenerator.setLayout(new GridLayout(0, 2, 20, 20));
        outerPanel_templateGenerator = new JPanel();
        outerPanel_templateGenerator.setLayout(new FlowLayout());
        outerPanel_templateGenerator.add(innerPanel_templateGenerator);
        TitledBorder titledBorder_templateGenerator = new TitledBorder(UIBorder.getInstance(), "Biểu mẫu");
        titledBorder_templateGenerator.setTitleJustification(TitledBorder.CENTER);
        outerPanel_templateGenerator.setBorder(titledBorder_templateGenerator);

        innerPanel_delete = new JPanel(new GridLayout(0, 1, 20, 20));
        outerPanel_delete = new JPanel(new FlowLayout());
        outerPanel_delete.add(innerPanel_delete);
        TitledBorder titledBorder_delete = new TitledBorder(UIBorder.getInstance(), "Xóa");
        titledBorder_delete.setTitleJustification(TitledBorder.CENTER);
        outerPanel_delete.setBorder(titledBorder_delete);
        // Set up buttons and toolbar
        setUpComponents();
        // Add components to main frame
        this.add(toolBar_templateGenerator, BorderLayout.NORTH);
        this.add(outerPanel_templateGenerator, BorderLayout.CENTER);
        this.add(outerPanel_delete, BorderLayout.EAST);
        this.pack();
    }
    /**
     * Shit
     */
    /*private void addTemplate(String header, String UserFormatType) {
        if (UserFormatType.equals(templateFormatTypes[3])) {
            return;
        }
        UserTemplateFormat format = null;
        switch(UserFormatType) {
            case templateFormatTypes[0]:
                format = new UserTemplateFormat(TemplateFormatType.DATE, header);
                break;
            case templateFormatTypes[1]:
                format = new UserTemplateFormat(TemplateFormatType.NUMBER, header);
                break;
            case templateFormatTypes[2]:
                format = new UserTemplateFormat(TemplateFormatType.NULL, header);
                break;
        }
        //userTemplateFormats.add(format);
    }*/

    /**
     * save user's template and send to database
     *
     * @throws TemplateException
     */
    private void saveTemplate() throws TemplateException {
        if (headers.isEmpty()) {
            throw new TemplateException("Biểu mẫu rỗng");
        }
        /**
         * Important note:
         * this is the demo version so the template will be written to file
         */
        try {
            PrintWriter printWriter = new PrintWriter("Template.txt", "UTF-8");
            for (int i = 0; i < headers.size(); i++) {
                printWriter.println(headers.get(i).getText() + " " + comboBoxes_templateFormats.get(i).getSelectedItem());
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        JOptionPane.showMessageDialog(null, "Biểu mẫu đã lưu");
    }

    /**
     * Initialize and set up buttons and toolbar
     */
    private void setUpComponents() {
        /**
         * Insert button
         * User click this button to add a new header to template
         */
        button_insert = new JButton("Thêm");
        button_insert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    addTemplate();
                } catch (TemplateException te) {
                    JOptionPane.showMessageDialog(null, te.getMessage());
                }
            }
        });
        /**
         * Save button
         * User click this button to save template and send to database
         */
        button_save = new JButton("Lưu");
        button_save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveTemplate();
                } catch (TemplateException te) {
                    JOptionPane.showMessageDialog(null, te.getMessage());
                }
            }
        });
        /**
         * Reset button
         * User click this button to clear all current template contents
         */
        button_reset = new JButton("Đặt lại");
        button_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeAllTemplate();
            }
        });
        // Add buttons to toolbar
        toolBar_templateGenerator = new JToolBar();
        TitledBorder titledBorder_toolbar = new TitledBorder(UIBorder.getInstance(), "Tác vụ");
        titledBorder_toolbar.setTitleJustification(TitledBorder.CENTER);
        toolBar_templateGenerator.setBorder(titledBorder_toolbar);

        toolBar_templateGenerator.add(button_insert);
        toolBar_templateGenerator.add(button_save);
        toolBar_templateGenerator.add(button_reset);
    }

    /**
     * Add new header to current template
     *
     * @throws TemplateException
     */
    private void addTemplate() throws TemplateException {
        /**
         * After user clicks insert button a JOptionPane prompts.
         * User enters header and 3 new components created:
         * TemplateTextField: user's new header is set in the textfield so that user can modify
         * TemplateComboBox: user's choose format type for data recording and viewing
         * DelateButton: user clicjs this button to delete the created header
         * All 3 components inherit from Swing components(See below), with additional variable 'key'
         * for delete purpose(see declaration above).
         */
        TemplateTextField textField_header = new TemplateTextField();
        textField_header.setKey(key);
        // Prompts "insert header" textfield
        JOptionPane.showMessageDialog(null, textField_header, "Tên cột mới", JOptionPane.INFORMATION_MESSAGE);
        if (textField_header.getText().equals("")) {
            throw new TemplateException("Chuỗi ký tự rỗng! Cột không được khởi tạo!");
        }
        // Create combo box for format choices
        TemplateComboBox comboBox_templateFormats = new TemplateComboBox(templateFormatTypes);
        comboBox_templateFormats.setKey(key);
        comboBox_templateFormats.addActionListener(new ActionListener() {
            @Override
            /**
             * If user choose to add a new custom format:
             * add user's custom format to combo box and set it as selected item
             */
            public void actionPerformed(ActionEvent e) {
                String s = (String) comboBox_templateFormats.getSelectedItem();
                if (s.equals(templateFormatTypes[3])) {
                    JTextField textField_format = new JTextField();
                    JOptionPane.showMessageDialog(null, textField_format, "Định dạng mới", JOptionPane.INFORMATION_MESSAGE);
                    comboBox_templateFormats.addItem(textField_format.getText());
                    comboBox_templateFormats.setSelectedItem(textField_format.getText());
                }
            }
        });
        // Add new components to data structures for delete and save purposes
        headers.add(textField_header);
        comboBoxes_templateFormats.add(comboBox_templateFormats);
        // Add new delete button
        DeleteButton button_delete = new DeleteButton("Xóa");
        button_delete.setKey(key);
        //System.out.println(button_delete.getKey());
        button_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeTemplate(button_delete.getKey());
            }
        });
        buttons_delete.add(button_delete);

        // Add new components to panels then update panels
        innerPanel_templateGenerator.add(textField_header);
        innerPanel_templateGenerator.add(comboBox_templateFormats);
        innerPanel_templateGenerator.updateUI();
        innerPanel_delete.add(button_delete);
        innerPanel_delete.updateUI();

        key++;
    }

    /**
     * Remove specific components with variabel key as identifier.
     *
     * @param key identifier
     */
    private void removeTemplate(int key) {
        // Search for components that has the same key and remove them from panels
        for (int i = 0; i < buttons_delete.size(); i++) {
            if (buttons_delete.get(i).getKey() == key) {
                innerPanel_templateGenerator.remove(headers.remove(i));
                innerPanel_templateGenerator.remove(comboBoxes_templateFormats.remove(i));
                innerPanel_delete.remove(buttons_delete.remove(i));
                break;
            }
        }
        // Update panels
        innerPanel_delete.revalidate();
        innerPanel_templateGenerator.revalidate();
        innerPanel_delete.updateUI();
        innerPanel_templateGenerator.updateUI();
    }

    /**
     * Clear user's current template
     */
    private void removeAllTemplate() {
        innerPanel_templateGenerator.removeAll();
        innerPanel_templateGenerator.revalidate();
        innerPanel_templateGenerator.updateUI();
        innerPanel_delete.removeAll();
        innerPanel_delete.revalidate();
        innerPanel_delete.updateUI();
    }
}

/**
 * JButton with additional variable 'key'
 * Author: Ha Tuan Phong
 */
class DeleteButton extends JButton {
    private int key;

    public DeleteButton(String name) {
        super(name);
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}

/**
 * JTextField with additional variable 'key'
 * Author: Ha Tuan Phong
 */
class TemplateTextField extends JTextField {
    private int key;

    /**
     * Public constructor
     */
    public TemplateTextField() {
        super();
    }

    /**
     * key setter
     *
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * key getter
     *
     * @return key
     */
    public int getKey() {
        return key;
    }
}

/**
 * JComboBox with additional variable 'key'
 * Author: Ha Tuan Phong
 */
class TemplateComboBox extends JComboBox {
    private int key;

    /**
     * Public constructor
     */
    public TemplateComboBox(String[] strings) {
        super(strings);
    }

    /**
     * key setter
     *
     * @param key the key to set
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * key getter
     *
     * @return key
     */
    public int getKey() {
        return key;
    }
}