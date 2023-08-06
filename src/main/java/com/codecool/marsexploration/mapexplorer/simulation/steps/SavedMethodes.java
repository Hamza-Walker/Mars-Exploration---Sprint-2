package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.PathFinder;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.Point;


import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

public class SavedMethodes implements SimulationStep {

    @Override
    public void execute(SimulationContext context) {
        IntegerMap integerMap = context.getIntegerMap();
        Rover rover = context.getRover();
        Coordinate landingSpot = context.getSpaceshipLocation();
        RoverPlacer roverPlacer = new RoverPlacer();

        // Initialize a queue for systematic exploration
        Queue<Coordinate> explorationQueue = new LinkedList<>();
        explorationQueue.offer(rover.getCurrentPosition());

        // Continue exploration until the queue is empty
        while (!explorationQueue.isEmpty()) {
            Coordinate currentCoordinate = explorationQueue.poll();

            // Deploy rover to the current coordinate
            roverPlacer.deployRover(integerMap, rover, currentCoordinate);
            context.setRover(rover);

            // Explore adjacent empty spots and add them to the queue
            exploreAdjacentSpots(explorationQueue, integerMap, currentCoordinate);

            // Delay for a short period to simulate rover movement (optional)
            try {
                Thread.sleep(100); // Adjust the delay time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // If exploration outcome is determined, the rover should be returning to the landing spot.
        // The logic for this part is not shown here as it depends on the actual implementation of the return routine.

        // Todo:
        // So far the rover is stuck in the Movement step and is aimlessly looking for positions to move until it runs out
        // check how the ExplorationOutcome influences the program
        // then check how to deal with the rovers movements and what algorithm to implement
        // then check how to collect resources as you explore the map
        // then check how to return the spaceship and overcome the obstacles
        //
    }

    private void exploreAdjacentSpots(Queue<Coordinate> explorationQueue, IntegerMap integerMap, Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        // Check adjacent spots and add empty ones to the queue
        if (isEmptySpot(x - 1, y, integerMap)) {
            explorationQueue.offer(new Coordinate(x - 1, y));
        }
        if (isEmptySpot(x + 1, y, integerMap)) {
            explorationQueue.offer(new Coordinate(x + 1, y));
        }
        if (isEmptySpot(x, y - 1, integerMap)) {
            explorationQueue.offer(new Coordinate(x, y - 1));
        }
        if (isEmptySpot(x, y + 1, integerMap)) {
            explorationQueue.offer(new Coordinate(x, y + 1));
        }
    }

    private boolean isEmptySpot(int x, int y, IntegerMap integerMap) {
        return x >= 0 && x < integerMap.getDimension() &&
                y >= 0 && y < integerMap.getDimension() &&
                integerMap.getValue(x, y) == 0;
    }



    private int[][] generatePathMap(IntegerMap integerMap, Coordinate start, Coordinate end) {
        int[][] targetPosition = new int[integerMap.getDimension()][integerMap.getDimension()];

        // Convert the coordinates to Point objects for the BFS algorithm.
        Point startPoint = new Point(start.getX(), start.getY(), null);
        Point endPoint = new Point(end.getX(), end.getY(), null);

        List<Point> path = PathFinder.FindPath(integerMap, startPoint, endPoint);

        System.out.println("Path: " + path); // Add this print statement to check the path value.

        // Mark the path on the targetPosition map for return routine.
        if (path != null) {
            for (Point point : path) {
                targetPosition[point.y][point.x] = 1;
            }
        }

        return targetPosition;
    }

}
