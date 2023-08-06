package com.codecool.marsexploration.mapexplorer.simulation;

import com.codecool.marsexploration.mapexplorer.configuration.Configuration;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.exploration.ExplorationOutcome;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.steps.*;
import com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder.IntegerMap;

import java.util.Arrays;
import java.util.List;


public class ExplorationSimulator {

    private final MapLoader mapLoader;
    private final RoverPlacer roverPlacer;
    private final ConfigurationValidator configurationValidator;

    public ExplorationSimulator(MapLoader mapLoader, RoverPlacer roverPlacer, ConfigurationValidator configurationValidator) {
        this.mapLoader = mapLoader;
        this.roverPlacer = roverPlacer;
        this.configurationValidator = configurationValidator;
    }

    public SimulationContext simulateExploration(Configuration config) {
        // Generate the context by loading the map and validating the configuration
        String workDir = "src/main";
        String mapFile = workDir + "/resources/exploration-2.map";
        MapLoader mapLoader = new MapLoaderImpl();
        IntegerMap integerMap = mapLoader.createIntegerMap(mapFile);
        System.out.println(integerMap);

        if (!configurationValidator.isTimeOutValid(config.timeOut())) {
            throw new IllegalArgumentException("Timeout must be greater than zero.");
        }

        Coordinate spaceshipCoordinate = config.landingSpot();
        if (!roverPlacer.isEmptySpot(spaceshipCoordinate.getX(), spaceshipCoordinate.getY(), integerMap)) {
            throw new IllegalArgumentException("No empty spot adjacent to the spaceship coordinate found.");
        }

        // Deploy the rover on an empty spot adjacent to the spaceship
        Rover rover = new Rover(config.roverID(), spaceshipCoordinate.getX(), spaceshipCoordinate.getY(), config.sightRange());


        // tying to set the rover on the map before the simulation begins
        Coordinate emptyAdjacentSpot = roverPlacer.findEmptyAdjacentSpot(integerMap, spaceshipCoordinate);
        if (emptyAdjacentSpot == null) {
            throw new IllegalArgumentException("No empty spot adjacent to the spaceship coordinate found.");
        }
        roverPlacer.deployRover(integerMap, rover, emptyAdjacentSpot);

        // Create the simulation context and start the exploration
        SimulationContext context = new SimulationContext(
                config.numberOfSteps(),
                config.timeOut(),
                rover,
                emptyAdjacentSpot, // Use the same coordinate as the one used for deploying the rover
                integerMap,
                config.resources(),
null
        );

        // Start the exploration loop
        explore(context);

        return context;
    }



    private void explore(SimulationContext context) {
        List<SimulationStep> steps = Arrays.asList(
                new MovementStep(),
                new ScanningStep(),
                new AnalysisStep(),
                new LogStep(),
                new StepIncrementStep()
        );

        for (int i = 0; i < context.getStepsToTimeout(); i++) {
            for (SimulationStep step : steps) {
                step.execute(context);
            }
        }
    }

    // Add helper methods for specific simulation steps if needed
}
