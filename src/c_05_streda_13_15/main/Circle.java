package c_05_streda_13_15.main;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    private List<Point2> point = new ArrayList<Point2>();
    private int clicks = 0;
    private double dx;
    private double dy;
    private double r;
    private double angle;
    private int n = 3;

    public Circle() {
    }

    public void setN(int i) {
        n += i;
        this.n = n;
        System.out.println(n);
    }

    public int getN() {
        return n;
    }

    public void addPoint(int x, int y) {
        Point2 vertex = new Point2(x, y);
        point.add(vertex);
    }

    public int getX(int index) {
        Point2 at = point.get(index);
        return at.getX();
    }

    public int getY(int index) {
        Point2 at = point.get(index);
        return at.getY();
    }

    public void click() {
        clicks++;
    }

    public double getR() {
        dx = Math.abs(getX(0) - getX(1));
        dy = Math.abs(getY(0) - getY(1));
        return (Math.max(dx, dy));
    }

    public void getVert() {
        //v podstate dx, dy??
        double x0 = getX(1) - getX(0);
        double y0 = getY(1) - getY(0);

        dx = (getX(0) - getX(1));
        dy = (getY(0) - getY(1));
        double fraction = dy / getR();

        angle = 360 / n;
        double rad = Math.toRadians(angle);
        double r = getR();

        for (double i = rad; i <= Math.toRadians(360); i += rad) {
            //apply magic
            double x = x0 * Math.cos(i) - y0 * Math.sin(i);
            double y = x0 * Math.sin(i) + y0 * Math.cos(i);

            x += getX(0);
            y += getY(0);
            Point2 pt = new Point2((int) x, (int) y);
            point.add(pt);
        }

    }

    public int clickCount() {
        return clicks;
    }

    public int getSize() {
        return point.size();
    }

    public void clear() {
        clicks = 0;
        point.clear();
    }

}
