package com.github.martinfrank.catansettler.mapdata;

import com.github.martinfrank.catansettler.model.Settlement;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;

public class GameMapNodeData extends ResourceData {

    private Settlement settlement;

    public GameMapNodeData(ResourceImages resource) {
        super(resource);
    }

    public boolean hasSettlement() {
        return settlement != null;
    }

    public Settlement getSettlement() {
        return settlement;
    }

    public void setSettlement(Settlement settlement) {
        this.settlement = settlement;
    }

    public void clear() {
        settlement = null;
    }
}
