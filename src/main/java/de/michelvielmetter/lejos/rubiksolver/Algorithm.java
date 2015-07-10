package de.michelvielmetter.lejos.rubiksolver;

import cs.min2phase.Search;
import de.michelvielmetter.lejos.util.Display;

/**
 * Created by Vera on 27.06.15.
 */


public class Algorithm {

    private boolean cubeError;

    public boolean isCubeError() {
        return cubeError;
    }

    public boolean isMovesCalculated() {
        return movesCalculated;
    }

    public boolean isDebug() {
        return debug;
    }

    private boolean movesCalculated;
    private boolean debug;

    private long solveTime;

    public Algorithm(boolean debug) {
        cubeError = true;
        movesCalculated = false;
        this.debug = debug;
    }

    public String run(RubikCube cube, Display display) {
        String input = convertCube(cube, display);
        int maxDepth = 21;
        int maxTime = 5;
        String moves = calculateMoves(input, maxDepth, maxTime);
        cubeError = verifyCube(moves, display);
        movesCalculated = verifyCalculatetMoves(moves, display);
        int i = 1;
        while (!cubeError && movesCalculated && i < 6) {
            moves = calculateMoves(input, maxDepth + i, maxTime + i);
            movesCalculated = verifyCalculatetMoves(moves, display);
            i++;
        }
        if (debug) {
            display.drawString(moves, 4);
        }
        return moves;
//        return "";
    }

    public String runDebug(String input, Display display) {
        debug = true;
        display.drawString("Calculating moves", 1);
        int maxDepth = 21;
        int maxTime = 5;
        String moves = calculateMoves(input, maxDepth, maxTime);
        cubeError = verifyCube(moves, display);
        movesCalculated = verifyCalculatetMoves(moves, display);
        int i = 1;
        while (!cubeError && !movesCalculated && i < 6) {
            display.drawString("Calculating again!", 1 + i);
            moves = calculateMoves(input, maxDepth + i, maxTime + i);
            movesCalculated = verifyCalculatetMoves(moves, display);
            i++;
        }
        if (debug) {
            display.clear();
            display.drawString(moves, 1);
        }
        return moves;
    }

    private String convertCube(RubikCube cube, Display display) {
        StringBuilder s = new StringBuilder(0); //StringBuffer to generate input String

        //Array with the position of the middleColors
        char[] middleColors = new char[14];
        middleColors[cube.getSide(RubikSide.TOP).getMiddleColor()] = 'U';
        middleColors[cube.getSide(RubikSide.BACK).getMiddleColor()] = 'B';
        middleColors[cube.getSide(RubikSide.FRONT).getMiddleColor()] = 'F';
        middleColors[cube.getSide(RubikSide.DOWN).getMiddleColor()] = 'D';
        middleColors[cube.getSide(RubikSide.LEFT).getMiddleColor()] = 'L';
        middleColors[cube.getSide(RubikSide.RIGHT).getMiddleColor()] = 'R';
        for (int i = 0; i < 9; i++) {
            char color = middleColors[cube.getSide(0).convertToBlockColors()[i]];
            s.append(color);
        }
        for (int i = 8; i >= 0; i--) {
            char color = middleColors[cube.getSide(5).convertToBlockColors()[i]];
            s.append(color);
        }
        for (int i = 0; i < 9; i++) {
            char color = middleColors[cube.getSide(3).convertToBlockColors()[i]];
            s.append(color);
        }
        for (int i = 0; i < 9; i++) {
            char color = middleColors[cube.getSide(2).convertToBlockColors()[i]];
            s.append(color);
        }
        for (int i = 8; i >= 0; i--) {
            char color = middleColors[cube.getSide(4).convertToBlockColors()[i]];
            s.append(color);
        }
        for (int i = 0; i < 9; i++) {
            char color = middleColors[cube.getSide(1).convertToBlockColors()[i]];
            s.append(color);
        }
        String input = s.toString();
        if (debug) {
            if (s.length() > 54) {
                display.drawString("Input String is to long", 2);
            }
            if (s.length() < 54) {
                display.drawString("Input String is to short", 2);
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            display.clear();
            System.out.println(input);
        }
        return input;
    }

    private String calculateMoves(String input, int maxDepth, int maxTime) {
        boolean inverse = false;
        Search search = new Search();
        long t = System.nanoTime();
        String result = search.solution(input, maxDepth, 100, 0, 4);
        long n_probe = search.numberOfProbes();
        while (result.startsWith("Error 8") && ((System.nanoTime() - t) < maxTime * 1.0e9)) {
            result = search.next(100, 0, 4);
            n_probe += search.numberOfProbes();
        }
        solveTime = System.nanoTime() - t;
        return result;
    }

    private boolean verifyCube(String result, Display display) {
        boolean error = false;
        if (result.contains("Error")) {
            switch (result.charAt(result.length() - 1)) {
                case '1':
                    display.drawString("There are not exactly nine facelets of each color!", 2);
                    error = true;
                    break;
                case '2':
                    display.drawString("Not all 12 edges exist exactly once!", 2);
                    error = true;
                    break;
                case '3':
                    display.drawString("Flip error: One edge has to be flipped!", 2);
                    error = true;
                    break;
                case '4':
                    display.drawString("Not all 8 corners exist exactly once!", 2);
                    error = true;
                    break;
                case '5':
                    display.drawString("Twist error: One corner has to be twisted!", 2);
                    error = true;
                    break;
                case '6':
                    display.drawString("Parity error: Two corners or two edges have to be exchanged!", 2);
                    error = true;
                    break;
            }
        }
        return error;

    }

    private boolean verifyCalculatetMoves(String result, Display display) {
        boolean movesOK = false;
        if (result.contains("Error")) {
            movesOK = false;
            switch (result.charAt(result.length() - 1)) {
                case '7':
                    display.drawString("No solution exists for the given maximum move number! Trying again!", 2);
                    break;
                case '8':
                    display.drawString("Timeout, no solution found within given maximum time! Trying again!", 2);
                    break;
            }
        } else {
            movesOK = true;
        }
        return movesOK;
    }
}
