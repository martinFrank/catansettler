package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.mapdata.GameMapNodeData;
import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.maplib.MapNode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameMapNode extends MapNode<GameMapNodeData, GameMapField, GameMapEdge, GameMapNode> {

    public GameMapNode(GameMapNodeData mapNodeData) {
        super(mapNodeData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;

        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        Point point = getPoint().getTransformed();
        gc.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
    }
}
