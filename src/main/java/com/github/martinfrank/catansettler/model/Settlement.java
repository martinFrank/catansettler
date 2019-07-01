package com.github.martinfrank.catansettler.model;

import com.github.martinfrank.catansettler.Player;
import com.github.martinfrank.catansettler.map.GameMapNode;

public class Settlement {

    private GameMapNode location;
    private final Player owner;

    public Settlement(Player owner) {
        this.owner = owner;
    }

    public GameMapNode getLocation(){
        return location;
    }

    public void setLocation(GameMapNode node) {
        this.location = node;
    }

    public Player getOwner() {
        return owner;
    }
}
