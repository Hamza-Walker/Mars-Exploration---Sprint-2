package com.codecool.marsexploration.mapexplorer;

import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationImpl;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidator;
import com.codecool.marsexploration.mapexplorer.configuration.ConfigurationValidatorImpl;

import com.codecool.marsexploration.mapexplorer.maploader.MapLoader;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.rovers.RoverPlacer;
import com.codecool.marsexploration.mapexplorer.simulation.ExplorationSimulator;

import java.util.HashSet;
import java.util.Set;

public class Application {
    private static final String workDir = "src/main";

    public static void main(String[] args) {
        String mapFile = workDir + "/resources/exploration-1.map";
        MapLoader mapLoader = new MapLoaderImpl();
        RoverPlacer roverPlacer = new RoverPlacer();
        ConfigurationValidator configurationValidator = new ConfigurationValidatorImpl();

        // Create the ExplorationSimulator
        ExplorationSimulator explorationSimulator = new ExplorationSimulator(mapLoader, roverPlacer, configurationValidator);


        // Define the configuration
        Set<Integer> resources = new HashSet<>();
        // resources.add(2);


        ConfigurationImpl config = new ConfigurationImpl(
                2,                     // numberOfSteps
                60,                    // timeOut
                1,              // roverID
                new Coordinate(0, 0),   // landingSpot
                5,                      // sightRange
                mapFile,                // mapPath
                resources               // resources
        );

        // why does explorationSimulator receive a configuration and not context
        // the coordinates are correctly being passed from the config to the Context
        // now check the rover creation : done all seems to be in check


        // Run the simulation
        explorationSimulator.simulateExploration(config);
    }
}
