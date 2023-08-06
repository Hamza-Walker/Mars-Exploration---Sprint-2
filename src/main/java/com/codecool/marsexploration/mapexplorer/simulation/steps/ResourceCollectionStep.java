package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;

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
        Coordinate end = context.getFoundResources().get(0).getCoordinate();
        // Use A* algorithm to find the path to the first monitored resource coordinate
        List<Coordinate> path = AStarPathFinder.findPath(context.getIntegerMap(), roverPosition, end );

        if (path == null) {
            System.out.println("Unable to find a path to the resource.");
        } else {
            System.out.println("Path to resources:");
            for (Coordinate coordinate : path) {
                System.out.println(coordinate);
            }

            // Update rover's current position to the resource coordinate
            rover.setCurrentPosition(path.get(path.size() - 1));
            context.setRover(rover);

            // Remove the collected resource from monitored resources
            Coordinate collectedResource = monitoredResourceCoordinates.get(0);
            monitoredResourceCoordinates.remove(collectedResource);

            // Print collected resource message
            System.out.println("Resource successfully collected at: " + collectedResource);
        }
    }
}
