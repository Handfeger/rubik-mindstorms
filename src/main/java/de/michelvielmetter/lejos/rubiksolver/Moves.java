package de.michelvielmetter.lejos.rubiksolver;

/**
 * Created by Vera on 09.07.15.
 */

public class Moves {
    RubikSide up;
    RubikSide back;
    RubikSide down;
    RubikSide front;
    RubikSide left;
    RubikSide right;
    String movesNumberString;

    public Moves(RubikCube cube, String moves) {
        init(cube);
        move(cube, moves);

    }

    public String getMovesNumberString() {
        return movesNumberString;
    }

    private void init(RubikCube cube) {
        up = cube.getSide(RubikSide.TOP);
        back = cube.getSide(RubikSide.BACK);
        down = cube.getSide(RubikSide.DOWN);
        front = cube.getSide(RubikSide.FRONT);
        left = cube.getSide(RubikSide.LEFT);
        right = cube.getSide(RubikSide.RIGHT);
    }

    public void move(RubikCube cube, String moves) {
        RubikSide current = null;
        int moveCounter=0;
        for (int i = 0; i < moves.length(); i += 3) {
            switch (moves.charAt(i)) {
                case 'U':
                    current = up;
                    break;
                case 'B':
                    current = back;
                    break;
                case 'D':
                    current = down;
                    break;
                case 'F':
                    current = front;
                    break;
                case 'L':
                    current = left;
                    break;
                case 'R':
                    current = right;
                    break;
                case '(':
                    current = null;
                    moveCounter--;
                    break;
            }
            if (current != null) {
                if (moves.charAt(i + 1) == '\'') {
                    cube.sideCounterClockwise(current);
                } else if (moves.charAt(i + 1) == '2') {
                    cube.sideTwoTimes(current);
                } else {
                    cube.sideClockwise(current);
                }
            }
            moveCounter++;
        }
        movesNumberString = Integer.toString(moveCounter) + " face turns!";

    }
}
