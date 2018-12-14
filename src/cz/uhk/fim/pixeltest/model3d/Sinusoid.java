package cz.uhk.fim.pixeltest.model3d;

import transforms.Mat4Transl;
import transforms.Point3D;

import java.awt.*;

public class Sinusoid extends Solid {

    public Sinusoid() {

        color = Color.YELLOW;
        isAxis = false;
        int i = 0;
        for (double a = 0; a<= Math.PI/2; a+=0.01){
            double x = a;
            double y = 0;
            double z = Math.sin(a*10);
            vertices.add(new Point3D(x,y,z));
            if (a>0) {
                indices.add(i);
                indices.add(++i);
            }
        }

        //posunuti objektu
        for (int j = 0; j < vertices.size(); j++) {
            vertices.set(j,(vertices.get(j).mul(new Mat4Transl(-1, 0, 3))));
        }

    }
}
