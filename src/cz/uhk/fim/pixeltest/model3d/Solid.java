package cz.uhk.fim.pixeltest.model3d;

import transforms.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Solid {

    List<Point3D> vertices = new ArrayList<>();
    List<Integer> indices = new ArrayList<>();
    Color color;

    public List<Point3D> getVertices() {
        return vertices;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public Color getColor() {
        return color;
    }
}
