package cz.uhk.fim.pixeltest.model3d;

import transforms.Point3D;

import java.awt.*;

public class XAxis extends Solid {

    public XAxis() {
        color = Color.RED;
        isAxis = true;

        vertices.add(new Point3D(0,0,0));
        vertices.add(new Point3D(1,0,0));

        indices.add(0);
        indices.add(1);
    }
}
