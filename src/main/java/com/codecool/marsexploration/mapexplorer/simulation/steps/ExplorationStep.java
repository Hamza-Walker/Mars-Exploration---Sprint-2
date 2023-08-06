package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class ExplorationStep implements SimulationStep {
    private static final int RESOURCE_CODE = 2;

    @Override
    public void execute(SimulationContext context) {
        IntegerMap integerMap = context.getIntegerMap();
        Rover rover = context.getRover();
        RoverPlacer roverPlacer = new RoverPlacer();

        // Initialize a queue for systematic exploration
        Queue<Coordinate> explorationQueue = new LinkedList<>();
        explorationQueue.offer(rover.getCurrentPosition());

        // Initialize a set to keep track of visited coordinates
        Set<Coordinate> visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(rover.getCurrentPosition());

        boolean allResourcesCollected = false;

        while (!explorationQueue.isEmpty() && !allResourcesCollected) {
            Coordinate currentCoordinate = explorationQueue.poll();

            // Deploy rover to the current coordinate
            roverPlacer.deployRover(integerMap, rover, currentCoordinate);
            context.setRover(rover);

            // Explore adjacent empty spots and add them to the queue
            exploreAdjacentSpots(explorationQueue, integerMap, visitedCoordinates, currentCoordinate);

            // Check if the current coordinate has a resource
            if (integerMap.getValue(currentCoordinate.getX(), currentCoordinate.getY()) == RESOURCE_CODE) {
                System.out.println("Resource found at: " + currentCoordinate); // Debug print
                collectResource(context, currentCoordinate, integerMap); // Collect the resource
                if (context.getMonitoredResources().isEmpty()) {
                    allResourcesCollected = true;
                }
            } else {
                System.out.println("No resource at: " + currentCoordinate); // Debug print
            }

            // Delay for a short period to simulate rover movement (optional)
            try {
                Thread.sleep(100); // Adjust the delay time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Set the exploration outcome to complete
        context.setExplorationOutcome(ExplorationOutcome.COMPLETE);
    }


    private void exploreAdjacentSpots(Queue<Coordinate> explorationQueue, IntegerMap integerMap, Set<Coordinate> visitedCoordinates, Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        // Check adjacent spots and add unvisited, empty ones to the queue
        if (isValidCoordinate(x - 1, y, integerMap) && !visitedCoordinates.contains(new Coordinate(x - 1, y)) && integerMap.getValue(x - 1, y) == 0) {
            visitedCoordinates.add(new Coordinate(x - 1, y));
            explorationQueue.offer(new Coordinate(x - 1, y));
        }
        // Repeat for other directions...

        // Check adjacent spots and add unvisited, resource ones to the queue
        if (isValidCoordinate(x - 1, y, integerMap) && integerMap.getValue(x - 1, y) == RESOURCE_CODE) {
            visitedCoordinates.add(new Coordinate(x - 1, y));
            explorationQueue.offer(new Coordinate(x - 1, y));
        }
        // Repeat for other directions...
    }




    private boolean isValidCoordinate(int x, int y, IntegerMap integerMap) {
        int dimension = integerMap.getDimension();
        return x >= 0 && x < dimension && y >= 0 && y < dimension;
    }

    private void collectResource(SimulationContext context, Coordinate coordinate, IntegerMap integerMap) {
        context.getMonitoredResources().remove(integerMap.getValue(coordinate.getX(), coordinate.getY()));
    }

}
