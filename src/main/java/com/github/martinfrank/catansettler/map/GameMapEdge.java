package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.gui.PaintUtil;
import com.github.martinfrank.catansettler.mapdata.GameMapEdgeData;
import com.github.martinfrank.catansettler.model.FieldResource;
import com.github.martinfrank.catansettler.model.Road;
import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.maplib.MapEdge;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameMapEdge extends MapEdge<GameMapEdgeData, GameMapField, GameMapEdge, GameMapNode> {

    public GameMapEdge(GameMapEdgeData mapEdgeData) {
        super(mapEdgeData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(3);

        Point a = getLine().getTransformed().getA();
        Point b = getLine().getTransformed().getB();
        if(getFields().size() == 1) {
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
            return;
        }

        if (getFields().get(0).getData().getFieldResource() == FieldResource.EMPTY || getFields().get(1).getData().getFieldResource() == FieldResource.EMPTY){
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
        }


        if (getData().hasRoad()) {
            Road road = getData().getRoad();
            Paint color = PaintUtil.fromRgb(road.getOwner().getColor());
            gc.setStroke(color);
            gc.setLineWidth(8);
//            gc.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
            gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
        }

    }

    public void clearEdgeData() {
        getData().clear();
    }
}
