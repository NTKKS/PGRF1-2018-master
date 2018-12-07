package cz.uhk.fim.pixeltest.model3d;

import transforms.Point3D;

import java.awt.*;

public class YAxis extends Solid {

    public YAxis() {
        color = Color.GREEN;
        isAxis = true;

        vertices.add(new Point3D(0,0,0));
        vertices.add(new Point3D(0,1,0));

        indices.add(0);
        indices.add(1);
    }
}
