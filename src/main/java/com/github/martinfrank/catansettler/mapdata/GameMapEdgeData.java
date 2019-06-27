package com.github.martinfrank.catansettler.mapdata;

import com.github.martinfrank.catansettler.model.Road;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;

public class GameMapEdgeData extends ResourceData {

    private Road road;

    public GameMapEdgeData(ResourceImages resource) {
        super(resource);
    }

    public void clear() {
        road = null;
    }

    public boolean hasRoad() {
        return road != null;
    }

    public Road getRoad() {
        return road;
    }

    public void setRoad(Road road) {
        this.road = road;
    }
}
