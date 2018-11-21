package cz.uhk.fim.pixeltest.controller;

import cz.uhk.fim.pixeltest.model3d.Cube;
import cz.uhk.fim.pixeltest.model3d.Solid;
import cz.uhk.fim.pixeltest.renderer.Renderer3D;
import cz.uhk.fim.pixeltest.view.Raster;

public class Controller3D {

    private Raster raster;
    private Renderer3D renderer3D;
    private Solid cube;

    public Controller3D(Raster raster) {
        this.raster = raster;
        initObjects();
        initListeners();
        renderer3D.draw(cube);
    }

    private void initListeners() {

    }

    private void initObjects() {
        renderer3D = new Renderer3D(raster);
        cube = new Cube();
    }

}
