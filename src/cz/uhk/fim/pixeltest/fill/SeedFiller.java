package cz.uhk.fim.pixeltest.fill;

import cz.uhk.fim.pixeltest.view.Raster;

public class SeedFiller implements Filler {

    private Raster raster;
    private int fillColor, bgColor;
    private int x, y;

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill() {
        seed(x, y);
    }

    public void init(int x, int y, int fillColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        bgColor = raster.getPixel(x,y);
    }

    private void seed(int ax, int ay) {
        if (ax >= 0 && ay >= 0 && ax < Raster.WIDTH && ay < Raster.HEIGHT) {
            if (bgColor == raster.getPixel(ax, ay)) {
                raster.drawPixel(ax, ay, fillColor);
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay + 1);
                seed(ax, ay - 1);
            }
        }
    }
}
