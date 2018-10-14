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

    public int getR(){
        dx = Math.abs(getX(0)-getX(1));
        dy = Math.abs(getY(0)-getY(1));
        return (Math.max(dx,dy));
    }

    public void getVert(){
        angle = 10;
        for (float fi = 0;fi<=Math.PI/2;fi+=angle){
            int x = (int)(getR()*Math.cos(fi));
            int y = (int)(getR()*Math.sin(fi));
            Point2 vert = new Point2(x,y);
            point.add(vert);
            System.out.println("fi "+fi);
        }
        System.out.println(size());
    }

    public int size(){
        return clicks;
    }

    public void clear(){
        clicks = 0;
        point.clear();
    }

}
