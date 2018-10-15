package c_05_streda_13_15.main;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    private List<Point2> point = new ArrayList<Point2>();
    private int clicks = 0;
    private int dx;
    private int dy;
    private float r;
    private int angle;

    public Circle() {
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

    public int getR() {
        dx = Math.abs(getX(0) - getX(1));
        dy = Math.abs(getY(0) - getY(1));
        return (Math.max(dx, dy));
    }

    public void getVert() {

        double a = getY(0)-getY(1);
        a = a/getR();
        int baseDeg = (int) Math.toDegrees(Math.asin(a));
        angle = 45+baseDeg;
        double rad = Math.toRadians(angle);
        int r = getR();
        for (double i = rad; i <= Math.toRadians(360); i += rad) {
            int x = (int) (r * Math.cos(i));
            int y = (int) (r * Math.sin(i));
            x += getX(0);
            y += getY(0);
            Point2 pt = new Point2(x, y);
            point.add(pt);
            //TODO
            //vyřešit pro jine uhle 90 +
        }
    }

    public int clickCount() {
        return clicks;
    }

    public int getSize(){
        return point.size();
    }

    public void clear() {
        clicks = 0;
        point.clear();
    }

}
