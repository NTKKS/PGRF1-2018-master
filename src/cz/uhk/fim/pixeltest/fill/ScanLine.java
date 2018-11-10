package cz.uhk.fim.pixeltest.fill;

import cz.uhk.fim.pixeltest.model.Edge;
import cz.uhk.fim.pixeltest.model.Point;
import cz.uhk.fim.pixeltest.renderer.Renderer;
import cz.uhk.fim.pixeltest.view.Raster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScanLine implements Filler {

    private Raster raster;
    private int fillColor, edgeColor;
    private List<Point> points;
    private Renderer renderer;

    @Override
    public void setRaster(Raster raster) {
        this.raster = raster;
    }

    @Override
    public void fill() {
        scanLine();

    }

    public void init(List<Point> points, int fillColor, int edgeColor) {
        this.points = points;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
        renderer = new Renderer(raster);
    }

    private void scanLine() {

        List<Edge> edges = new ArrayList<>();
        // projet všechny body a vytvořit z nich hrany (jako polygon)
        for (int i = 0; i < points.size() - 1; i++) {
            Edge newEdge = new Edge(points.get(i), points.get(i + 1));
            if (!newEdge.isHorizontal()) {
                edges.add(newEdge);
            }
        }
        Edge newEdge = new Edge(points.get(points.size()-1), points.get(0));
        if (!newEdge.isHorizontal()) {
            edges.add(newEdge);
        }

        // 0. a 1. bod budou první hrana; 1. a 2. bod budou druhá hrana
        // ...; poslední a 0. bod budou poslední hrana
        // ignorovat vodorovné hrany
        // vyvtořené hrany zorientovat a přidat do seznamu
        for (Edge e:edges
             ) {
            e.orientate();
        }
        // najít minimum a maximum pro Y
        int minY = points.get(0).y;
        int maxY = minY;
        // projet všechny body a najít minimální a maximální Y
        for (Point p : points
        ) {
            if (p.y > maxY) {
                maxY = p.y;
            } else if (p.y < minY) {
                minY = p.y;
            }
        }
        // pro všechna Y od min do max včetně
        for (int y = minY; y <= maxY; y++) {
            List<Integer> intersections = new ArrayList<>();
            // projít všechny hrany
            // pokud hrana má průsečík pro dané Y
            // tak vypočítáme průsečík a uložíme hodnotu do seznamu
            for (Edge e:edges
                 ) {
                if (e.intersectionExists(y)){
                    intersections.add(e.getIntersection(y));
                }
            }
            Collections.sort(intersections);
            // nebo volitelně implementovat vlastní algoritmus na seřazení

            // vybarvení mezi průsečíky
            // spojení vždy sudého s lichým
            // 0. a 1.; 2. a 3.;...

            for (int i=0;i<intersections.size()-1;i+=2){
                renderer.drawLineDDA(intersections.get(i),y,intersections.get(i+1),y,fillColor);
            }
        }

        // obtáhnutí hranice
        renderer.drawPolygon(points, fillColor);
    }
}
