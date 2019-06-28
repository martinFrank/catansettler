package com.github.martinfrank.catansettler.gui;


import com.github.martinfrank.catansettler.map.GameMapEdge;
import com.github.martinfrank.catansettler.map.GameMapField;
import com.github.martinfrank.catansettler.map.GameMapNode;

public class MouseSelection {

    private final int x;
    private final int y;
    private GameMapNode node;
    private GameMapEdge edge;
    private GameMapField field;

    public MouseSelection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public GameMapNode getNode() {
        return node;
    }

    public void setNode(GameMapNode node) {
        this.node = node;
    }

    public boolean hasNode() {
        return node != null;
    }

    public GameMapEdge getEdge() {
        return edge;
    }

    public void setEdge(GameMapEdge edge) {
        this.edge = edge;
    }

    public boolean hasEdge() {
        return edge != null;
    }

    public GameMapField getField() {
        return field;
    }

    public void setField(GameMapField field) {
        this.field = field;
    }

    public boolean hasField() {
        return field != null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "mouse selection @ " + x + "/" + y + " field='" + field + "', edge='" + edge + "', node='" + node + "'.";
    }
}
