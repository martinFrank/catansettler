package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.maplib.Map;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.catansettler.mapdata.GameMapData;

public class GameMap extends Map<GameMapData, GameMapField, GameMapEdge, GameMapNode, GameMapWalker> {


    public GameMap(int width, int height, MapStyle style, GameMapData mapData) {
        super(width, height, style, mapData);
    }

    @Override
    public void draw(Object drawContext) {
        getFields().forEach(f -> f.draw(drawContext));
    }

}
