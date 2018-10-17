package cz.uhk.fim.pixeltest;

import java.awt.image.BufferedImage;

public class SeedFiller implements Filler {

    private BufferedImage img;
    private int fillColor, bgColor;
    private int x, y;

    @Override
    public void fill() {
        seed(x, y);
    }

    @Override
    public void setBufferedImage(BufferedImage image) {
        this.img = image;
    }

    public void init(int x, int y, int fillColor) {
        this.x = x;
        this.y = y;
        this.fillColor = fillColor;
        bgColor = img.getRGB(x, y);
    }

    private void seed(int ax, int ay) {
        if (ax >= 0 && ay >= 0 && ax < img.getWidth() && ay < img.getHeight()) {
            if (bgColor == img.getRGB(ax, ay)) {
                img.setRGB(ax, ay, fillColor);
                seed(ax + 1, ay);
                seed(ax - 1, ay);
                seed(ax, ay + 1);
                seed(ax, ay - 1);
            }
        }
    }
}
