package view.components.panels.chartPanel;

import controller.Main;
import java.awt.*;
import java.text.DecimalFormat;

import lombok.Getter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.ui.RectangleInsets;
import sevices.JDBCStatisticsService;
import util.enums.Stage;

@Getter
public class DetailsChart extends ChartPanel {
    
    private final DetailDataModel dataSet;
    private final JFreeChart chart;
    
    
    public DetailsChart(JDBCStatisticsService service, StatisticsContext context) {
        super(null);
        this.dataSet = new DetailDataModel(service, context);
        this.chart = initChart();
        setChart(chart);
    }
    
    public final JFreeChart initChart(){
        JFreeChart cChart = ChartFactory.createBarChart(
                    "",
                    null,
                    "number of candidates",
                dataSet,
                    PlotOrientation.VERTICAL,
                    false,
                    true,
                    false);
        
        BarRenderer renderer = new BarRenderer();

        cChart.setBackgroundPaint(Color.decode("#faf2f9"));
        CategoryPlot plot = cChart.getCategoryPlot();
        CategoryAxis axisX = cChart.getCategoryPlot().getDomainAxis();
        
        NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
        numberAxis.setLabelFont(Main.MY_FONT);
        ValueAxis axisY = plot.getRangeAxis();

        plot.setRenderer(renderer);
        plot.setRangeGridlinePaint(Color.BLACK);
        plot.setBackgroundPaint(Color.decode("#faf2f9"));
        renderer.setItemMargin(-2.20D);
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", new DecimalFormat("#.##")));
        
        plot.setOutlineVisible(false);
        axisX.setAxisLinePaint(Color.BLACK);
        axisX.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        axisX.setTickLabelPaint(Color.BLACK);
        axisX.setLabelFont(Main.MY_FONT);
        axisX.setUpperMargin(0.01d);
        axisX.setLowerMargin(0.01d);

        axisY.setAxisLinePaint(Color.BLACK);
        axisY.setTickLabelPaint(Color.BLACK);
        axisY.setLabelFont(Main.MY_FONT);

        plot.setAxisOffset(new RectangleInsets(0d,0d,0d,0d));
        
        return cChart;
    }
    
    public void updateCandidateColor(Stage stage){
        BarRenderer renderer = (BarRenderer)chart.getCategoryPlot().getRenderer();
            switch (stage) {
                case PHONE_SCREENING -> renderer.setSeriesPaint(0, new Color(181, 152, 187));
                case HR_INTERVIEW -> renderer.setSeriesPaint(0, new Color(169, 137, 174));
                case SUBMITTED_TO_HM -> renderer.setSeriesPaint(0, new Color(161, 126, 168));
                case HM_INTERVIEW -> renderer.setSeriesPaint(0, new Color(153, 115, 160));
                case TEST -> renderer.setSeriesPaint(0, new Color(147, 110, 154));
                case OFFER_LETTER -> renderer.setSeriesPaint(0, new Color(144, 104, 147));
                case SELECTION_CLOSED -> renderer.setSeriesPaint(0, new Color(136, 110, 154));
                case NONE -> renderer.setSeriesPaint(0, new Color(129, 101, 140));
        }
    }

    public void updatePositionColor() {
        BarRenderer renderer = (BarRenderer)chart.getCategoryPlot().getRenderer();
        renderer.setSeriesPaint(0, Color.decode("#946aa6"));
    }
}