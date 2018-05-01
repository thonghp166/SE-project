package BasicComponents;

import template.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Main UI
 * @author Ha Tuan Phong
 */
public class UI extends JFrame{
    /* templates' access panel */
    private TemplatesAccessPanel templatesAccessPanel = new TemplatesAccessPanel();
    /* template panel */
    private TemplateUI templateUI = new CostTemplateUI();
    /* templates list */
    private ArrayList<Template> templates = new ArrayList<Template>();
    /**
     * Public constructor
     */
    public UI() {
        super();
        this.setName("Ban hành chính Viettel");
        this.setMinimumSize(new Dimension(600, 400));
        this.setLayout(new BorderLayout());
        this.setJMenuBar(MenuBar.getInstance());
        template.CostTemplate a = new template.CostTemplate("Chi phí năm 2017");
        a.setContents();
        addTemplate(a);
        addTemplate(new CostTemplate("Chi phí năm 2018"));
        addTemplate(new CostTemplate("Chi phí năm 2019"));
        setup();
        this.setVisible(true);
    }

    /**
     * Add template
     * @param template the template to add
     */
    public void addTemplate(Template template) {
        templates.add(template);
        templatesAccessPanel.addAccess(template);
        templatesAccessPanel.updateUI();
    }

    /**
     * Set up components
     */
    private void setup() {
        if(!templates.isEmpty()) {
            CostTemplateUI cast = (CostTemplateUI) templateUI;
            cast.setCostTemplate((CostTemplate)templates.get(0));
        }

        this.add(templatesAccessPanel, BorderLayout.WEST);
        this.add(templateUI, BorderLayout.CENTER);
        this.add(Toolbar.getInstance(), BorderLayout.NORTH);
    }
    /**
     * test ui
     * @param args
     */
    public static void main(String[] args) {
        UI ui = new UI();
        ui.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
