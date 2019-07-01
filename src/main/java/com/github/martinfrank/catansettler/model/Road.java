package com.github.martinfrank.catansettler.model;

import com.github.martinfrank.catansettler.Player;
import com.github.martinfrank.catansettler.map.GameMapEdge;

public class Road{

    private GameMapEdge edge;
    private final Player owner;

    public Road(Player owner) {
        this.owner = owner;
    }


    public void setLocation(GameMapEdge edge) {
        this.edge = edge;
    }

    public GameMapEdge getLocation() {
        return edge;
    }

    public Player getOwner(){
        return owner;
    }
}
