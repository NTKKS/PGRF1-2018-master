package cz.uhk.fim.pixeltest.model;

public class Edge {

    private int x1, y1, x2, y2;

    public Edge(Point p1, Point p2) {
        x1 = p1.x;
        y1 = p1.y;
        x2 = p2.x;
        y2 = p2.y;
    }

    /**
     * Zjistí, zda je hrana vodorovná
     *
     * @return true pokud je vodorovná, jinak false
     */
    public boolean isHorizontal() {
        return y1 == y2;
    }

    /**
     * Zorientuje hranu odshora dolů
     */
    public void orientate() {
        if (y1 > y2) {
            int a1 = x1;
            int b1 = y1;
            x1 = x2;
            y1 = y2;
            x2 = a1;
            y2 = b1;
        }
    }

    /**
     * Zjistí, zda existuje průsečík scanline s hranou
     *
     * @param y y-ová souřadnice scanline
     * @return true pokud průsečík existuje, jinak false
     */
    public boolean intersectionExists(int y) {
        return y >= y1 && y < y2;
    }

    /**
     * Vypočítá a vrátí x-ovou souřadnici průsečíku se scanline
     *
     * @param y y-ová souřadnice scanline
     * @return souřadnice x
     */
    public int getIntersection(int y) {
        float k,q,x;

        k = (float)(x2 - x1) / (float)(y2 - y1);
        q = (float)x1 - k * (float)y1;

        x = k*y + q;

        return (int)x;
    }

    /**
     * Zjistí, na které straně přímky tvořené touto úsečkou se nachází bod z parametru
     *
     * @param p testovaný bod
     * @return true pokud se nachází uvnitř (za předpokladu správné orientace)
     */
    public boolean inside(Point p) {
        Point t = new Point(x2 - x1, y2 - y1);
        Point n = new Point(t.y, -t.x);
        //Point n = new Point(-t.y, t.x);
        Point v = new Point(p.x - x1, p.y - y1);
        return (v.x * n.x + v.y * n.y < 0);
    }

    /**
     * Vypočítání průsečíku dvou hran
     *
     * @param v1 první bod druhé hrany
     * @param v2 druhý bod druhé hrany
     * @return průsečík
     */
    public Point getIntersection(Point v1, Point v2) {
        float x0 = ((v1.x * v2.y - v1.y * v2.x) * (x1 - x2) - (x1 * y2 - y1 * x2) * (v1.x - v2.x))
                / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));

        float y0 = ((v1.x * v2.y - v1.y * v2.x) * (y1 - y2) - (x1 * y2 - y1 * x2) * (v1.y - v2.y))
                / (float) ((v1.x - v2.x) * (y1 - y2) - (x1 - x2) * (v1.y - v2.y));

        return new Point(Math.round(x0), Math.round(y0));
    }

}
