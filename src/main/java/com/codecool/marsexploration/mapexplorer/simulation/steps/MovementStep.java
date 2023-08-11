package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;

import java.util.*;

public class MovementStep implements SimulationStep {

    @Override
    public void execute(SimulationContext context) {

        int numberOfSteps = context.getNumberOfSteps();
        IntegerMap integerMap =context.getIntegerMap();
        Rover rover = context.getRover();
        RoverPlacer roverPlacer = new RoverPlacer();
        List<Coordinate> monitoredResourceCoordinates = context.getMonitoredResourceCoordinate();
        Coordinate currentCoordinate = rover.getCurrentPosition();

        Queue <Coordinate> explorationQueue = new LinkedList<>();
        explorationQueue.offer(rover.getCurrentPosition());

        if (!monitoredResourceCoordinates.isEmpty()) {

            List<Coordinate> path = AStarPathFinder.findPath(integerMap, rover.getCurrentPosition(), new Coordinate(0,20));
            System.out.println(path);
        }
        if (rover.getCurrentPosition() != null) {
            exploreAdjacentSpots(explorationQueue, integerMap, currentCoordinate);
            // System.out.println(" surrounding spots : " + explorationQueue);

            // Move the rover and clear the queue if the rover is placed
            moveRoverAndClearQueue(rover, explorationQueue);
        }


    }
    private void moveRoverAndClearQueue(Rover rover, Queue<Coordinate> explorationQueue) {
        Coordinate newPosition = getRandomElementFromQueue(explorationQueue);
        if (newPosition != null) {
            rover.setCurrentPosition(newPosition);
            explorationQueue.clear();
        }
    }
    private Coordinate getRandomElementFromQueue(Queue<Coordinate> queue) {

        int randomIndex = new Random().nextInt(queue.size());
        Coordinate Randomcoordinate = null;

        for (Coordinate coordinate : queue) {
            if (randomIndex == 0) {
               // System.out.println(" rover moved to : " + coordinate);
                Randomcoordinate = coordinate;
            }
            randomIndex--;

        }
        return Randomcoordinate;

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
