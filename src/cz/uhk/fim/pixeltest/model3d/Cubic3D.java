package cz.uhk.fim.pixeltest.model3d;

import transforms.Cubic;
import transforms.Mat4;
import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cubic3D extends Solid {

    private List<Point3D> points = new ArrayList<>();

    public Cubic3D(Mat4 type) {

        color = Color.CYAN;
        isAxis = false;
        //fergusonova vyzaduje jine poradi bodu
        if (type.eEquals(Cubic.FERGUSON)){
            points.add(new Point3D(-1,-1,-1));
            points.add(new Point3D(1,1,-1));
            points.add(new Point3D(2, 2, 2));
            points.add(new Point3D(3,3,-3));
        }else{
            points.add(new Point3D(-1, -1, -1));
            points.add(new Point3D(-1, -1, 1));
            points.add(new Point3D(1, 1, 1));
            points.add(new Point3D(1, 1, -1));
        }
        create(type);

    }
    public void create(Mat4 type){

        List<Cubic> cubics = new ArrayList<>();
        for (int i = 0; i < points.size()-3; i+=3) {
            cubics.add(new Cubic(type,points.get(i),points.get(i+1),points.get(i+2),points.get(i+3)));
        }

        int i = 0;
        for (Cubic cubic : cubics) {
            Point3D p1 = cubic.compute(0);
            for (double a = 0;a<=Math.PI*2;a+=0.05){
                Point3D p2 = cubic.compute(a);
                vertices.add(p1);
                vertices.add(p2);
                p1 = p2;
                indices.add(i);
                indices.add(++i);
            }
        }

    }
}
