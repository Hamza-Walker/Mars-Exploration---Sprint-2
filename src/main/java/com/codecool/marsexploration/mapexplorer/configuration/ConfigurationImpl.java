package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.Set;

public class ConfigurationImpl implements Configuration {
    private int numberOfSteps;
    private int timeOut;
    private int roverID;
    private Coordinate landingSpot;
    private int sightRange;
    private String mapPath;
    private Set<Integer> resources;

    public ConfigurationImpl(int numberOfSteps, int timeOut, int roverID, Coordinate landingSpot, int sightRange, String mapPath, Set<Integer> resources) {
        this.numberOfSteps = numberOfSteps;
        this.timeOut = timeOut;
        this.roverID = roverID;
        this.landingSpot = landingSpot;
        this.sightRange = sightRange;
        this.mapPath = mapPath;
        this.resources = resources;
    }

    @Override
    public int numberOfSteps() {
        return numberOfSteps;
    }

    @Override
    public int timeOut() {
        return timeOut;
    }

    @Override
    public int roverID() {
        return roverID;
    }

    @Override
    public Coordinate landingSpot() {
        return landingSpot;
    }

    @Override
    public int sightRange() {
        return sightRange;
    }

    @Override
    public String mapPath() {
        return mapPath;
    }

    @Override
    public Set<Integer> resources() {
        return resources;
    }
}
