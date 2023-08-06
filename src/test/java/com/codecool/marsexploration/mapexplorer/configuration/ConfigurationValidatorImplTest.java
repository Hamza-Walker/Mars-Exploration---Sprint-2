package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationValidatorImplTest {
    private ConfigurationValidator configurationValidator;

    @BeforeEach
    public void setUp() {
        configurationValidator = new ConfigurationValidatorImpl();
    }

    @Test
    public void testIsLandingSpotValid_ValidSpot() {
        String[][] mapData = {
                {" ", " ", " "},
                {" ", null, " "},
                {" ", " ", " "}
        };
        Coordinate landingSpot = new Coordinate(1, 1);

        assertTrue(configurationValidator.isLandingSpotValid(landingSpot, mapData));
    }


    @Test
    public void testIsLandingSpotValid_NullSpot() {
        String[][] mapData = {
                {" ", " ", " "},
                {" ", null, " "},
                {" ", " ", " "}
        };
        Coordinate landingSpot = null;

        assertFalse(configurationValidator.isLandingSpotValid(landingSpot, mapData));
    }

    @Test
    public void testIsLandingSpotValid_OutOfBounds() {
        String[][] mapData = {
                {" ", " ", " "},
                {" ", null, " "},
                {" ", " ", " "}
        };
        Coordinate landingSpot = new Coordinate(3, 1); // Out of bounds

        assertFalse(configurationValidator.isLandingSpotValid(landingSpot, mapData));
    }

    @Test
    public void testHasEmptyAdjacentSpot_ValidSpot() {
        String[][] mapData = {
                {" ", " ", " "},
                {" ", null, " "},
                {" ", " ", " "}
        };
        Coordinate landingSpot = new Coordinate(0, 1);

        assertTrue(configurationValidator.hasEmptyAdjacentSpot(landingSpot, mapData));
    }

    @Test
    public void testHasEmptyAdjacentSpot_NullSpot() {
        String[][] mapData = {
                {" ", " ", " "},
                {" ", null, " "},
                {" ", " ", " "}
        };
        Coordinate landingSpot = null;

        assertFalse(configurationValidator.hasEmptyAdjacentSpot(landingSpot, mapData));
    }

    @Test
    public void testHasEmptyAdjacentSpot_NoEmptyAdjacentSpot() {
        String[][] mapData = {
                {"#", "#", "#"},
                {"#", null, "#"},
                {"#", "#", "#"}
        };
        Coordinate landingSpot = new Coordinate(1, 1); // Surrounded by obstacles

        assertFalse(configurationValidator.hasEmptyAdjacentSpot(landingSpot, mapData));
    }

    @Test
    public void testIsMapPathNotEmpty_ValidPath() {
        String mapPath = "src/test/resources/exploration-0.map";

        assertTrue(configurationValidator.isMapPathNotEmpty(mapPath));
    }

    @Test
    public void testIsMapPathNotEmpty_EmptyPath() {
        String mapPath = "";

        assertFalse(configurationValidator.isMapPathNotEmpty(mapPath));
    }

    @Test
    public void testAreResourcesSpecified_ValidResources() {
        String resources = "Oxygen,Water,Rock";

        assertTrue(configurationValidator.areResourcesSpecified(resources));
    }

    @Test
    public void testAreResourcesSpecified_NoResources() {
        String resources = "";

        assertFalse(configurationValidator.areResourcesSpecified(resources));
    }

    @Test
    public void testIsTimeoutValid_ValidTimeout() {
        int timeout = 100;

        assertTrue(configurationValidator.isTimeOutValid(timeout));
    }

    @Test
    public void testIsTimeoutValid_InvalidTimeout() {
        int timeout = -10;

        assertFalse(configurationValidator.isTimeOutValid(timeout));
    }
}