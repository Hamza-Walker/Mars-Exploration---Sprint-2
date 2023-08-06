package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoverPlacer {
    public void deployRover(IntegerMap integerMap, Rover rover, Coordinate emptyAdjacentSpot) {
        if (emptyAdjacentSpot == null) {
            System.out.println("No empty adjacent spot found, unable to deploy the rover");
            return;
        }

        int x = emptyAdjacentSpot.getX();
        int y = emptyAdjacentSpot.getY();

        // Swap the x and y values to set the rover's position correctly
        rover.setCurrentPosition(new Coordinate(y, x));
        integerMap.setValue(x, y, rover.getId());

    }

    public Coordinate findEmptyAdjacentSpot(IntegerMap integerMap, Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        if (isValidCoordinate(x - 1, y, integerMap) && isEmptySpot(x - 1, y, integerMap)) {
            return new Coordinate(x - 1, y);
        } else if (isValidCoordinate(x + 1, y, integerMap) && isEmptySpot(x + 1, y, integerMap)) {
            return new Coordinate(x + 1, y);
        } else if (isValidCoordinate(x, y - 1, integerMap) && isEmptySpot(x, y - 1, integerMap)) {
            return new Coordinate(x, y - 1);
        } else if (isValidCoordinate(x, y + 1, integerMap) && isEmptySpot(x, y + 1, integerMap)) {
            return new Coordinate(x, y + 1);
        }

        return null;
    }

    private boolean isValidCoordinate(int x, int y, IntegerMap integerMap) {
        int dimension = integerMap.getDimension();
        return x >= 0 && x < dimension && y >= 0 && y < dimension;
    }

    public Coordinate getRandomEmptyAdjacentSpot(IntegerMap integerMap, Coordinate coordinate) {
        List<Coordinate> emptyAdjacentSpots = new ArrayList<>();
        int x = coordinate.getX();
        int y = coordinate.getY();

        if (isEmptySpot(x - 1, y, integerMap)) {
            emptyAdjacentSpots.add(new Coordinate(x - 1, y));
        }
        if (isEmptySpot(x + 1, y, integerMap)) {
            emptyAdjacentSpots.add(new Coordinate(x + 1, y));
        }
        if (isEmptySpot(x, y - 1, integerMap)) {
            emptyAdjacentSpots.add(new Coordinate(x, y - 1));
        }
        if (isEmptySpot(x, y + 1, integerMap)) {
            emptyAdjacentSpots.add(new Coordinate(x, y + 1));
        }

        if (!emptyAdjacentSpots.isEmpty()) {
            Random random = new Random();
            int randomIndex = random.nextInt(emptyAdjacentSpots.size());
            Coordinate chosenSpot = emptyAdjacentSpots.get(randomIndex);
            System.out.println("Current Position: " + coordinate);
            System.out.println("Chosen Destination: " + chosenSpot);
            return chosenSpot;
        }

        return null;
    }

    public boolean isEmptySpot(int x, int y, IntegerMap integerMap) {
        return x >= 0 && x < integerMap.getDimension() &&
                y >= 0 && y < integerMap.getDimension() &&
                integerMap.getValue(x, y) == 0;
    }
}
