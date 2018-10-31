package cz.uhk.fim.pixeltest.renderer;

import cz.uhk.fim.pixeltest.view.Raster;

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


}
