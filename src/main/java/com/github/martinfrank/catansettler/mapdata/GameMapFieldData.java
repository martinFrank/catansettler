package com.github.martinfrank.catansettler.mapdata;

import com.github.martinfrank.catansettler.model.FieldResource;
import com.github.martinfrank.catansettler.resource.image.ResourceImages;

public class GameMapFieldData extends ResourceData {

    private FieldResource fieldResource;
    private int fieldDice;

    public GameMapFieldData(ResourceImages resource) {
        super(resource);
        fieldResource = FieldResource.EMPTY;
    }

    public void setFieldResource(FieldResource fieldResource) {
        this.fieldResource = fieldResource;
    }

    public FieldResource getFieldResource(){
        return fieldResource;
    }

    public void setFieldDice(Integer value) {
        fieldDice = value;
    }

    public int getFieldDice() {
        return fieldDice;
    }

    public void clear() {
        fieldResource = FieldResource.EMPTY;
        fieldDice = 0;
    }
}
