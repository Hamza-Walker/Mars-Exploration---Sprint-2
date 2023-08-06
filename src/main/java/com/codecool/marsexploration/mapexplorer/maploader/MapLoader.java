package com.codecool.marsexploration.mapexplorer.maploader;

import com.codecool.marsexploration.mapexplorer.maploader.model.Map;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;

public interface MapLoader {
    Map load(String mapFile);

    IntegerMap createIntegerMap (String mapFile);
}
