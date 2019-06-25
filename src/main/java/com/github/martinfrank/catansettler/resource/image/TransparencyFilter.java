package com.github.martinfrank.catansettler.resource.image;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class TransparencyFilter {

    public static Image filter(Image source, int rgb){
        PixelReader reader = source.getPixelReader();
        int width = (int)source.getWidth();
        int height = (int)source.getHeight();

        WritableImage dest = new WritableImage(width, height);
        PixelWriter writer = dest.getPixelWriter();

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = reader.getColor(x, y);
                if(isMarkedTransparent( color, rgb)){
                    writer.setColor(x, y, Color.TRANSPARENT);
                }else{
                    writer.setColor(x, y, color);
                }
            }
        }
        return dest;
    }

    private static boolean isMarkedTransparent(Color color, int rgb) {
        double r = (0x00FF0000 & rgb) >> 16;
        double g = (0x0000FF00 & rgb) >> 8;
        double b = (0x000000FF & rgb);

        double dr =  r / 0xFF;
        double dg =  g / 0xFF;
        double db =  b / 0xFF;

        return color.getRed() == dr &&
                color.getGreen() == dg &&
                color.getBlue() == db;

    }
}
