import java.io.*;
import java.util.*;

public class Mower {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Mower <input_file>");
            return;
        }

        String inputFilePath = args[0];
        try {
            List<String> lines = readInputFile(inputFilePath);
            List<Position> finalPositions = processMowers(lines);
            for (Position pos : finalPositions) {
                System.out.println(pos);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }



    static List<Position> processMowers(List<String> lines) {
        List<Position> results = new ArrayList<>(); //we may have more than one mower
        String[] lawnDimensions = lines.get(0).split(" ");

        int maxX = Integer.parseInt(lawnDimensions[0]);
        int maxY = Integer.parseInt(lawnDimensions[1]);

        for (int i = 1; i < lines.size(); i += 2) {
            String initialPosition = lines.get(i);
            String instructions = lines.get(i + 1);

            String[] positionParts = initialPosition.split(" ");
            int x = Integer.parseInt(positionParts[0]);
            int y = Integer.parseInt(positionParts[1]);
            char orientation = positionParts[2].charAt(0);

            Position finalPosition = executeInstructions(x, y, orientation, instructions, maxX, maxY);
            results.add(finalPosition);
        }

        return results;
    }

    private static List<String> readInputFile(String filePath) throws IOException { //process the text file to execute the instructions
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line.trim());
            }
        }
        return lines;
    }

    private static Position executeInstructions(int x, int y, char orientation, String instructions, int maxX, int maxY) {
        Position position = new Position(x, y, orientation);

        for (char instruction : instructions.toCharArray()) { //each instruction is a char but we take in the intructions as a string
            switch (instruction) {
                case 'G':
                    position.turnLeft();
                    break;
                case 'D':
                    position.turnRight();
                    break;
                case 'A':
                    position.advance(maxX, maxY);
                    break;
            }
        }

        return position;
    }

    static class Position {
        private int x;
        private int y;
        private char orientation; //advance depends the orientation thus it is necessary to take this into account when moving

        Position(int x, int y, char orientation) {
            this.x = x;
            this.y = y;
            this.orientation = orientation;
        }

        void turnLeft() {
            switch (orientation) {
                case 'N':
                    orientation = 'W';
                    break;
                case 'W':
                    orientation = 'S';
                    break;
                case 'S':
                    orientation = 'E';
                    break;
                case 'E':
                    orientation = 'N';
                    break;
            }
        }

        void turnRight() {
            switch (orientation) {
                case 'N':
                    orientation = 'E';
                    break;
                case 'E':
                    orientation = 'S';
                    break;
                case 'S':
                    orientation = 'W';
                    break;
                case 'W':
                    orientation = 'N';
                    break;
            }
        }

        void advance(int maxX, int maxY) { //we need to beware of the maximum value x and y can take
            switch (orientation) {
                case 'N':
                    if (y + 1 <= maxY) y++; //else we are already at the edge
                    break;
                case 'E':
                    if (x + 1 <= maxX) x++;
                    break;
                case 'S':
                    if (y - 1 >= 0) y--;
                    break;
                case 'W':
                    if (x - 1 >= 0) x--;
                    break;
            }
        }

        @Override
        public String toString() {
            return x + " " + y + " " + orientation;
        }
    }
}
