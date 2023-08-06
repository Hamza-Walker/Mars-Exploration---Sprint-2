package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.Set;

public interface Configuration {
    int numberOfSteps();
    int timeOut();
    int roverID();
    Coordinate landingSpot();
    int sightRange();
    String mapPath();
    Set<Integer> resources();
}