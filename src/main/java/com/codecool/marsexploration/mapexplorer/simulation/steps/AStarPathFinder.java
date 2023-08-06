package com.codecool.marsexploration.mapexplorer.simulation.steps;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;
import com.codecool.marsexploration.mapexplorer.maploader.model.IntegerMap;

import java.util.*;

public class AStarPathFinder {
    private static final int DIAGONAL_COST = 14;
    private static final int VERTICAL_HORIZONTAL_COST = 10;

    public static List<Coordinate> findPath(IntegerMap integerMap, Coordinate start, Coordinate end) {


        int[][] mapData = new int[integerMap.getDimension()][integerMap.getDimension()];

        for (int y = 0; y < mapData.length; y++) {
            for (int x = 0; x < mapData[y].length; x++) {
                mapData[y][x] = integerMap.getValue(x, y);
            }
        }


        int width = integerMap.getDimension();
        int height = integerMap.getDimension();

        if (!isValidCoordinate( start, width, height) || !isValidCoordinate(end, width, height)) {
            System.out.println(" is not a valid coordinate ");
            return null;
        }

        if (mapData[end.getY()][end.getX()] != 0) {
            // The end point is not reachable, return null
            System.out.println("The end point is not reachable, return null");
            return null;
        }

        boolean[][] closed = new boolean[height][width];
        int[][] fCosts = new int[height][width];
        int[][] gCosts = new int[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                gCosts[y][x] = Integer.MAX_VALUE;
                fCosts[y][x] = Integer.MAX_VALUE;
            }
        }

        // Initialize the starting point
        int startX = start.getX();
        int startY = start.getY();
        gCosts[startY][startX] = 0;
        fCosts[startY][startX] = calculateHCost(startX, startY, end);

        PriorityQueue<Node> open = new PriorityQueue<>();
        open.add(new Node(startX, startY, 0, fCosts[startY][startX], null));

        while (!open.isEmpty()) {
            Node current = open.poll();
            int x = current.getX();
            int y = current.getY();
            closed[y][x] = true;

            if (x == end.getX() && y == end.getY()) {
                // Path found, reconstruct the path
                return reconstructPath(current);
            }

            for (int i = 0; i < 8; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (isValidCoordinate(newX, newY, width, height) && mapData[newY][newX] == 0 && !closed[newY][newX]) {
                    int gCost = gCosts[y][x] + ((i % 2 == 0) ? VERTICAL_HORIZONTAL_COST : DIAGONAL_COST);

                    if (gCost < gCosts[newY][newX]) {
                        int hCost = calculateHCost(newX, newY, end);
                        fCosts[newY][newX] = gCost + hCost;
                        gCosts[newY][newX] = gCost;
                        open.add(new Node(newX, newY, gCost, fCosts[newY][newX], current));
                    }
                }
            }
        }
        System.out.println("no Path found ");
        // No path found
        return null ;
    }

    private static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    private static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    private static boolean isValidCoordinate(int x, int y, int width, int height) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    private static boolean isValidCoordinate(Coordinate coordinate, int width, int height) {
        return isValidCoordinate(coordinate.getX(), coordinate.getY(), width, height);
    }

    private static int calculateHCost(int x, int y, Coordinate end) {
        return Math.abs(x - end.getX()) + Math.abs(y - end.getY());
    }

    private static List<Coordinate> reconstructPath(Node lastNode) {
        List<Coordinate> path = new ArrayList<>();
        Node current = lastNode;

        while (current != null) {
            path.add(new Coordinate(current.getX(), current.getY()));
            current = current.getPrevious();
        }

        Collections.reverse(path);
        return path;
    }

    private static class Node implements Comparable<Node> {
        private final int x;
        private final int y;
        private final int gCost;
        private final int fCost;
        private final Node previous;

        public Node(int x, int y, int gCost, int fCost, Node previous) {
            this.x = x;
            this.y = y;
            this.gCost = gCost;
            this.fCost = fCost;
            this.previous = previous;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getGCost() {
            return gCost;
        }

        public int getFCost() {
            return fCost;
        }

        public Node getPrevious() {
            return previous;
        }


        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.fCost, other.fCost);
        }
    }
}
