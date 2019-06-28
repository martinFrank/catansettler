package com.github.martinfrank.catansettler.gui;

import com.github.martinfrank.catansettler.map.GameMap;
import com.github.martinfrank.drawlib.Shape;
import javafx.scene.canvas.Canvas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameMapCanvas extends Canvas {

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GameMapCanvas.class);

    private GameMap map;
    public void setMap(GameMap map) {
        this.map = map;
        Shape aField = map.getFields().get(0).getShape();
        double fieldHeight = aField.getTransformed().getHeight();
        setWidth(map.getTransformed().getWidth());
        setHeight(map.getTransformed().getHeight() - fieldHeight*1.5);
        drawMap();
    }

    void drawMap() {
        if (map != null) {
            map.draw(getGraphicsContext2D());
        }
    }


    public MouseSelection getSelectionAt(int x, int y) {
        MouseSelection selection = new MouseSelection(x,y);
        if (map != null) {
            selection.setNode(map.getNodeAt(x, y));
            selection.setEdge(map.getEdgeAt(x, y));
            selection.setField(map.getFieldAt(x, y));
        }
        return selection;
    }
}
