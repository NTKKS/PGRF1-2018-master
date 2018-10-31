package cz.uhk.fim.pixeltest.view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Raster extends Canvas {

    private final BufferedImage img;
    private static final int FPS = 1000 / 30;
    public static final int WIDTH = 800, HEIGHT = 600;

    public Raster() {
        // inicializace image, nastavení rozměrů (nastavení typu - pro nás nedůležité)
        img = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        setLoop();
    }
    //TODO opravit blikání
    private void setLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if (getGraphics() == null) return;
                getGraphics().drawImage(img, 0, 0, null);
            }
        }, 0, FPS);
    }

    public void clear() {
        //vyčisti canvas
        Graphics g = img.getGraphics();
        g.setColor(Color.BLACK);
        g.clearRect(0, 0, WIDTH, HEIGHT);
    }

    public void drawPixel(int x, int y, int color) {
        //vykresli pixel
        img.setRGB(x, y, color);
    }

    public int getPixel(int x, int y) {
        return img.getRGB(x, y);
    }

}
