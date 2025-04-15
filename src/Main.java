import java.io.*;
import java.util.*;

public class TondeuseAutomatique {

    public static void main(String[] args) {

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

        for (char instruction : instructions.toCharArray()) { //each instruction is a char
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
