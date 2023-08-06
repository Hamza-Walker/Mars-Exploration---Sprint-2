package com.codecool.marsexploration.mapexplorer.simulation.steps;

import static org.junit.jupiter.api.Assertions.*;

import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;
import org.junit.jupiter.api.Test;

public class MovementStepTest {
    private static final String workDir = "src/main";

    @Test
    public void testExecute_UpdatesRoverPosition() {
        // Create a simulated IntegerMap and RoverPlacer for testing
        String mapFile = workDir + "/resources/exploration-2.map";
        MapLoader mapLoader = new MapLoaderImpl();
        IntegerMap integerMap = mapLoader.createIntegerMap(mapFile);
        RoverPlacer roverPlacer = new RoverPlacer();

        // Create a rover and set its initial position
        Rover rover = new Rover(1, 0, 0, 10);
        Coordinate initialPosition = new Coordinate(0, 0);
        rover.setCurrentPosition(initialPosition);

        // Create a SimulationContext with the rover and other required data
        SimulationContext context = new SimulationContext(
                10, // numberOfSteps
                10, // stepsToTimeout
                rover,
                new Coordinate(0, 0), // spaceshipLocation
                integerMap,
                null, // monitoredResources
                null  // explorationOutcome
        );

        // Create an instance of MovementStep and execute it
        MovementStep movementStep = new MovementStep();
        movementStep.execute(context);

        // Get the updated rover position
        Coordinate updatedPosition = rover.getCurrentPosition();

        // Assert that the rover's position has changed from the initial position
        assertNotEquals(initialPosition, updatedPosition);

        // You might want to add additional assertions here to check if the new position is valid,
        // such as whether it's within the map boundaries and not an obstacle.
    }

    // Add more tests as needed to cover different scenarios

}
