import java.io.*;
import java.util.*;

public class TondeuseAutomatique {

    public static void main(String[] args) {

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
