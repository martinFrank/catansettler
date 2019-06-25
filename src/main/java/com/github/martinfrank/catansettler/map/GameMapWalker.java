package com.github.martinfrank.catansettler.map;

import com.github.martinfrank.maplib.MapWalker;

import java.util.List;

public class GameMapWalker extends MapWalker<GameMapField, GameMapEdge, GameMapNode> {

    @Override
    public boolean canEnter(GameMapField from, GameMapField into) {
        return true;
    }

    @Override
    public int getEnterCosts(GameMapField from, GameMapField into) {
        return 10;
    }

    @Override
    public List<GameMapField> getNeighbours(GameMapField field) {
        return getNeighboursFromEdges(field);
    }
}
