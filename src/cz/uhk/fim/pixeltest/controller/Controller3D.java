package cz.uhk.fim.pixeltest.controller;

import cz.uhk.fim.pixeltest.model3d.Cube;
import cz.uhk.fim.pixeltest.model3d.Solid;
import cz.uhk.fim.pixeltest.renderer.Renderer3D;
import cz.uhk.fim.pixeltest.view.Raster;
import transforms.Camera;
import transforms.Mat4;
import transforms.Mat4RotXYZ;
import transforms.Vec3D;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller3D {

    private int mx,my;
    //private Raster raster;
    private final Renderer3D renderer3D;
    private Solid cube;
    private Camera camera;

    public Controller3D(Raster raster) {
        //this.raster = raster;
        renderer3D = new Renderer3D(raster);
        initObjects();
        initListeners(raster);
    }

    private void resetCamera(){
        camera = new Camera(
                new Vec3D(0,-5,4),
                Math.toRadians(90),//atimuth
                Math.toRadians(-40),//zenith
                1,true
        );
        renderer3D.setView(camera.getViewMatrix());
    }

    private void initObjects() {
        //renderer3D = new Renderer3D(raster);
        cube = new Cube();
        renderer3D.add(cube);
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
                if (SwingUtilities.isLeftMouseButton(e)){
                    double diff = (mx - e.getX()) / 200.0;
                    double azimuth = camera.getAzimuth() + diff;
                    camera = camera.withAzimuth(azimuth);
                    //dodělat zenit
                    double diff2 = my - e.getY() / 200.0;
                    double zenit = camera.getZenith() + diff2;
                    /*
                    if (zenit>=0){
                        zenit = Math.min(((my - e.getY()) / 200.0),Math.PI/2);
                    }else {
                        zenit = Math.max(((my - e.getY()) / 200.0),-(Math.PI/2));
                    }*/
                    System.out.println(zenit);
                    System.out.println(azimuth);
                    camera = camera.withZenith(zenit);
                    renderer3D.setView(camera.getViewMatrix());
                }

                else if (SwingUtilities.isRightMouseButton(e)){
                    double rotX = (mx - e.getX())/-200.0;
                    double rotY = (my - e.getY())/-200.0;
                    Mat4 rot = renderer3D.getModel().mul(new Mat4RotXYZ(rotY,0,rotX));
                    renderer3D.setModel(rot);
                }

                mx = e.getX();
                my = e.getY();
            }
        });

        raster.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_UP:
                        case KeyEvent.VK_W:
                            camera = camera.forward(1);
                            renderer3D.setView(camera.getViewMatrix());
                            break;
                            //doplnit ovladani
                }
            }
        });
    }

}
