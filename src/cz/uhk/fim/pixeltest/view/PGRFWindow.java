package cz.uhk.fim.pixeltest.view;

import javax.swing.*;

public class PGRFWindow extends JFrame{

    private final Raster raster;

    public PGRFWindow() {
        // bez tohoto nastavení se okno zavře, ale aplikace stále běží na pozadí
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(800, 600); // velikost okna
        setLocationRelativeTo(null);// vycentrovat okno
        setTitle("PGRF1 cvičení"); // titulek okna

        raster = new Raster();

        add(raster); // vložit plátno do okna
    }

    public Raster getRaster(){
        return raster;
    }
}
