package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.Point;

import java.util.ArrayList;
import java.util.List;

public class Rover {
    private int id;
    private Coordinate currentPosition;
    private int sight;
    private final List<Coordinate> resourceCoordinates;
    private int[][] currentPossition1;

    public Rover(int id, int x, int y, int sight) {
            this.id = id;
        this.currentPosition = new Coordinate(x, y);
        this.sight = sight;
        this.resourceCoordinates = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }


    public int getSightRange() {
        return sight;
    }

    public List<Coordinate> getResourceCoordinates() {
        return resourceCoordinates;
    }

    public void addResourceCoordinate(Coordinate resourceCoordinate) {
        this.resourceCoordinates.add(resourceCoordinate);
        // this keyword is used to differentiate between instance variables and method parameters with the same name.
    }

    @Override
    public String toString() {
        return "Rover{" +
                "id='" + id + '\'' +
                ", currentPosition=" + currentPosition +
                ", sight=" + sight +
                ", resourceCoordinates=" + resourceCoordinates +
                '}';
    }

}
