package cz.uhk.fim.pixeltest.view;

import javax.swing.*;

public class PGRFWindow extends JFrame {

    private Raster raster;

    public PGRFWindow() {
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(Raster.WIDTH, Raster.HEIGHT); // velikost okna
        setLocationRelativeTo(null);// vycentrovat okno
        setTitle("PGRF1 cvičení"); // titulek okna

        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus();
        add(raster);
    }

    public Raster getRaster() {
        return raster;
    }
}
