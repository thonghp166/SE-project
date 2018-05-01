package template;

import java.util.ArrayList;

/**
 * 'Biểu mẫu chi phí'
 * Always consists of 2 columns 'estimate'('Dự kiến') and 'actual'('Thực tế')
 *
 * @author Ha Tuan Phong
 */
public class CostTemplate extends Template {
    /* template's name */
    private String name = "Chi phí";
    /* template's input columns */
    private final String[] columns = {"Dự kiến", "Thực tế"};
    /* template's contents */
    private ArrayList<Content> contents;

    /**
     * Public constructor
     */
    public CostTemplate() {
        contents = new ArrayList<Content>();
    }

    /**
     * Public constructor
     *
     * @param name the name to set
     */
    public CostTemplate(String name) {
        this.name = name;
        contents = new ArrayList<Content>();
    }

    /**
     * Set template's name
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get template's name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Import template's contents
     */
    /* Testing */
    public void setContents() {
        for (int i = 0; i < DemoContent.contents.length; i++) {
            contents.add(new Content(DemoContent.contents[i]));
            System.out.println(contents.get(i).getContent());
        }
    }


    /**
     * Get template's contents
     *
     * @return contents
     */
    @Override
    public ArrayList<Content> getContents() {
        return contents;
    }

    /**
     * Get columns
     *
     * @return columns
     */
    public String[] getColumns() {
        return columns;
    }
}

