package com.codecool.marsexploration.mapexplorer.simulation.steps.aStarPathFinder;

import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.simulation.steps.AStarPathFinder;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AStarPathFinderTest {
    String workDir = "src/main";
    String mapFile = workDir + "/resources/exploration-2.map";
    MapLoader mapLoader = new MapLoaderImpl();
    IntegerMap integerMap = mapLoader.createIntegerMap(mapFile);

    @Test
    public void testFindPath() {

        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(6, 16);

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
