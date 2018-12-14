package cz.uhk.fim.pixeltest.model3d;

import transforms.Mat4Transl;
import transforms.Point3D;

import java.awt.*;

public class SphericalHelix extends Solid {

    public SphericalHelix() {
        color = Color.GREEN;
        isAxis = false;

        int i = 0;
        double a = 0.2;
        double r = 1;
        //alg podle predpisu funkce z webu wolfram alpha
        for (double t = -30; t<= 30; t+=0.1){
            double x = ((r*Math.cos(t))/(Math.sqrt(Math.pow(a,2)*Math.pow(t,2)+1)));
            double y = ((r*Math.sin(t))/(Math.sqrt(Math.pow(a,2)*Math.pow(t,2)+1)));
            double z = (-((a*r*t)/(Math.sqrt(Math.pow(a,2)*Math.pow(t,2)+1))));
            vertices.add(new Point3D(x,y,z));
            if (t>-30) {
                indices.add(i);
                indices.add(++i);
            }
        }

        //posunuti objektu
        for (int j = 0; j < vertices.size(); j++) {
            vertices.set(j,(vertices.get(j).mul(new Mat4Transl(3.5, -2.5, 0))));
        }

    }
}
