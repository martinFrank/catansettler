package com.github.martinfrank.catansettler.ai;

import com.github.martinfrank.catansettler.map.GameMapNode;
import com.github.martinfrank.catansettler.model.FieldResource;

import java.util.List;

public class SettlementPlacingValue extends PlacingValue<GameMapNode> {

    public SettlementPlacingValue(GameMapNode node, int value, List<FieldResource> resources) {
        super(node, value, resources);
    }
}
