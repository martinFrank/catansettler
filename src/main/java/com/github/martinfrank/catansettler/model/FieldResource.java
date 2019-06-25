package com.github.martinfrank.catansettler.model;

import javafx.scene.image.Image;

public enum FieldResource {

    EMPTY("empty"), CLAY("hex_clay"), WOOD("hex_wood"), GRAIN("hex_grain"), SHEEP("hex_sheep"),
    ROCK("hex_rock"), DESERT("hex_desert"), WATER("hex_water");// HARBOUR_CLAY("hex_clay");

    private final String id;
    FieldResource(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public boolean isResource() {
        return this != EMPTY;
    }
}

