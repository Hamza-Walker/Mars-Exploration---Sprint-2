package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExplorationStepTest {

    @Test
    void execute_ExplorationOutcomeComplete_AllResourcesCollected() {
        // Create a mock IntegerMap with a 2D array of integers
        int[][] mapArray = {
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 2, 0, 0},
                {0, 0, 0, 2, 0},
                {0, 0, 0, 0, 0}
        };
        IntegerMap integerMap = new IntegerMap(mapArray);
        // Set resource at specific coordinates
        integerMap.setValue(2, 2, 2);
        integerMap.setValue(3, 3, 2);

        // Create a rover and set its initial position
        Coordinate startingPosition = new Coordinate(1, 1);
        Rover rover = new Rover(1, startingPosition.getX(), startingPosition.getY(), 10);

        // Create a SimulationContext with the rover, integerMap, and monitoredResources set to resource codes
        Set<Integer> monitoredResources = new HashSet<>();
        monitoredResources.add(2);
        SimulationContext context = new SimulationContext(
                100, // Number of steps
                10, // Steps to timeout
                rover,
                null, // Set the spaceship location if needed
                integerMap,
                monitoredResources,
                null // Set initial ExplorationOutcome if needed
        );

        // Create an ExplorationStep and execute it
        ExplorationStep explorationStep = new ExplorationStep();
        explorationStep.execute(context);

        // Check if all resources have been collected and the exploration outcome is complete
        assertTrue(context.getMonitoredResources().isEmpty());
        assertEquals(ExplorationOutcome.COMPLETE, context.getExplorationOutcome());
    }
}
