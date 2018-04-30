package Template;

import java.util.ArrayList;

public abstract class Template {
    /* Get template's name */
    public abstract String getName();
    /* Get template's contents */
    public abstract ArrayList<Content> getContents();
}