package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.simulation.SimulationContext;
import com.codecool.marsexploration.mapexplorer.simulation.SimulationStep;

public class StepIncrementStep implements SimulationStep {
    @Override
    public void execute(SimulationContext context) {
        // Implement the logic for the Step Increment step here
        // For example, increment the context step variable by one.
        int currentStep = context.getStep();
        context.setStep(currentStep + 1);
    }
}
