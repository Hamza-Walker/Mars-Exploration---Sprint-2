package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.logger.ConsoleLogger;
import com.codecool.marsexploration.mapexplorer.logger.Logger;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;

public class LogStep implements SimulationStep {
    private final Logger logger;

    public LogStep() {
        this.logger = new ConsoleLogger();
    }

    @Override
    public void execute(SimulationContext context) {
        // Implement the logic for the Log step here
        // For example, write the current state of events in the simulation to a log file.

        String message = generateLogMessage(context);
        logger.log(message);
    }

    private String generateLogMessage(SimulationContext context) {
        StringBuilder sb = new StringBuilder();
        sb.append("Simulation Step: ").append(context.getStep()).append("\n");
        sb.append("----------------------------\n");
        sb.append("Rover ID: ").append(context.getRover().getId()).append("\n");
        sb.append("Current Position: ").append(context.getRover().getCurrentPosition()).append("\n");
        sb.append("Monitored Resources: ").append(context.getMonitoredResources()).append("\n");
        sb.append("Resources Collected: ").append(context.getRover().getResourceCoordinates()).append("\n");
        sb.append("Exploration Outcome: ").append(context.getExplorationOutcome()).append("\n");
        sb.append("----------------------------\n");

        return sb.toString();
    }
}
