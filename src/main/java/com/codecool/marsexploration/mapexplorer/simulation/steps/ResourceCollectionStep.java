package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;
import com.codecool.marsexploration.mapexplorer.simulation.steps.aStarPathFinder.AStarPathFinder;

import java.util.List;

public class ResourceCollectionStep implements SimulationStep {

    @Override
    public void execute(SimulationContext context) {
        Rover rover = context.getRover();
        Coordinate roverPosition = rover.getCurrentPosition();

        // Get the monitored resource coordinates
        List<Coordinate> monitoredResourceCoordinates = context.getMonitoredResourceCoordinate();

        if (monitoredResourceCoordinates.isEmpty()) {
            System.out.println("No monitored resources available.");
            return;
        }

        // Use A* algorithm to find the path to the first monitored resource coordinate
        AStarPathFinder pathFinder = new AStarPathFinder();
        List<Coordinate> path = pathFinder.findPath(context.getIntegerMap(), roverPosition, monitoredResourceCoordinates.get(0));

        if (path == null) {
            System.out.println("Unable to find a path to the resource.");
        } else {
            System.out.println("Path to resources:");
            for (Coordinate coordinate : path) {
                System.out.println(coordinate);
            }
        }

        // Update rover's current position to the resource coordinate
        if (!path.isEmpty()) {
            rover.setCurrentPosition(path.get(path.size() - 1));
            context.setRover(rover);
        }
    }
}
