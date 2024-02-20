package view.components.panels.chartPanel;

import java.awt.*;
import java.text.DecimalFormat;
import lombok.Getter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import sevices.JDBCStatisticsService;


@Getter
public class GeneralChart extends ChartPanel {

    private final GeneralDataModel dataSet;
    private final JFreeChart chart;

    
    public GeneralChart(JDBCStatisticsService service) {
        super(null);
            initComponents();
            dataSet = new GeneralDataModel(service);
            chart = initChart();
            setChart(chart);
    }
    
    public final JFreeChart initChart(){
        JFreeChart cChart = ChartFactory.createBarChart(
                    "Candidates",
                    null,
                    "days",
                dataSet,
                    PlotOrientation.VERTICAL,
                    false,
                    true,
                    false);
        
        BarRenderer renderer = new BarRenderer(){
            @Override
            public Paint getItemPaint(int row, int column) {
                Color color = Color.WHITE;
                switch (column) {
                    case 0 -> color = new Color(181,152,187);
                    case 1 -> color = new Color(169,137,174);
                    case 2 -> color = new Color(161,126,168);
                    case 3 -> color = new Color(153,115,160);
                    case 4 -> color = new Color(147,110,154);
                    case 5 -> color = new Color(144,104,147);
                    case 6 -> color = new Color(136,110,154);
                    case 7 -> color = new Color(129,101,140);
                }
                return color;
            }
        };
        cChart.setBackgroundPaint(Color.decode("#faf2f9"));
        CategoryPlot plot = cChart.getCategoryPlot();
        CategoryAxis axisX = plot.getDomainAxis();
        ValueAxis axisY = plot.getRangeAxis();

        axisX.setAxisLinePaint(Color.BLACK);
        axisY.setAxisLinePaint(Color.BLACK);

        axisX.setTickLabelPaint(Color.BLACK);
        axisY.setTickLabelPaint(Color.BLACK);

        plot.setRenderer(renderer);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.decode("#faf2f9"));
        renderer.setItemMargin(-2.20D);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("#.##")));
        
        cChart.getCategoryPlot().setOutlineVisible(false);
        cChart.getCategoryPlot().setAxisOffset(new RectangleInsets(0d,0d,0d,0d));
        cChart.getCategoryPlot().getDomainAxis().setLowerMargin(0.01d);
        cChart.getCategoryPlot().getDomainAxis().setUpperMargin(0.01d);
        
        return cChart;
    }
    
    private void initComponents() {

        setLayout(new BorderLayout());
    }
}