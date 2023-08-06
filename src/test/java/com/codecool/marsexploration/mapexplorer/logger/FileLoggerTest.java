package com.codecool.marsexploration.mapexplorer.logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileLoggerTest {
    private String testFilePath = "test.log";
    private FileLogger fileLogger;

    @BeforeEach
    public void setUp() {
        fileLogger = new FileLogger(testFilePath);
    }

    @AfterEach
    public void tearDown() {
        // Delete the test file after each test
        try {
            FileWriter fileWriter = new FileWriter(testFilePath);
            fileWriter.write("");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLog() {
        // Given
        String message = "Test message";

        // When
        fileLogger.log(message);

        // Then
        String actualMessage = readLastLineFromFile();
        assertEquals(message, actualMessage);
    }

    private String readLastLineFromFile() {
        String lastLine = "";
        try (BufferedReader br = new BufferedReader(new FileReader(testFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastLine;
    }
}
