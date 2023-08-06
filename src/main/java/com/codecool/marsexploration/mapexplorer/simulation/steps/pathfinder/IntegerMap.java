package com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.Arrays;

public class IntegerMap {
    private int[][] representation;

    public IntegerMap(int[][] representation) {
        this.representation = representation;
    }

    public int getValue(int x, int y) {
        return representation[y][x];
    }

    public int getDimension() {
        return representation.length;
    }

    public boolean isEmpty(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        return x >= 0 && x < representation.length &&
                y >= 0 && y < representation[x].length &&
                representation[y][x] == 0;
    }

    public  Coordinate getSpaceshipCoordinate() {
        for (int x = 0; x < representation.length; x++) {
            for (int y = 0; y < representation[x].length; y++) {
                if (representation[y][x] == 1) {
                    return new Coordinate(x, y);
                }
            }
        }
        return null; // No spaceship coordinate found
    }

    public void setValue(int x, int y, int value) {
        representation[y][x] = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int[] row : representation) {
            sb.append(Arrays.toString(row)).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
