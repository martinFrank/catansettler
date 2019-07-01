package com.github.martinfrank.catansettler.ai;

import com.github.martinfrank.catansettler.map.GameMapEdge;
import com.github.martinfrank.catansettler.model.FieldResource;
import com.github.martinfrank.catansettler.model.Road;

import java.util.List;

public class RoadPlacingValue extends PlacingValue<GameMapEdge> {

    public RoadPlacingValue(GameMapEdge edge, int value, List<FieldResource> resources) {
        super(edge, value, resources);
    }
}
