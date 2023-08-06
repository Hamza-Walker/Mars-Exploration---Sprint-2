package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class MapLoaderImplTest {

    private MapLoaderImpl mapLoader;

    @BeforeEach
    public void setUp() {
        mapLoader = new MapLoaderImpl();
    }

    @Test
    public void testLoadValidMapFile() {
        String mapFile = "src/test/rescources/exploration-0.map";
        Map map = mapLoader.load(mapFile);

        assertNotNull(map);
        assertFalse(map.successfullyGenerated());

        String[][] expectedMapData = {
                {"%", " ", "#", " ", "#", " "},
                {"*", "&", " ", "#", "#", " "},
                {" ", " ", "*", " ", " ", " "},
                {" ", " ", "#", "#", " ", " "},
                {"#", "#", "#", "#", " ", " "},
                {" ", " ", " ", " ", "&", " "}
        };

        assertArrayEquals(expectedMapData, map.getRepresentation());
    }

    @Test
    public void testLoadNonExistentMapFile() {
        String mapFile = "src/test/resources/nonexistent_map.map";
        Map map = mapLoader.load(mapFile);

        assertNull(map);
    }

    @Test
    public void testLoadEmptyMapFile() {
        String mapFile = "src/test/resources/empty_map.map";
        Map map = mapLoader.load(mapFile);

        assertNull(map);
    }

    @Test
    public void testLoadInvalidMapFile() {
        String mapFile = "src/test/resources/invalid_map.map";
        Map map = mapLoader.load(mapFile);

        assertNull(map);
    }
}