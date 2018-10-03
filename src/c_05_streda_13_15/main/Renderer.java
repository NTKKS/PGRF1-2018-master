package c_05_streda_13_15.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Renderer {
    private BufferedImage img;
    private Canvas canvas;
    private static final int FPS = 1000/30;

    public Renderer(BufferedImage img, Canvas canvas) {
        this.img = img;
        this.canvas = canvas;
        setLoop();
    }

    private void setLoop() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                canvas.getGraphics().drawImage(img, 0,0,null);
                }
        },0, FPS);
    }

    public void drawLine(int x1, int y1, int x2, int y2, int color) {
        int dx = x2 - x1;
        int dy = y2 -y1;
        float k = dy / (float) dx;
        float q = y1 - k * x1;

        for (int x = x1; x<= x2; x++){
            int y = Math.round(k*x+q);
            drawPixel(x,y,color);
        }

    }

    public void  drawPixel(int x, int y, int color) {
        //nastavit pixel do img
        img.setRGB(x,y,color);
    }


}
