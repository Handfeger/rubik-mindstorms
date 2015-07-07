package de.michelvielmetter.lejos.rubiksolver;

import cs.min2phase.Search;
import de.michelvielmetter.lejos.util.Display;

/**
 * Created by Vera on 27.06.15.
 */


public class Algorithm {

    public Boolean cubeError;
    public Boolean movesCalculated;
    private Boolean debug;

    public Algorithm(Boolean debug){
        cubeError=true;
        movesCalculated=false;
        this.debug=debug;
    }

    public String run(RubikCube cube, Display display){
        String input=convertCube(cube, display);
        int maxDepth=21; int maxTime=5;
        String moves=calculateMoves(input,maxDepth,maxTime);
        cubeError =verifyCube(moves,display);
        movesCalculated=verifyCalculatetMoves(moves,display);
        int i=1;
        while(!cubeError&&movesCalculated&&i<6){
            moves=calculateMoves(input,maxDepth+i,maxTime+i);
            movesCalculated=verifyCalculatetMoves(moves,display);
            i++;
        }
        if (debug){
            display.drawString(moves,4);
        }
        return moves;
    }
    public String runDebug(String input,Display display){
        debug=true;
        display.drawString("Calculating moves",1);
        int maxDepth=16; int maxTime=5;
        String moves=calculateMoves(input,maxDepth,maxTime);
        cubeError =verifyCube(moves,display);
        movesCalculated=verifyCalculatetMoves(moves, display);
        int i=1;
        while(!cubeError&&!movesCalculated&&i<6){
            display.drawString("Calculating again!",1+i);
            moves=calculateMoves(input,maxDepth+i,maxTime+i);
            movesCalculated=verifyCalculatetMoves(moves,display);
            i++;
        }
        if (debug){
            display.clear();
            display.drawString(moves,1);
        }
        return moves;
    }

    private String convertCube(RubikCube cube,Display display) {
        StringBuffer s = new StringBuffer(0); //StringBuffer to generate input String

        //Array with the position of the middleColors
        char[] middleColors =new char [14];
        middleColors[cube.getSide(RubikSide.TOP).getMiddleColor()]='U';
        middleColors[cube.getSide(RubikSide.BACK).getMiddleColor()]='B';
        middleColors[cube.getSide(RubikSide.FRONT).getMiddleColor()]='F';
        middleColors[cube.getSide(RubikSide.DOWN).getMiddleColor()]='D';
        middleColors[cube.getSide(RubikSide.LEFT).getMiddleColor()]='L';
        middleColors[cube.getSide(RubikSide.RIGHT).getMiddleColor()]='R';
        for(int i=0;i<9;i++){
            char color=middleColors[cube.getSide(0).convertToBlockColors()[i]];
            s.append(color);
        }
        for(int i=0;i<9;i++){
            char color=middleColors[cube.getSide(5).convertToBlockColors()[i]];
            s.append(color);
        }
        for(int i=0;i<9;i++){
            char color=middleColors[cube.getSide(3).convertToBlockColors()[i]];
            s.append(color);
        }
        for(int i=8;i>=0;i--){
            char color=middleColors[cube.getSide(2).convertToBlockColors()[i]];
            s.append(color);
        }
        for(int i=0;i<9;i++){
            char color=middleColors[cube.getSide(4).convertToBlockColors()[i]];
            s.append(color);
        }
        for(int i=0;i<9;i++){
            char color=middleColors[cube.getSide(1).convertToBlockColors()[i]];
            s.append(color);
        }
        String input = s.toString();
        if(debug){
            if (s.length()>54){
                display.drawString("Input String is to long",2);
            }
            if (s.length()<54) {
                display.drawString("Input String is to short", 2);
            }
            display.drawString(input, 3);
        }
        return input;
    }
    private String calculateMoves(String input,int maxDepth,int maxTime){
        boolean inverse=false;
        Search search=new Search();
        long t = System.nanoTime();
        int mask=inverse ? 6 : 4;
        String result = search.solution(input, maxDepth, 100, 0, mask);
        long n_probe = search.numberOfProbes();
        while (result.startsWith("Error 8") && ((System.nanoTime() - t) < maxTime * 1.0e9)) {
            result = search.next(100, 0, mask);
            n_probe += search.numberOfProbes();
        }
        t = System.nanoTime() - t;
        return result;
    }
    private Boolean verifyCube(String result,Display display){
        Boolean error=false;
        if (result.contains("Error")) {
            switch (result.charAt(result.length() - 1)) {
                case '1':
                    display.drawString("There are not exactly nine facelets of each color!",2);
                    error=true;
                    break;
                case '2':
                    display.drawString("Not all 12 edges exist exactly once!",2);
                    error=true;
                    break;
                case '3':
                    display.drawString("Flip error: One edge has to be flipped!",2);
                    error=true;
                    break;
                case '4':
                    display.drawString("Not all 8 corners exist exactly once!",2);
                    error=true;
                    break;
                case '5':
                    display.drawString("Twist error: One corner has to be twisted!",2);
                    error=true;
                    break;
                case '6':
                    display.drawString("Parity error: Two corners or two edges have to be exchanged!",2);
                    error=true;
                    break;
            }
        }
        return error;

    }
    private Boolean verifyCalculatetMoves(String result, Display display){
        Boolean movesOK=false;
        if (result.contains("Error")) {
            movesOK = false;
            switch (result.charAt(result.length() - 1)) {
                case '7':
                    display.drawString("No solution exists for the given maximum move number! Trying again!",2);
                    break;
                case '8':
                    display.drawString("Timeout, no solution found within given maximum time! Trying again!",2);
                    break;
            }
        }
        else{
            movesOK=true;
        }
        return movesOK;
    }
}
