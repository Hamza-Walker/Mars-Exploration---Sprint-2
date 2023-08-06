package com.codecool.marsexploration.mapexplorer.simulation.steps.pathfinder;

import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;

import java.util.ArrayList;
import java.util.List;

public class PathFinder{

    private static boolean IsWalkable(IntegerMap map, Point point) {
        if (point.y < 0 || point.y >= map.getDimension()) return false;
        if (point.x < 0 || point.x >= map.getDimension()) return false;
        return map.getValue(point.x, point.y) == 0;
    }

    public static List<Point> FindNeighbors(IntegerMap map, Point point) {
        List<Point> neighbors = new ArrayList<>();
        Point up = point.offset(0, 1);
        Point down = point.offset(0, -1);
        Point left = point.offset(-1, 0);
        Point right = point.offset(1, 0);
        if (IsWalkable(map, up)) neighbors.add(up);
        if (IsWalkable(map, down)) neighbors.add(down);
        if (IsWalkable(map, left)) neighbors.add(left);
        if (IsWalkable(map, right)) neighbors.add(right);
        return neighbors;
    }

    public static List<Point> FindPath(IntegerMap map, Point start, Point end) {
        boolean finished = false;
        List<Point> open = new ArrayList<>();
        open.add(start);
        List<Point> closed = new ArrayList<>();
        while (!finished) {
            if (open.isEmpty()) {
                System.out.println("No path found");
                return null;
            }

            Point current = open.remove(open.size() - 1);
            List<Point> neighbors = FindNeighbors(map, current);

            for (Point neighbor : neighbors) {
                if (!open.contains(neighbor) && !closed.contains(neighbor)) {
                    neighbor.previous = current; // Set the 'previous' field to trace the path
                    open.add(neighbor);
                    if (neighbor.equals(end)) {
                        finished = true;
                        break;
                    }
                }
            }

            closed.add(current);
        }

        List<Point> path = new ArrayList<>();
        Point point = end;
        while (point != null) {
            path.add(0, point);
            point = point.previous;
        }
        return path;
    }


//    public static void main(String[] args) {
//        int[][] map = {
//                {0, 0, 0, 0, 0},
//                {0, 0, 1, 0, 1},
//                {1, 0, 0, 1, 1},
//                {0, 0, 0, 1, 0},
//                {1, 1, 0, 0, 1}
//        };
//
//        Point start = new Point(0, 0, null);
//        Point end = new Point(3, 4, null);
//        List<Point> path = FindPath(map, start, end);
//        if (path != null) {
//            for (Point point : path) {
//                System.out.println(point);
//            }
//        }
//        else
//            System.out.println("No path found");
//    }
}