package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;
import com.codecool.marsexploration.mapexplorer.rovers.Rover;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ScanningStep implements SimulationStep {



    @Override
    public void execute(SimulationContext context) {
        IntegerMap integerMap = context.getIntegerMap();
        Rover rover = context.getRover();
        int sightRange = rover.getSightRange();

        Coordinate roverPosition = rover.getCurrentPosition();
        Set<Integer> resourcesFound = new HashSet<>();
        List<Coordinate> monitoredResourceCoordinates = context.getMonitoredResourceCoordinate();


        // Scan the area around the rover within the sight range
        for (int x = roverPosition.getX() - sightRange; x <= roverPosition.getX() + sightRange; x++) {
            for (int y = roverPosition.getY() - sightRange; y <= roverPosition.getY() + sightRange; y++) {

                if (x >= 0 && x < integerMap.getDimension() && y >= 0 && y < integerMap.getDimension()) {
                    Coordinate currentCoordinate = new Coordinate(x, y);
                    int resource = integerMap.getValue(x, y);

                    if ((resource == 2 || resource == 3) && !monitoredResourceCoordinates.contains(currentCoordinate)) {
                        // Resource found, add it to the set of resourcesFound
                        resourcesFound.add(resource);
                        monitoredResourceCoordinates.add(new Coordinate(x,y));
                        System.out.println(" monitored resources " + monitoredResourceCoordinates);
                    }
                }
            }
        }

        // Update the context with the resources found during the scanning step
        Set<Integer> monitoredResources = context.getMonitoredResources();
        monitoredResources.addAll(resourcesFound);
        context.setMonitoredResources(monitoredResources);
    }
}
