package com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder;

import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.PathFinder;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class PathFinderTest {

    @Test
    void testFindNeighbors() {
        int[][] map = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        IntegerMap integerMap = new IntegerMap(map);
        Point point = new Point(1, 1, null);
        List<Point> neighbors = PathFinder.FindNeighbors(integerMap, point);
        Assertions.assertEquals(4, neighbors.size());
    }

    @Test
    void testFindPath() {
        int[][] map = {
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 0, 1}
        };
        IntegerMap integerMap = new IntegerMap(map);
        Point start = new Point(0, 0, null);
        Point end = new Point(3, 4, null);
        List<Point> path = PathFinder.FindPath(integerMap, start, end);
        Assertions.assertNotNull(path);
        Assertions.assertFalse(path.isEmpty());
        Assertions.assertEquals(8, path.size()); // Assuming there's a specific path in your sample data
    }
}
