package com.codecool.marsexploration.mapexplorer.maploader.model;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import java.util.List;

public record SimulationParameters(
        String mapFilePath,
        Coordinate landingSpot,
        List<String> resourcesToScan,
        int timeoutSteps
) {}