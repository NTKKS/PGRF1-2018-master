package cz.uhk.fim.pixeltest.controller;

import cz.uhk.fim.pixeltest.fill.ScanLine;
import cz.uhk.fim.pixeltest.fill.SeedFiller;
import cz.uhk.fim.pixeltest.model.Point;
import cz.uhk.fim.pixeltest.renderer.Renderer;
import cz.uhk.fim.pixeltest.view.PGRFWindow;
import cz.uhk.fim.pixeltest.view.Raster;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class PGRFController {

    private Raster raster;
    private Renderer renderer; //implementace vykreslovacich algoritmu
    private SeedFiller seedFiller;
    private List<Point> polygonPoints = new ArrayList<>();
    private List<Point> clipPoints = new ArrayList<>();
    private List<Point> linePoints = new ArrayList<>();
    private ScanLine scanLine;

    public PGRFController(PGRFWindow window) {
        initObjects(window);
        initListeners();
    }

    private void initObjects(PGRFWindow window) {
        raster = new Raster();
        raster.setFocusable(true);
        raster.grabFocus();
        window.add(raster); // vložit plátno do okna

        renderer = new Renderer(raster);

        seedFiller = new SeedFiller();
        seedFiller.setRaster(raster);

        scanLine = new ScanLine();
        scanLine.setRaster(raster);
    }

    private void initListeners() {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e) && !e.isControlDown() && !e.isShiftDown()) {
                    polygonPoints.add(new Point(e.getX(), e.getY()));
                    if (polygonPoints.size() == 1) { // při prvním kliknutí přidat rovnou i druhý bod
                        polygonPoints.add(new Point(e.getX(), e.getY()));
                    }
                } else if (SwingUtilities.isRightMouseButton(e) && !e.isControlDown() && !e.isShiftDown()) {
                    //linePoints.add(new Point(e.getX(), e.getY()));
                    //linePoints.add(new Point(e.getX(), e.getY()));
                    clipPoints.add(new Point(e.getX(), e.getY()));
                    if (clipPoints.size() == 1) { // při prvním kliknutí přidat rovnou i druhý bod
                        clipPoints.add(new Point(e.getX(), e.getY()));
                    }
                }
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.isControlDown() && SwingUtilities.isLeftMouseButton(e)) {
                    seedFiller.init(e.getX(), e.getY(), 0xff00ff, false);
                    seedFiller.fill();
                } else if (e.isControlDown() && SwingUtilities.isRightMouseButton(e)) {
                    scanLine.init((renderer.clip(polygonPoints, clipPoints)), 0x00ff00, 0xff0000, false);
                    scanLine.fill();
                } else if (e.isShiftDown() && SwingUtilities.isRightMouseButton(e)) {
                    scanLine.init((renderer.clip(polygonPoints, clipPoints)), 0x00ff00, 0xff0000, true);
                    scanLine.fill();
                } else if (e.isShiftDown() && SwingUtilities.isLeftMouseButton(e)) {
                    seedFiller.init(e.getX(), e.getY(), 0xff00ff, true);
                    seedFiller.fill();
                }
            }
        });
        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    polygonPoints.get(polygonPoints.size() - 1).x = e.getX();
                    polygonPoints.get(polygonPoints.size() - 1).y = e.getY();
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    clipPoints.get(clipPoints.size() - 1).x = e.getX();
                    clipPoints.get(clipPoints.size() - 1).y = e.getY();
                }
                update();
            }
        });
        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //System.out.println(e.getKeyCode());
                // při zmáčknutí klávesy C vymazat plátno
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    raster.clear();
                    linePoints.clear();
                    polygonPoints.clear();
                    clipPoints.clear();
                }
            }
        });
    }

    private void update() {
        raster.clear();
        renderer.drawLines(linePoints, 0x00ff00);
        if (!polygonPoints.isEmpty()) {
            renderer.drawPolygon(polygonPoints, 0xff0000);
        }
        if (!clipPoints.isEmpty()) {
            renderer.drawPolygon(clipPoints, 0x0000ff);
        }
    }

}


