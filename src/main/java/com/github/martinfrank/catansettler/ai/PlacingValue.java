package com.github.martinfrank.catansettler.ai;

import com.github.martinfrank.catansettler.map.GameMapNode;
import com.github.martinfrank.catansettler.model.FieldResource;

import java.util.Comparator;
import java.util.List;

public class PlacingValue<T> implements Comparator<PlacingValue>, Comparable<PlacingValue> {

    private final T t;
    private final int value;
    private final List<FieldResource> resources;

    public PlacingValue(T node, int value, List<FieldResource> resources) {
        this.t = node;
        this.value = value;
        this.resources = resources;
    }


    @Override
    public int compare(PlacingValue o1, PlacingValue o2) {
        return Integer.compare(o2.value, o1.value);
    }

    @Override
    public int compareTo(PlacingValue o) {
        return Integer.compare(o.value, value);
    }

    public T get() {
        return t;
    }
}
