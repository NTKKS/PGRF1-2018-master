package c_05_streda_13_15.main;

import java.util.ArrayList;

public class VertexPos {
    //trida s listem vrcholu x,y
    private ArrayList<Integer> pos = new ArrayList<>();
    private int nextX = 0;
    private int nextY = 1;
    private int temp = nextX;
    private int temp2 = nextY;

    public VertexPos() {
        this.pos = pos;
    }

    public void addPos(int x, int y) {
        pos.add(x);
        pos.add(y);
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
        temp = nextX;
        nextX += 2;
        return pos.get(temp);
    }

    public int getX(int index) {
        return pos.get(index);
    }

    public int getY() {
        temp2 = nextY;
        nextY += 2;
        return pos.get(temp2);
    }

    public int getY(int index) {
        return pos.get(index);
    }

    public int getSize() {
        return pos.size();
    }

    public int lastX() {
        return pos.get(temp + 2);
    }

    public int lastY() {
        return pos.get(temp2 + 2);
    }
}
