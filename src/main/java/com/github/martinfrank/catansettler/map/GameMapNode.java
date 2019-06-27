package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.gui.PaintUtil;
import com.github.martinfrank.catansettler.mapdata.GameMapNodeData;
import com.github.martinfrank.catansettler.model.Settlement;
import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.maplib.MapNode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class GameMapNode extends MapNode<GameMapNodeData, GameMapField, GameMapEdge, GameMapNode> {

    public GameMapNode(GameMapNodeData mapNodeData) {
        super(mapNodeData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;


        if (getData().hasSettlement()) {
            Settlement settlement = getData().getSettlement();
            Paint color = PaintUtil.fromRgb(settlement.getOwner().getColor());
            gc.setFill(color);
//            gc.setLineWidth(15);
            Point point = getPoint().getTransformed();
            int r = 10;
//            gc.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
            gc.fillOval(point.getX() - r, point.getY() - r, 2 * r, 2 * r);
        }

    }

    public void clearNodeData() {
        getData().clear();
    }
}
