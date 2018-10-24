package cz.uhk.fim.pixeltest.fill;

import java.awt.image.BufferedImage;

public interface Filler {

    void fill();

    void setBufferedImage(BufferedImage image);
}
