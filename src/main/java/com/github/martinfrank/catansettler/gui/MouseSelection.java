package com.github.martinfrank.catansettler.gui;


import com.github.martinfrank.catansettler.map.GameMapEdge;
import com.github.martinfrank.catansettler.map.GameMapField;
import com.github.martinfrank.catansettler.map.GameMapNode;

public class MouseSelection {

    private final int x;
    private final int y;
    private GameMapNode point;
    private GameMapEdge edge;
    private GameMapField field;

    public MouseSelection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameMapNode getPoint() {
        return point;
    }

    public void setPoint(GameMapNode point) {
        this.point = point;
    }

    public GameMapEdge getEdge() {
        return edge;
    }

    public void setEdge(GameMapEdge edge) {
        this.edge = edge;
    }

    public GameMapField getField() {
        return field;
    }

    public void setField(GameMapField field) {
        this.field = field;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "mouse selection @ "+x+"/"+y+" field='"+field+"', edge='"+edge+"', point='"+point+"'.";
    }
}
