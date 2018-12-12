package cz.uhk.fim.pixeltest.model3d;

import transforms.Mat4Transl;
import transforms.Point3D;

import java.awt.*;

public class Spiral extends Solid {

    public Spiral() {
        //křivka v cilyndrickych souradnicich
        //TODO vymyslet další
        color = Color.MAGENTA;
        int i = 0;
        for (double a = 0; a<= Math.PI*10; a+=0.1){
            double x = Math.cos(a);
            double y = Math.sin(a);
            double z = a/20;
            vertices.add(new Point3D(x,y,z));
            if (a>0) {
                indices.add(i);
                indices.add(++i);
            }
        }

        //posunuti objektu
        for (int j = 0; j < vertices.size(); j++) {
            vertices.set(j,(vertices.get(j).mul(new Mat4Transl(3, 0, -1))));
        }

    }
}
