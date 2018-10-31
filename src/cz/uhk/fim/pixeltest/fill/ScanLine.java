package cz.uhk.fim.pixeltest.fill;

import cz.uhk.fim.pixeltest.model.Edge;
import cz.uhk.fim.pixeltest.model.Point;
import cz.uhk.fim.pixeltest.view.Raster;

import java.util.ArrayList;
import java.util.List;

public class ScanLine implements Filler{

    private Raster raster;
    private int fillColor, edgeColor;
    private List<Point> points;
    private Edge edge;

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill() {
        scanLine();

    }

    public void init(List<Point> points, int fillColor, int edgeColor){
        this.points = points;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
    }

    private void scanLine(){

        List<Edge> edges = new ArrayList<>();
        //TODO
        /*
         * List<Point> => List<Edge>
         * hrany orientovane podle osy Y (shora dolu)
         * vodorovne nechceme
         * ziskat Y min, Y max
         * v tomto rozsahu vest cyklus
         * spocitat protnuti s horizontalni linkou a hranou
         * List<Prusecik>
         * seradit pruseciky
         * spojit liche a sude
         * obtahnout hrany
         * */
    }
}
