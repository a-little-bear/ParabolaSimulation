package parabolaSimulation.draw;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DynamicParabola {

    public XYSeries series = new XYSeries("Parabola");
    public JFreeChart chart = null;
    public ChartPanel panel;

    public static int sleep = 25;
    private final static int START = -1000;
    private final static int END = 1000;

    private Color backColor = Color.YELLOW;

    public JPanel getChart() {
        chart = ChartFactory.createXYLineChart(null, null, null, createDataset(), PlotOrientation.VERTICAL, false, true,
                false);
        chart.setBorderVisible(false);
        chart.setBackgroundPaint(backColor);

        XYPlot xyplot = (XYPlot) chart.getPlot();
        xyplot.setBackgroundAlpha(0.0f);
        NumberAxis x = (NumberAxis) xyplot.getDomainAxis();
        x.setLowerBound(START);
        x.setUpperBound(END);

        panel = new ChartPanel(chart, true);
        panel.setVisible(true);
        panel.updateUI();
        return panel;
    }

    private XYDataset createDataset() {
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        return collection;
    }

    public void dynamicRun(double a, double b, double c) {
        series.clear();

        int x = START;

        while (true) {

            double y = a * x * x + b * x + c;
            series.add(x, y);

            x += 50;
            if (x > END) {
                sleep(2000);
                return;
            } else {
                sleep(sleep);
            }


        }
    }

    public void sleep(int millsec) {
        try {
            Thread.currentThread();
            Thread.sleep(millsec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public DynamicParabola(String name, Color backColor, double a, double b, double c) {
        JFrame frame = new JFrame(name);
        this.backColor = backColor;
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(700, 500);
        frame.getContentPane().add(getChart(), BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        dynamicRun(a, b, c);

        frame.dispose();
    }

    public static void main(String[] args) {
        new DynamicParabola("Parabola 1", Color.YELLOW, 1, 1, 1);
    }
}
