package com.github.martinfrank.catansettler.resource.image;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class ScaleFilter {

    public static Image scale(Image source, int newWidth, int newHeight) {
        PixelReader reader = source.getPixelReader();
        double width = (int)source.getWidth();
        double height = (int)source.getHeight();

        double scalex = width / newWidth;
        double scaley = height / newHeight;


        WritableImage dest = new WritableImage(newWidth, newHeight);
        PixelWriter writer = dest.getPixelWriter();

        for (int x = 0; x < newWidth; x++) {
            for (int y = 0; y < newHeight; y++) {
                int xAtSource = (int)(x * scalex);
                int yAtSource = (int)(y * scaley);
                Color color = reader.getColor(xAtSource, yAtSource);
                writer.setColor(x, y, color);

            }
        }
        return dest;
    }
}
