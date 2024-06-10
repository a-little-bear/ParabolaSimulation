package parabolaSimulation.draw;

import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.Plot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class DrawParabola {

    public ChartPanel panel;
    public XYSeriesCollection dataset = new XYSeriesCollection();
    public XYSeries series;
    public JFreeChart chart = null;
    public Color backColor = Color.YELLOW;
    public int count;

    public double a, b, c;

    public DrawParabola() {
        drawParabola();
    }

    public DrawParabola(Color backColor) {
        this.backColor = backColor;
        drawParabola();
    }

    public DrawParabola(double a, double b, double c) {
        draw(a, b, c);
    }

    public DrawParabola(double a, double b, double c, Color backColor) {
        this.backColor = backColor;
        draw(a, b, c);
    }

    /*
     * public void drawParabola(double a, double b, double c, Color backColor) {
     * this.backColor = backColor; drawParabola(a,b,c); }
     */

    public void drawParabola() {

        if (count != 0) {
            XYSeries series = new XYSeries(0);

            boolean pass = false;
            gotoWhile:
            while (pass == false) {
                try {
                    series = new XYSeries("Parabola " + (count++));
                    pass = true;
                } catch (Exception e) {
                    //e.printStackTrace();
                    continue gotoWhile;
                }
            }

            for (double x = -1000; x < 1000; x += 50) {
                double y = a * x * x + b * x + c;
                series.add(x, y);
            }

            dataset.addSeries(series);

            Runnable dynamicParabola = new parabolaSimulation.draw.RunnableDynamicParabola("Parabola " + (count - 1), backColor, a, b, c);
            Thread thread = new Thread(dynamicParabola);
            thread.setName("Parabola " + (count - 1));
            thread.start();
        } else {
            count++;
        }

        /*
         * chart = ChartFactory.createXYLineChart( "y = ax^2 + bx + c", "x", "y",
         * dataset, PlotOrientation.VERTICAL, false, false, false);
         */

        // TimeSeries timeSeries = new TimeSeries("Parabola Simulation",
        // Millisecond.class);
        // TimeSeriesCollection timeSeriesCollection = new
        // TimeSeriesCollection(timeSeries);

        generateChart();
    }

    public void deleteParabola(int num) {
        dataset.removeSeries(num);
        count = 1;
        generateChart();
    }

    public void reset() {
        dataset.removeAllSeries();
        count = 1;
        generateChart();
    }

    public void draw(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        drawParabola();
    }

    public DrawParabola draw(Color backColor) {
        this.backColor = backColor;
        this.a = 0;
        this.b = 0;
        this.c = 0;
        generateChart();
        return this;
    }

    public void generateChart() {
        chart = ChartFactory.createXYLineChart("Parabola Simulation", "second", "y", dataset);

        panel = new ChartPanel(chart);
        panel.setVisible(true);

        changeColor();
    }

    public void changeColor() {
        panel.setBackground(backColor);
        panel.setOpaque(false);

        chart.setBackgroundPaint(backColor);
        chart.setBorderPaint(backColor);

        Plot plot = chart.getPlot();
        plot.setForegroundAlpha(1.0f);
        plot.setBackgroundAlpha(0.0f);
        plot.setOutlinePaint(null);
        plot.setBackgroundPaint(backColor);

        panel.repaint();
    }

    public void changeColor(Color backColor) {
        this.backColor = backColor;
        changeColor();
    }
}
