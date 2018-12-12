package cz.uhk.fim.pixeltest.controller;

import cz.uhk.fim.pixeltest.model3d.*;
import cz.uhk.fim.pixeltest.renderer.Renderer3D;
import cz.uhk.fim.pixeltest.view.Raster;
import transforms.*;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller3D {

    private int mx, my;
    private final Renderer3D renderer3D;
    private Solid cube, pyramid,xaxis, yaxis, zaxis, spiral,cubic3d;
    private Camera camera;
    private boolean persp = true;

    public Controller3D(Raster raster) {
        renderer3D = new Renderer3D(raster, persp);
        initObjects();
        initListeners(raster);
    }

    private void resetCamera() {
        camera = new Camera(
                new Vec3D(0, -5, 4),
                Math.toRadians(90),//atimuth
                Math.toRadians(-40),//zenith
                1, true
        );
        renderer3D.setView(camera.getViewMatrix());
    }

    private void initObjects() {
        xaxis = new XAxis();
        yaxis = new YAxis();
        zaxis = new ZAxis();
        cube = new Cube();
        pyramid = new Pyramid();
        spiral = new Spiral();
        cubic3d = new Cubic3D();
        renderer3D.add(xaxis);
        renderer3D.add(yaxis);
        renderer3D.add(zaxis);
        renderer3D.add(cube);
        renderer3D.add(pyramid);
        renderer3D.add(spiral);
        renderer3D.add(cubic3d);
        resetCamera();
    }

    private void initListeners(Raster raster) {
        raster.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mx = e.getX();
                my = e.getY();
            }
        });

        raster.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //rotace pohledu
                if (SwingUtilities.isLeftMouseButton(e)) {
                    //azimuth
                    double diff = (mx - e.getX()) / 200.0;
                    double azimuth = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimuth);
                    //zenit
                    double diff2 = (my - e.getY()) / 200.0;
                    double zenit = camera.getZenith() + diff2;
                    //ořez zenitu na <-PI/2,PI/2>
                    if (zenit > (Math.PI / 2)) {
                        zenit = (Math.PI / 2);
                    } else if (zenit < (-(Math.PI / 2))) {
                        zenit = (-(Math.PI / 2));
                    }
                    camera = camera.withZenith(zenit);
                    renderer3D.setView(camera.getViewMatrix());
                }
                //rotace modelu
                else if (SwingUtilities.isRightMouseButton(e) && (!e.isControlDown())) {
                    double rotX = (mx - e.getX()) / -200.0;
                    double rotY = (my - e.getY()) / -200.0;
                    Mat4 rot = renderer3D.getModel().mul(new Mat4RotXYZ(rotY, 0, rotX));
                    renderer3D.setModel(rot);
                }
                //posun modelu
                else if (SwingUtilities.isMiddleMouseButton(e)) {
                    double translX = (mx - e.getX()) / -200.0;
                    double translY = (my - e.getY()) / 200.0;
                    Mat4 transl = renderer3D.getModel().mul(new Mat4Transl(translX, translY, 0));
                    renderer3D.setModel(transl);
                }
                //zmena meritka modelu
                else if (SwingUtilities.isRightMouseButton(e) && (e.isControlDown())) {
                    double scaleX = (1 + (e.getX() - mx) / 200.0);
                    double scaleY = (1 + (e.getY() - my) / 200.0);
                    Mat4 scale = renderer3D.getModel().mul(new Mat4Scale(scaleX, scaleY, 1));
                    renderer3D.setModel(scale);
                }

                mx = e.getX();
                my = e.getY();
            }
        });

        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                    case KeyEvent.VK_W:
                        camera = camera.forward(0.5);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_DOWN:
                    case KeyEvent.VK_S:
                        camera = camera.backward(0.5);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_LEFT:
                    case KeyEvent.VK_A:
                        camera = camera.left(0.5);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_RIGHT:
                    case KeyEvent.VK_D:
                        camera = camera.right(0.5);
                        renderer3D.setView(camera.getViewMatrix());
                        break;
                    case KeyEvent.VK_R:
                        resetCamera();
                        break;
                    case KeyEvent.VK_P:
                        persp = !persp;
                        renderer3D.init(persp);
                        renderer3D.setView(camera.getViewMatrix());

                }
            }
        });
    }

}
