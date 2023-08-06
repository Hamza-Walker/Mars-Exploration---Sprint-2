package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SimulationContext {
    private int numberOfSteps;
    private int stepsToTimeout;
    private int step; // Add the step variable
    private Rover rover;
    private Coordinate spaceshipLocation;
    private Map map;
    private IntegerMap integerMap; // Add the IntegerMap property
    private Set<Integer> monitoredResources;
    private ExplorationOutcome explorationOutcome;
    public List <Coordinate> monitoredResourceCoordinate = new ArrayList<>();


    public SimulationContext(
            int numberOfSteps,
            int stepsToTimeout,
            Rover rover,
            Coordinate spaceshipLocation,
            IntegerMap integerMap, // Add the IntegerMap parameter
            Set<Integer> monitoredResources,
            ExplorationOutcome explorationOutcome
    ) {
        this.numberOfSteps = numberOfSteps;
        this.stepsToTimeout = stepsToTimeout;
        this.step = 0; // Set the initial step to 0
        this.rover = rover;
        this.spaceshipLocation = spaceshipLocation;
        this.map = map;
        this.integerMap = integerMap; // Assign the IntegerMap object
        this.monitoredResources = monitoredResources;
        this.explorationOutcome = explorationOutcome;
    }

    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public int getStepsToTimeout() {
        return stepsToTimeout;
    }

    public int getStep() {
        return step;
    }
    public List<Coordinate> getMonitoredResourceCoordinate() {
        return monitoredResourceCoordinate;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Rover getRover() {
        return rover;
    }

    public Coordinate getSpaceshipLocation() {
        return spaceshipLocation;
    }

    public Map getMap() {
        return map;
    }

    public IntegerMap getIntegerMap() {
        return integerMap; // Add a getter for IntegerMap
    }

    public Set<Integer> getMonitoredResources() {
        return monitoredResources;
    }

    public ExplorationOutcome getExplorationOutcome() {
        return explorationOutcome;
    }

    public void setRover(Rover rover) {
        this.rover = rover;
    }

    public void setMonitoredResources(Set<Integer> monitoredResources) {
        this.monitoredResources = monitoredResources;
    }

    public void setExplorationOutcome(ExplorationOutcome explorationOutcome) {
        this.explorationOutcome = explorationOutcome;
    }
}
