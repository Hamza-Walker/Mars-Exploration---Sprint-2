package com.codecool.marsexploration.mapexplorer.maploader.model;

public class Map {
    private String[][] representation;
    private boolean successfullyGenerated;

    public Map(String[][] representation, boolean successfullyGenerated) {
        this.representation = representation;
        this.successfullyGenerated = successfullyGenerated;
    }

    public int getDimension() {
        return representation.length;
    }

    private static String createStringRepresentation(String[][] arr) {
        StringBuilder sb = new StringBuilder();

        for (String[] strings : arr) {
            StringBuilder s = new StringBuilder();
            for (String string : strings) {
                s.append(string == null ? " " : string);
            }

            sb.append(s).append("\n");
        }

        return sb.toString();
    }

    public String getByCoordinate(Coordinate coordinate) {
        return representation[coordinate.getX()][coordinate.getY()];
    }

    public boolean isEmpty(Coordinate coordinate) {
        int x = coordinate.getX();
        int y = coordinate.getY();

        if (x >= 0 && x < representation.length && y >= 0 && y < representation[x].length) {
            String value = representation[x][y];
            return value == null || value.isEmpty() || value.equals(" ");
        }

        // Return true for coordinates outside the map bounds
        return true;
    }

    public boolean successfullyGenerated() {
        return successfullyGenerated;
    }

    public String[][] getRepresentation() {
        return representation;
    }

    public Coordinate getSpaceshipCoordinate() {
        for (int x = 0; x < representation.length; x++) {
            for (int y = 0; y < representation[x].length; y++) {
                if ("&".equals(representation[x][y])) {
                    return new Coordinate(x, y);
                }
            }
        }
        return null; // No spaceship coordinate found
    }

    @Override
    public String toString() {
        return createStringRepresentation(representation);
    }
}
