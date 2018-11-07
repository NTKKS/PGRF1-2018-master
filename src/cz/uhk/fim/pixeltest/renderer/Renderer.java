package cz.uhk.fim.pixeltest.renderer;

import cz.uhk.fim.pixeltest.model.Point;
import cz.uhk.fim.pixeltest.view.Raster;

import java.util.ArrayList;
import java.util.List;

public class Renderer {

    private Raster raster;

    public Renderer(Raster raster) {
        this.raster = raster;
    }

    //primitivni draLine algoritmus
    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        float k = dy / (float) dx;
        float q = y1 - k * x1;

        if (Math.abs(k) < 1) {
            //ridici osa je X

            if (x2 < x1) {
                int pomocna = x1;
                x1 = x2;
                x2 = pomocna;
            }

            for (int x = x1; x <= x2; x++) {
                int y = Math.round(k * x + q);
                raster.drawPixel(x, y, color);
            }
        } else {
            //řídící osa je y
            if (y2 < y1) {
                int pomocna = y1;
                y1 = y2;
                y2 = pomocna;
            }

            for (int y = y1; y <= y2; y++) {
                int x = Math.round((y - q) / k);
                raster.drawPixel(x, y, color);
            }
        }
    }

    public void drawLineDDA(int x1, int y1, int x2, int y2, int color) {
        float x, y, g, h;
        int dx = x2 - x1;
        int dy = y2 - y1;
        x = x1;
        y = y1;
        float k = dy / (float) dx;

        if (Math.abs(k) < 1) {
            if (dx > 0) {
                g = 1;
                h = k;
            } else {
                g = -1;
                h = -k;
            }
        } else {
            if (dy > 0) {
                g = 1 / k;
                h = 1;
            } else {
                g = -(1 / k);
                h = -1;
            }
        }

        int length;
        if (Math.abs(dx) > Math.abs(dy)) {
            length = Math.abs(dx);
        } else {
            length = Math.abs(dy);
        }

        for (int i = 0; i <= length; i++) {
            raster.drawPixel(Math.round(x), Math.round(y), color);
            x = x + g;
            y = y + h;
        }
    }
    public void drawPolygon(List<Point> polygonPoints, int color) {
        for (int i = 0; i < polygonPoints.size() - 1; i++) {
            drawLineDDA(polygonPoints.get(i).x,
                    polygonPoints.get(i).y,
                    polygonPoints.get(i + 1).x,
                    polygonPoints.get(i + 1).y,
                    color
            );
        }
        drawLineDDA(polygonPoints.get(0).x,
                polygonPoints.get(0).y,
                polygonPoints.get(polygonPoints.size() - 1).x,
                polygonPoints.get(polygonPoints.size() - 1).y,
                color
        );
    }
    public void drawLines(List<Point> linePoints, int color) {
        for (int i = 0; i < linePoints.size() - 1; i+=2) {
            drawLineDDA(linePoints.get(i).x,
                    linePoints.get(i).y,
                    linePoints.get(i + 1).x,
                    linePoints.get(i + 1).y,
                    color
            );
        }
    }

    public List<Point> clip(List<Point> polygonPoints, List<Point> clipPoints){

        List<Point> in = polygonPoints;
        Point p1 = null; //vložit poslední clip point

        for (Point p2: clipPoints
             ) {
            List<Point> out = new ArrayList<>();
            //Edge e = //vytvoreni hrany z bodu p1 a p2
            //Point v1 = ...
            for (Point v2: in
                 ) {//TODO algoritmus

            }
            p1 = p2;
            in = out;
        }
        return in;
    }

}
