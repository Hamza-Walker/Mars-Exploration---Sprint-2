package com.codecool.marsexploration.mapexplorer.simulation.steps.aStarPathFinder;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.steps.aStarPathFinder.AStarPathFinder;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AStarPathFinderTest {

    @Test
    public void testFindPath() {
        int[][] matrix = {
                {1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1},
                {1, 0, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 0, 1, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1}
        };

        IntegerMap integerMap = new IntegerMap(matrix);

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(6, 6);

        List<Coordinate> path = AStarPathFinder.findPath(integerMap, start, end);
        int resource = integerMap.getValue(end.getX(), end.getY());
        System.out.println("Path route " + path);
        System.out.println(" value of end point " + resource);
        assertNotNull(path);
        assertTrue(path.size() > 0);

        assertEquals(start, path.get(0));
        assertEquals(end, path.get(path.size() - 1));
    }
}
