package com.codecool.marsexploration.mapexplorer.logger;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleLoggerTest {

    @Test
    public void testLog() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Given
        ConsoleLogger consoleLogger = new ConsoleLogger();
        String message = "Test message";

        // When
        consoleLogger.log(message);

        // Then
        String expectedOutput = message + "\n";
        assertEquals(expectedOutput, outputStream.toString());

        // Reset System.out
        System.setOut(System.out);
    }
}
