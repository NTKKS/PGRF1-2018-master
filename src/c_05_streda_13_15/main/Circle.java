package c_05_streda_13_15.main;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    private List<Point2> point = new ArrayList<Point2>();
    private int clicks = 0; //click counter
    private double dx, dy;
    private double angle;
    private double n = 3; //zakladni pocet vrcholu

    public Circle() {
    }

    public void setN(int i) {
        n += i;
        this.n = n;
        System.out.println("Pocet vrcholu: " + n);
    }

    public double getN() {
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

    // click counter
    public void click() {
        clicks++;
    }

    // vypocet polomeru
    public double getR() {
        dx = Math.abs(getX(0) - getX(1));
        dy = Math.abs(getY(0) - getY(1));
        return (Math.max(dx, dy));
    }

    // vypocet vrcholu a pridani do seznamu
    public void getVert() {
        //v podstate dx, dy
        double x0 = getX(1) - getX(0);
        double y0 = getY(1) - getY(0);

        //vypocet uhlu a prevedeni na radiany
        angle = 360 / n;
        double rad = Math.toRadians(angle);
        double r = getR();

        //vypocet hran n-uhelniku
        for (double i = rad; i <= Math.toRadians(360); i += rad) {
            //matrix rotation formula
            double x = x0 * Math.cos(i) - y0 * Math.sin(i);
            double y = x0 * Math.sin(i) + y0 * Math.cos(i);

            //posun na pocatek n-uhleniku
            x += getX(0);
            y += getY(0);
            //pridej vrchol do seznamu
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
