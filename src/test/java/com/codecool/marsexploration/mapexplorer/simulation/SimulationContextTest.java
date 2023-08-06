/*
package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimulationContextTest {

    @Test
    public void testSimulationContext() {
        // Create test data
        int numberOfSteps = 100;
        int stepsToTimeout = 50;
        Rover rover = new Rover("rover-1", 2, 3, 3); // Update this line
        Coordinate spaceshipLocation = new Coordinate(0, 0);
        String[][] mapData = {
                {"%", " ", "#"},
                {"*", "&", " "},
                {" ", " ", "*"},
        };
        Map map = new Map(mapData, true);
        Set<String> monitoredResources = new HashSet<>();
        monitoredResources.add("&");
        ExplorationOutcome explorationOutcome = ExplorationOutcome.COLONIZABLE;

        // Create a SimulationContext object
        SimulationContext context = new SimulationContext(
                numberOfSteps,
                stepsToTimeout,
                rover,
                spaceshipLocation,
                IntegerMap,
                monitoredResources,
                explorationOutcome
        );

        // Test the fields using getter methods
        assertEquals(numberOfSteps, context.getNumberOfSteps());
        assertEquals(stepsToTimeout, context.getStepsToTimeout());
        assertEquals(rover, context.getRover());
        assertEquals(spaceshipLocation, context.getSpaceshipLocation());
        assertEquals(map, context.getMap());
        assertEquals(monitoredResources, context.getMonitoredResources());
        assertEquals(explorationOutcome, context.getExplorationOutcome());
    }
}
*/
