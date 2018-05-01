package BasicComponents.Chart;

import com.util.TypeEnum;
import org.jfree.chart.*;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;



public class Chart extends JFrame {
    public static final int BAR_CHART = 0;
    public static final int PIE_CHART = 1;



    private JToolBar toolBar;
    private JButton saveAsPNGBtn;
    private JButton zoomOutBtn;
    private JButton zoomInBtn;
    private JButton rearrangeBtn;
    private JButton printBtn;
    private JCheckBox viewLabelItem;
    private JFreeChart chart;


    private ChartPanel chartPanel;


    public Chart(JTable data, ArrayList<TypeEnum> inputType, int type) {
        data.setModel(new DefaultTableModel());
        DefaultTableModel model = (DefaultTableModel) data.getModel();
        chartPanel = new ChartPanel(initChart(data.getName(),model, inputType, type));
        initComponents();
    }

    private JFreeChart initChart(String name, DefaultTableModel model, ArrayList<TypeEnum> inputType, int type) {
        if (type == BAR_CHART) {
            CategoryDataset dataset = createDataset(model, inputType);
            chart = ChartFactory.createBarChart3D("Biểu đồ", model.getColumnName(1), model.getColumnName(2),
                    dataset, PlotOrientation.VERTICAL, true, true, true);
            this.setTitle(name);
            chart.getTitle().setFont(new Font("Arial", Font.BOLD, 24));
            chart.getTitle().setPaint(Color.RED);
            CategoryPlot plot = chart.getCategoryPlot();

            LineAndShapeRenderer renderer = new LineAndShapeRenderer();
            renderer.setBaseLinesVisible(true);
            renderer.setSeriesPaint(0, Color.BLUE);

            plot.setRenderer(renderer);
            plot.setRangeGridlinesVisible(true);
            plot.setRangeGridlinePaint(Color.BLACK);
            plot.setDomainGridlinesVisible(true);
            plot.setDomainGridlinePaint(Color.BLACK);

            NumberFormat format = NumberFormat.getNumberInstance();
            format.setMaximumFractionDigits(1);
            //CategoryItemLabelGenerator generator =
                    //new StandardCategoryItemLabelGenerator("{2}", format, format);
            renderer.setBaseItemLabelsVisible(true);
            //renderer.setBaseItemLabelGenerator(generator);
            renderer.setBaseItemLabelPaint(Color.RED);

        } else {
            DefaultPieDataset dataset = createPieDataset(model, inputType);
            chart = ChartFactory.createPieChart3D(name,dataset,true,true,false);
            final PiePlot3D plot = (PiePlot3D) chart.getPlot();
            plot.setStartAngle( 270 );
            plot.setForegroundAlpha( 0.60f );
            plot.setInteriorGap( 0.02 );
        }


        return chart;
    }

