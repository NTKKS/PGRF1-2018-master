package cz.uhk.fim.pixeltest.model3d;

import transforms.Point3D;

import java.util.List;

public abstract class Solid {
    public List<Point3D> vertices;
    public List<Integer> indices;
    private int color;

    public List<Point3D> getVertices() {
        return vertices;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public int getColor() {
        return color;
    }
}
