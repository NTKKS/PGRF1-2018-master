package cz.uhk.fim.pixeltest.renderer;

import cz.uhk.fim.pixeltest.model3d.Solid;
import cz.uhk.fim.pixeltest.view.Raster;
import transforms.*;

import java.awt.*;
import java.util.List;

public class Renderer3D {

    private Raster raster;
    private Mat4 model;
    private Mat4 view;
    private Mat4 projection;

    public Renderer3D(Raster raster) {
        this.raster = raster;

        model = new Mat4Identity();

        Vec3D e = new Vec3D(0, -5, 4);
        Vec3D v = new Vec3D(0, 5, -4);
        Vec3D u = new Vec3D(0, 0, 1);

        view = new Mat4ViewRH(e, v, u);

        projection = new Mat4PerspRH(
                Math.PI / 4,
                Raster.HEIGHT / (double) Raster.WIDTH,
                0.1,
                200);
    }

    public void draw(Solid... solids) {
        for (Solid solid : solids
        ) {
            List<Point3D> vertices = solid.getVertices();
            List<Integer> indices = solid.getIndices();
            for (int i = 0; i < solid.getIndices().size(); i += 2) {
                Integer i1 = indices.get(i);
                Point3D a = vertices.get(i1);
                Integer i2 = indices.get(i + 1);
                Point3D b = vertices.get(i2);
                drawLine(a, b, solid.getColor());
            }
        }
    }

    public void drawLine(Point3D a, Point3D b, Color color) {
        a = a.mul(model).mul(view).mul(projection);
        b = b.mul(model).mul(view).mul(projection);

        Vec3D v1, v2;
        if (a.dehomog().isPresent()) {
            v1 = a.dehomog().get();
        } else {
            v1 = new Vec3D(0);
        }
        if (b.dehomog().isPresent()) {
            v2 = b.dehomog().get();
        } else {
            v2 = new Vec3D(0);
        }

        if (Math.abs(v1.getX()) > 1 || Math.abs(v1.getY()) > 1 || v1.getZ() < 0 || v1.getZ() > 1) {
            return;
        }
        if (Math.abs(v2.getX()) > 1 || Math.abs(v2.getY()) > 1 || v2.getZ() < 0 || v2.getZ() > 1) {
            return;
        }

        v1 = v1.add(new Vec3D(1, 1, 0))
                .mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));
        v2 = v2.add(new Vec3D(1, 1, 0))
                .mul(new Vec3D(Raster.WIDTH / 2f, Raster.HEIGHT / 2f, 1));

        raster.drawLine(v1.getX(), v1.getY(), v2.getX(), v2.getY(), color);
    }


}
