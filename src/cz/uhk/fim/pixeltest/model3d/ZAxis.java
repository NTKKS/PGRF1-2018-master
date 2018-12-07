package cz.uhk.fim.pixeltest.model3d;

import transforms.Point3D;

import java.awt.*;

public class ZAxis extends Solid {

    public ZAxis() {
        color = Color.BLUE;
        isAxis = true;

        vertices.add(new Point3D(0,0,0));
        vertices.add(new Point3D(0,0,1));

        indices.add(0);
        indices.add(1);
    }
}
