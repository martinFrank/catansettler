package com.github.martinfrank.catansettler.mapdata;

import com.github.martinfrank.catansettler.resource.image.ResourceImages;

public class ResourceData {

    private final ResourceImages resource;

    public ResourceData(ResourceImages resource) {
        this.resource = resource;
    }

    public ResourceImages getResource(){
        return resource;
    }
}
