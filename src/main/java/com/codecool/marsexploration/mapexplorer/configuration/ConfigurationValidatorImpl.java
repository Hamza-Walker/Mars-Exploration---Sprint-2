package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

public class ConfigurationValidatorImpl implements ConfigurationValidator{
    @Override
    public boolean isLandingSpotValid(Coordinate landingSpot, String[][] mapData) {
        //  If it is null, it means there is no coordinate provided
        if( landingSpot == null ) return false;

        int x = landingSpot.getX();
        int y = landingSpot.getY();

        if (x >= 0 && x < mapData.length && y >= 0 && y < mapData[x].length) {
            return mapData[x][y] == null;
            //   it is null, it means the landing spot is empty and valid,
        }
        return false;
    }

    @Override
    public boolean hasEmptyAdjacentSpot(Coordinate landingSpot, String[][] mapData) {
        if (landingSpot == null) {
            return false;
        }

        int x = landingSpot.getX();
        int y = landingSpot.getY();

        // Check if any adjacent spot is empty
        return isEmptySpot(x - 1, y, mapData) ||
                isEmptySpot(x + 1, y, mapData) ||
                isEmptySpot(x, y - 1, mapData) ||
                isEmptySpot(x, y + 1, mapData);
    }
    private boolean isEmptySpot(int x, int y, String[][] mapData) {
        return x >= 0 && x < mapData.length && y >= 0 && y < mapData[x].length &&
                (mapData[x][y] == null || mapData[x][y].isEmpty());
    }

    @Override
    public boolean isMapPathNotEmpty(String mapPath) {
        return mapPath != null && !mapPath.trim().isEmpty();
    }

    @Override
    public boolean areResourcesSpecified(String resources) {
        return resources != null && resources.length() > 0;
    }

    @Override
    public boolean isTimeOutValid(int timeOut) {
        return timeOut > 0;
    }

}
