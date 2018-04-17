package BasicComponents.Table;

import javax.swing.*;
import java.awt.*;

public class TestTable {
    public static void main(String[] args) {
        String[] columnNames = {"Hà Nội", "Hồ Chí Minh", "Nghệ An", "Thanh Hóa", "Hải Phòng", "Hà Giang", "Thái Bình", "Washington", "New York", "Tokyo", "Paris", "San Francisco", "Texas", "New Orleans"};
        Table table = new Table(columnNames, "Thành phố");
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(table.getTablePanel(), BorderLayout.NORTH);
        frame.setVisible(true);
    }
}
