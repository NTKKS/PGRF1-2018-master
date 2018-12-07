package cz.uhk.fim.pixeltest.model3d;

import transforms.Mat4Transl;
import transforms.Point3D;

import java.awt.*;

public class Pyramid extends Solid {

    public Pyramid() {
        color = Color.ORANGE;
        isAxis = false;

        vertices.add(new Point3D(-1, 1, -1));
        vertices.add(new Point3D(1, 1, -1));
        vertices.add(new Point3D(1, -1, -1));
        vertices.add(new Point3D(-1, -1, -1));
        vertices.add(new Point3D(0, 0, 1));

        //posunuti objektu
        for (int i = 0; i < vertices.size(); i++) {
            vertices.set(i,(vertices.get(i).mul(new Mat4Transl(-3, 0, 0))));
        }

        indices.add(0);
        indices.add(1);
        indices.add(1);
        indices.add(2);
        indices.add(2);
        indices.add(3);
        indices.add(3);
        indices.add(0);

        indices.add(0);
        indices.add(4);
        indices.add(1);
        indices.add(4);
        indices.add(2);
        indices.add(4);
        indices.add(3);
        indices.add(4);

    }
}
