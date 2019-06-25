package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import com.github.martinfrank.maplib.MapPartFactory;
import com.github.martinfrank.maplib.MapStyle;
import com.github.martinfrank.catansettler.mapdata.GameMapData;
import com.github.martinfrank.catansettler.mapdata.GameMapEdgeData;
import com.github.martinfrank.catansettler.mapdata.GameMapFieldData;
import com.github.martinfrank.catansettler.mapdata.GameMapNodeData;

public class GameMapPartFactory extends MapPartFactory<GameMap, GameMapField, GameMapEdge, GameMapNode, GameMapWalker> {

    private final ResourceImages resource;

    public GameMapPartFactory(ResourceImages resource) {
        super();
        this.resource = resource;
    }

    @Override
    public GameMapNode createMapNode() {
        return new GameMapNode(new GameMapNodeData(resource));
    }

    @Override
    public GameMapEdge createMapEdge() {
        return new GameMapEdge(new GameMapEdgeData(resource));
    }

    @Override
    public GameMapField createMapField() {
        return new GameMapField(new GameMapFieldData(resource));
    }

    @Override
    public GameMap createMap(int columns, int rows, MapStyle style) {
        return new GameMap(columns, rows, style, new GameMapData(resource));
    }

    @Override
    public GameMapWalker createWalker() {
        return new GameMapWalker();
    }
}
