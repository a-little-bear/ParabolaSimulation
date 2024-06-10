package parabolaSimulation.draw;

import java.awt.Color;

public class RunnableDynamicParabola implements Runnable {

    private double a, b, c;
    private String name;
    private Color backColor;

    public RunnableDynamicParabola(String name, Color backColor, double a, double b, double c) {
        this.name = name;
        this.backColor = backColor;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void run() {
        new parabolaSimulation.draw.DynamicParabola(name, backColor, a, b, c);
    }
}