    private DefaultPieDataset createPieDataset(DefaultTableModel model, ArrayList<TypeEnum> inputType) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        HashMap<String, Double> map = new HashMap<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            String key = String.valueOf(model.getValueAt(i, 0));
            double data = Double.parseDouble(String.valueOf(model.getValueAt(i, 1)));
            if (map.containsKey(key)) {
                map.replace(key, map.get(key) + data);
            } else {
                map.put(key, data);
            }
        }

        for (String key : map.keySet()) {
            dataset.setValue(key, map.get(key));
        }
        return dataset;
    }

    private CategoryDataset createDataset(DefaultTableModel data, ArrayList<TypeEnum> inputType) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < data.getRowCount(); i++) {

            if (inputType.get(i) == TypeEnum.DATE.NUMBER) {
                Double d = Double.valueOf(String.valueOf(data.getValueAt(i, 1)));
                dataset.setValue(d, String.valueOf(data.getValueAt(i, 0)),
                        String.valueOf(data.getValueAt(i, 2)));
            } else {
//            data.getModel()
            }


        }
        return dataset;
    }

    private void initComponents() {
        // Tạo các chức năng cho toolBar
        toolBar = new JToolBar();
        saveAsPNGBtn = new JButton();
        zoomInBtn = new JButton();
        zoomOutBtn = new JButton();
        rearrangeBtn = new JButton();
        printBtn = new JButton();
        viewLabelItem = new JCheckBox("Hiển thị giá trị");

        // Cài đặt tool bar
        toolBar.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        toolBar.setRollover(true);
        toolBar.setPreferredSize(new Dimension(400, 40));
        toolBar.setFloatable(false);
        toolBar.setRequestFocusEnabled(false);

        ImageIcon save = new ImageIcon("/BasicComponents/imageIcon/saveIcon.png");
        saveAsPNGBtn.setIcon(save);
        saveAsPNGBtn.setText("Lưu Ảnh");
        saveAsPNGBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        saveAsPNGBtn.setFocusable(true);
        saveAsPNGBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        saveAsPNGBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        saveAsPNGBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    savePngPerform(e);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        saveAsPNGBtn.setMnemonic(KeyEvent.VK_S);
        toolBar.add(saveAsPNGBtn);
        toolBar.addSeparator();

        ImageIcon zOut = new ImageIcon("/BasicComponents/imageIcon/zoomOutIcon.png");
        zoomOutBtn.setIcon(zOut);
        zoomOutBtn.setText("Thu nhỏ");
        zoomOutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomOutBtn.setFocusable(true);
        zoomOutBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        zoomOutBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        zoomOutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomOutPerform(e);
            }
        });
        zoomOutBtn.setMnemonic(KeyEvent.VK_SUBTRACT);
        toolBar.add(zoomOutBtn);
        toolBar.addSeparator();

        ImageIcon zIn = new ImageIcon("/BasicComponents/imageIcon/zoomInIcon.png");
        zoomInBtn.setIcon(zIn);
        zoomInBtn.setText("Phóng to");
        zoomInBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        zoomInBtn.setFocusable(true);
        zoomInBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        zoomInBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        zoomInBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zoomInPerform(e);
            }
        });
        zoomInBtn.setMnemonic(KeyEvent.VK_PLUS);
        toolBar.add(zoomInBtn);
        toolBar.addSeparator();

        ImageIcon arr = new ImageIcon("/imageIcon/arrangeIcon.png");
        rearrangeBtn.setIcon(arr);
        rearrangeBtn.setText("Xếp lại");
        rearrangeBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        rearrangeBtn.setFocusable(true);
        rearrangeBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        rearrangeBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        rearrangeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rearrangePerform(e);
            }
        });
        rearrangeBtn.setMnemonic(KeyEvent.VK_R);
        toolBar.add(rearrangeBtn);
        toolBar.addSeparator();

        ImageIcon print = new ImageIcon("/BasicComponents/imageIcon/printIcon.png");
        printBtn.setIcon(print);
        printBtn.setText("In ra");
        printBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        printBtn.setFocusable(true);
        printBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        printBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        printBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printPerform(e);
            }
        });
        printBtn.setMnemonic(KeyEvent.VK_P);
        toolBar.add(printBtn);
        toolBar.addSeparator();

        toolBar.add(viewLabelItem);
        toolBar.setVisible(true);
        this.setLayout(new BorderLayout());
        this.add(toolBar,BorderLayout.NORTH);

        //Tạo chart panel
        this.add(chartPanel);


    }

    private void printPerform(ActionEvent e) {
        chartPanel.createChartPrintJob();
    }

    private void rearrangePerform(ActionEvent e) {
        chartPanel.restoreAutoRangeBounds();
    }

    private void zoomInPerform(ActionEvent e) {
        chartPanel.zoomInBoth(-1.0d, -1.0d);
    }

    private void zoomOutPerform(ActionEvent e) {
        chartPanel.zoomOutBoth(-1.0d, -1.0d);
    }

    private void savePngPerform(ActionEvent e) throws IOException {
        chartPanel.doSaveAs();
    }

    public ChartPanel getChartPanel() {
        return this.chartPanel;
    }

}
