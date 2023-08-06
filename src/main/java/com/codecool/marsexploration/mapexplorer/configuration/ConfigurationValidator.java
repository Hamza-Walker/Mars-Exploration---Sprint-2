package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

public interface ConfigurationValidator {
    boolean isLandingSpotValid (Coordinate landingSpot, String[][] mapData);
    boolean hasEmptyAdjacentSpot (Coordinate landingSpot, String[][] mapData);
    boolean isMapPathNotEmpty ( String mapPAth);
    boolean areResourcesSpecified(String resources);
    boolean isTimeOutValid ( int timeOut);
}
