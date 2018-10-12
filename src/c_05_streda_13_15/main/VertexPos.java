package c_05_streda_13_15.main;

import java.util.ArrayList;

public class VertexPos {
    private ArrayList<Integer> pos = new ArrayList<>();
    private int nextX = 0;
    private int nextY = 1;

    public VertexPos() {
        this.pos = pos;
    }

    public void addPos(int x, int y) {
        pos.add(x);
        pos.add(y);
        System.out.println(pos);
    }

    public void clear() {
        pos.clear();
        nextX = 0;
        nextY = 1;
    }

    public boolean ready() {
        return pos.size() > 2;
    }

    public int getX() {
        int temp = nextX;
        nextX += 2;
        System.out.println(temp);
        System.out.println(pos.get(temp));
        return pos.get(temp);
    }

    public int getY() {
        int temp2 = nextY;
        nextY += 2;
        System.out.println(temp2);
        System.out.println(pos.get(temp2));
        return pos.get(temp2);
    }
}
