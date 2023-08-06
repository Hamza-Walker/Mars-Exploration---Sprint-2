package com.codecool.marsexploration.mapexplorer.rovers;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RoverPlacerTest {

    private RoverPlacer roverPlacer;
    private IntegerMap integerMap;

    @BeforeEach
    public void setUp() {
        // Create an IntegerMap with some test data for the unit tests
        int[][] data = {
                {1, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };
        integerMap = new IntegerMap(data);
        roverPlacer = new RoverPlacer();
    }

    @Test
    public void testFindEmptyAdjacentSpot() {
        Coordinate coordinate = new Coordinate(0, 1);

        // The expected empty adjacent spot is (1, 1)
        Coordinate expected = new Coordinate(1, 1);
        Coordinate result = roverPlacer.findEmptyAdjacentSpot(integerMap, coordinate);
        Assertions.assertEquals(expected, result, "Incorrect empty adjacent spot found.");
    }

    @Test
    public void testDeployRover() {
        Coordinate coordinate = new Coordinate(0, 2);
        Rover rover = new Rover(1,  0, 2, 1);

        // The rover should be deployed to the empty spot (2, 0)
        Coordinate expectedPosition = new Coordinate(2, 0);
        roverPlacer.deployRover(integerMap, rover, coordinate);
        Assertions.assertEquals(expectedPosition, rover.getCurrentPosition(), "Rover not deployed to the correct spot.");
    }
}
