package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;


import java.util.HashSet;
import java.util.Set;

public class AnalysisStep implements SimulationStep {

    @Override
    public void execute(SimulationContext context) {
        // Implement the logic for the Analysis step here
        // For example, analyze the resources collected by the rover and update the exploration outcome in the context.

        Rover rover = context.getRover();
        Map map = context.getMap();

        Set<String> collectedResources = new HashSet<>();
        Set<Integer> monitoredResources = context.getMonitoredResources();

        for (Coordinate coordinate : rover.getResourceCoordinates()) {
            String resourceSymbol = map.getByCoordinate(coordinate);
            if (resourceSymbol != null && !resourceSymbol.trim().isEmpty() && !resourceSymbol.equals(" ")) {
                collectedResources.add(resourceSymbol);
            }
        }

        // Check if all monitored resources are collected by the rover
        if (collectedResources.containsAll(monitoredResources)) {
            // All resources have been collected, update the exploration outcome to SUCCESS
            context.setExplorationOutcome(ExplorationOutcome.COLONIZABLE);
        } else {
            // Not all resources have been collected, update the exploration outcome to INCOMPLETE
            context.setExplorationOutcome(ExplorationOutcome.INCOMPLETE);
        }
    }
}
