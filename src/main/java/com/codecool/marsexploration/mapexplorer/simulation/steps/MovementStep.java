package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;
import com.codecool.marsexploration.mapexplorer.simulation.steps.aStarPathFinder.AStarPathFinder;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MovementStep implements SimulationStep {

    private AStarPathFinder pathFinder = new AStarPathFinder();

    @Override
    public void execute(SimulationContext context) {
        IntegerMap integerMap = context.getIntegerMap();
        Rover rover = context.getRover();
        RoverPlacer roverPlacer = new RoverPlacer();
        // Get the number of steps from the context
        int numberOfSteps = context.getNumberOfSteps();

        // Initialize a queue for systematic exploration
        Queue<Coordinate> explorationQueue = new LinkedList<>();
        explorationQueue.offer(rover.getCurrentPosition());

        List<Coordinate> monitoredResourceCoordinates = context.getMonitoredResourceCoordinate();


        //System.out.println(explorationQueueList);
        // Iterate for the specified number of steps
        for (int step = 0; step < numberOfSteps; step++) {
            Coordinate currentCoordinate = explorationQueue.poll();
            System.out.println("CurrentCoordinate" + currentCoordinate);
            // Deploy rover to the current coordinate
            roverPlacer.deployRover(integerMap, rover, currentCoordinate);

            context.setRover(rover); //

            // Check if there are monitored resource coordinates
            if (monitoredResourceCoordinates.isEmpty()) {
                // Explore adjacent empty spots and add them to the queue
                if (currentCoordinate != null) {
                    exploreAdjacentSpots(explorationQueue, integerMap, currentCoordinate);
                }
            } else {
                // Find path to the first monitored resource
                List<Coordinate> pathToResource = pathFinder.findPath(integerMap, rover.getCurrentPosition(), monitoredResourceCoordinates.iterator().next());
                System.out.println("path to resource : " + pathToResource);
                if (pathToResource != null) {
                    // Create a copy of the rover for simulation purposes
                    Rover simulatedRover = rover;

                    // Follow the path to the resource
                    for (Coordinate pathCoordinate : pathToResource) {
                        // Deploy simulated rover to the path coordinate
                        roverPlacer.deployRover(integerMap, simulatedRover, pathCoordinate);

                        // Delay for a short period to simulate rover movement (optional)
                        try {
                            Thread.sleep(2); // Adjust the delay time as needed
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    // Update the actual rover's position to the last coordinate of the path
                    roverPlacer.deployRover(integerMap, rover, pathToResource.get(pathToResource.size() - 1));
                }

                // Remove the first coordinate from monitored resources
                monitoredResourceCoordinates.remove(monitoredResourceCoordinates.iterator().next());
            }


            // Delay for a short period to simulate rover movement (optional)
            try {
                Thread.sleep(2); // Adjust the delay time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // If exploration outcome is determined, the rover should be returning to the landing spot.
        // The logic for this part is not shown here as it depends on the actual implementation of the return routine.

        // Todo:
        // When a resource is monitored save the Coordinate [X]
        // use the aStar Algorithm to use the current location to get the resource and print a statement and put that in a new class
        // resource collection step
        // then fix the all star algorithm so that it receives the start, target and map to do its thing

        // Begin the return phase where the rover returns to the spaceShip

        // check how the ExplorationOutcome influences the program
        //
    }

    private void exploreAdjacentSpots(Queue<Coordinate> explorationQueue, IntegerMap integerMap, Coordinate coordinate) {

        int x = coordinate.getX();
        int y = coordinate.getY();
        RoverPlacer roverPlacer = new RoverPlacer();

        // Check adjacent spots and add empty ones to the queue

        if (roverPlacer.isEmptySpot(x - 1, y, integerMap)) {
            explorationQueue.offer(new Coordinate(x - 1, y));
        }
        if (roverPlacer.isEmptySpot(x + 1, y, integerMap)) {
            explorationQueue.offer(new Coordinate(x + 1, y));
        }
        if (roverPlacer.isEmptySpot(x, y - 1, integerMap)) {
            explorationQueue.offer(new Coordinate(x, y - 1));
        }
        if (roverPlacer.isEmptySpot(x, y + 1, integerMap)) {
            explorationQueue.offer(new Coordinate(x, y + 1));
        }
    }
}
