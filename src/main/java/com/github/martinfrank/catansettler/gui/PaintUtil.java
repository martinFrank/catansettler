package com.github.martinfrank.catansettler.gui;

import javafx.scene.paint.Color;

public class PaintUtil {

    public static Color fromRgb(int col) {
        int r = (0xFF0000 & col) >>16;
        int g = (0xFF00 & col) >>8;
        int b = (0xFF & col) ;

        return Color.rgb(r,g,b);
    }

    public static int toRgb(Color col) {
        int r = (int)(col.getRed()*256D) << 16;
        int g = (int)(col.getGreen()*256D) << 8;
        int b = (int)(col.getBlue()*256D);
        return r | g | b;
    }
}
