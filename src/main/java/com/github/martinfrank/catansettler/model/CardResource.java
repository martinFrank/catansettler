package com.github.martinfrank.catansettler.model;

public enum CardResource {

    EMPTY("empty"), CLAY("card_clay"), WOOD("card_wood"), GRAIN("card_grain"), SHEEP("card_sheep"),
    ROCK("card_rock"), PROGRESS("card_progress"), WATER("card_water");// HARBOUR_CLAY("card_clay");

    private final String id;
    CardResource(String id){
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public boolean isResource() {
        return this != EMPTY;
    }
}

