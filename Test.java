package Template;

import javax.swing.*;

public class Test {
    public static void main(String[] args) {
        /*try {
            File file = new File("costcontents.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
            String str;
            while((str = in.readLine()) != null) {
                System.out.println(str);
            }
            in.close();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }*/
        JFrame frame = new JFrame();
        CostTemplate a = new CostTemplate("Chi phí năm 2017");
        a.setContents();
        Template b = new CostTemplate("Chi phí năm 2018");
        TemplatesAccessPanel templatesAccessPanel = new TemplatesAccessPanel();
        templatesAccessPanel.addAccess(a);
        templatesAccessPanel.addAccess(b);
        //frame.add(templatesAccessPanel);
        CostTemplateUI costTemplateUI = new CostTemplateUI();
        costTemplateUI.setCostTemplate(a);
        frame.add(costTemplateUI);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
