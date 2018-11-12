package cz.uhk.fim.pixeltest.fill;

import cz.uhk.fim.pixeltest.view.Raster;

public class SeedFiller implements Filler {

    private Raster raster;
    private int fillColor, bgColor;
    private int x, y;
    private boolean useSample;

    private static final int[][] sample = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}, {0, 1, 1, 1, 0}, {0, 0, 1, 0, 0}, {0, 0, 0, 0, 0}};

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill() {
        seed(x, y);
    }

    public void init(int x, int y, int fillColor, boolean useSample) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        bgColor = raster.getPixel(x, y);
        this.useSample = useSample;
    }

    private int sampleColor(int ax, int ay) {
        int i = ax % 5;
        int j = ay % 5;
        int color = 0xffffff;
        if (sample[i][j] == 1) {
            return color;
        } else {
            return fillColor;
        }
    }

    private void seed(int ax, int ay) {
        if (ax >= 0 && ay >= 0 && ax < Raster.WIDTH && ay < Raster.HEIGHT) {
            if (bgColor == raster.getPixel(ax, ay)) {
                if (useSample) {
                    raster.drawPixel(ax, ay, sampleColor(ax, ay));
                } else {
                    raster.drawPixel(ax, ay, fillColor);
                }
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay + 1);
                seed(ax, ay - 1);
            }
        }
    }
}
