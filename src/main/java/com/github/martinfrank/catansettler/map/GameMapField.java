package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.mapdata.GameMapFieldData;
import com.github.martinfrank.catansettler.model.FieldResource;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.maplib.MapField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class GameMapField extends MapField<GameMapFieldData, GameMapField, GameMapEdge, GameMapNode> {

    private static final int IMAGE_X_OFF_SET = -48;
    private static final int IMAGE_Y_OFF_SET = -48;
//    private int angle = -60 + new Random().nextInt(3)*60;


    public GameMapField(GameMapFieldData mapFieldData) {
        super(mapFieldData);
    }

    @Override
    public void draw(Object drawContext) {
        GraphicsContext gc = (GraphicsContext) drawContext;
        GameMapFieldData data = getData();
        FieldResource fieldResource = data.getFieldResource();
        if (fieldResource.isResource() ) {
            ResourceImages resource = data.getResource();
            Image image = resource.getHexImage(data.getFieldResource());

            gc.setStroke(Color.DARKGRAY);
            gc.setLineWidth(1);

            Shape shape = getShape().getTransformed();
            double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
            double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
            int amount = xs.length;
            gc.fillPolygon(xs, ys, amount);

            if (image != null) {
                //            setRotation(gc, shape.getCenter());
                gc.drawImage(image,
                        shape.getCenter().getX() + IMAGE_X_OFF_SET,
                        shape.getCenter().getY() + IMAGE_Y_OFF_SET);
                //            revertRotation(gc, shape.getCenter());
            } else {
                System.out.println("images = null!");
            }

            int dice = getData().getFieldDice();
            if (dice != 0) {
                gc.setFill(Color.WHITE);
                final double r = 14;
                gc.fillOval(shape.getCenter().getX()-r, shape.getCenter().getY()-r, 2*r, 2*r);
                gc.setFill(Color.BLACK);

                String text = ""+dice;
                final Text textThrowAway = new Text(text);
//                Font font = Font.font("Arial", 20);
//                textThrowAway.setFont(font);

                final double w = textThrowAway.getLayoutBounds().getWidth();
                final double h = textThrowAway.getFont().getSize();

                gc.fillText("" + dice, shape.getCenter().getX() - (w / 2), shape.getCenter().getY() + (h / 2) - 1);
            }

            getEdges().forEach(e -> e.draw(drawContext));

            getNodes().forEach(p -> p.draw(drawContext));
        }
    }

    public void clearFieldData() {
        getData().clear();

        getEdges().forEach(GameMapEdge::clearEdgeData);
        getNodes().forEach(GameMapNode::clearNodeData);
    }

//    private void revertRotation(GraphicsContext gc, Point center) {
//        setRotation(gc, center, -1 * angle);
//    }
//
//    private void setRotation(GraphicsContext gc, Point center) {
//        setRotation(gc, center, angle);
//    }
//
//    private void setRotation(GraphicsContext gc, Point center, double angle) {
//        Affine transform = gc.getTransform();
//        transform.appendRotation(angle, center.getX(), center.getY());
//        gc.setTransform(transform);
//    }

}
