    package com.codecool.marsexploration.mapexplorer.maploader;

    import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
    import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;

    import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.util.List;

    public class MapLoaderImpl implements MapLoader {

        @Override
        public Map load(String mapFile) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(mapFile));
                String[][] mapData = parseMapData(lines);
                return new Map(mapData, false); // 'false' indicates that the map is not explored yet
            } catch (IOException e) {
                e.printStackTrace();
                return null; // You can handle the exception more gracefully if needed.
            }
        }

        private String[][] parseMapData(List<String> lines) {
            int height = lines.size();
            int width = lines.get(0).length();
            String[][] mapData = new String[height][width];

            for (int i = 0; i < height; i++) {
                String line = lines.get(i);
                for (int j = 0; j < width; j++) {
                    mapData[i][j] = String.valueOf(line.charAt(j));
                }
            }

            return mapData;
        }

        @Override
        public IntegerMap createIntegerMap(String mapFile) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(mapFile));
                int[][] integerMapData = parseIntegerMapData(lines);
                return new IntegerMap(integerMapData);
            } catch (IOException e) {
                e.printStackTrace();
                return null; // You can handle the exception more gracefully if needed.
            }
        }

        private int[][] parseIntegerMapData(List<String> lines) {
            int height = lines.size();
            int width = lines.get(0).length();
            int[][] integerMapData = new int[height][width];

            // Define the mapping of terrain type strings to integer values
            // Modify this mapping according to your terrain types
            java.util.Map<String, Integer> terrainMapping = new java.util.HashMap<>(); // naming conflict
            terrainMapping.put(" ", 0); // Empty space
            terrainMapping.put("#", 1); // Wall
            terrainMapping.put("%", 2); // Resource
            terrainMapping.put("*", 3); // Resource
            // Add more mappings for other terrain types as needed

            for (int i = 0; i < height; i++) {
                String line = lines.get(i);
                for (int j = 0; j < width; j++) {
                    String terrain = String.valueOf(line.charAt(j));
                    // Map the terrain type string to the corresponding integer value
                    int terrainValue = terrainMapping.getOrDefault(terrain, 0); // Use 0 as default for unknown terrains
                    integerMapData[i][j] = terrainValue;
                }
            }

            return integerMapData;
        }

    }
