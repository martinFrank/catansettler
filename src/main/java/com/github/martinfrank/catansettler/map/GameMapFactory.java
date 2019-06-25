package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.catansettler.resource.image.ResourceImages;
import com.github.martinfrank.maplib.MapFactory;

public class GameMapFactory extends MapFactory<GameMap, GameMapField, GameMapEdge, GameMapNode, GameMapWalker> {

    public GameMapFactory(ResourceImages resource) {
        super(new GameMapPartFactory(resource));
    }
}
