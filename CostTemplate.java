package Template;

import Template.Template;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
        try {
            File file = new File("costcontents.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String str;
            while (in.ready()) {
                str = in.readLine();
                if (str.isEmpty()) continue;
                contents.add(new Content(str));
            }
            in.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (Content c : contents) {
            System.out.println(c.getContent());
            if (!c.getSubContents().isEmpty()) {
                for (Content sc : c.getSubContents()) {
                    System.out.println(sc.getContent());
                }
            }
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
