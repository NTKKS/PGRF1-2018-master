package c_05_streda_13_15.main;

import java.util.ArrayList;
import java.util.List;

public class Circle {
    private List<Point2> point = new ArrayList<Point2>();
    private int clicks = 0;

    public Circle() {
        this.point = point;
    }

    public void addPoint(int x, int y){
        Point2 vertex = new Point2(x,y);
        point.add(vertex);
    }

    public int getX(int index){
        Point2 at = point.get(index);
        return at.getX();
    }

    public int getY(int index){
        Point2 at = point.get(index);
        return at.getY();
    }

    public void click(){
        clicks++;
    }

    public int size(){
        return clicks;
    }

    public void clear(){
        clicks = 0;
        point.clear();
    }

}
