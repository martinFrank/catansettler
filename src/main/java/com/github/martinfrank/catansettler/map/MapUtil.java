package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.maplib.MapStyle;

public class MapUtil {

    private MapUtil(){

    }

    public static GameMap createMap(ResourceImages resourceImages) {
        GameMapFactory factory = new GameMapFactory(resourceImages);
        GameMap map = factory.createMap(7,8, MapStyle.HEX_HORIZONTAL);
        map.scale(24);

        Shape aField = map.getFields().get(0).getShape();
        double fieldHeight = aField.getTransformed().getHeight();
        map.pan(0, -fieldHeight/2);
        return map;
    }
}
