package com.walker.mapLoader;


import com.walker.mapLoader.model.Map;

public interface MapLoader {
    Map load(String mapFile);
}
