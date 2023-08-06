package com.codecool.marsexploration.mapexplorer.maploader.model;


public class FoundResource {
    private final int resourceType;
    private final Coordinate coordinate;
    private final String name;

    public FoundResource(int resourceType, Coordinate coordinate, String name) {
        this.resourceType = resourceType;
        this.coordinate = coordinate;
        this.name = name;
    }

    public int getResourceType() {
        return resourceType;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "FoundResource{" +
                "resourceType=" + resourceType +
                ", coordinate=" + coordinate +
                ", name='" + name + '\'' +
                '}';
    }
}
