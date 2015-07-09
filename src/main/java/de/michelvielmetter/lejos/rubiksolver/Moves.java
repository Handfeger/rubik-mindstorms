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
        for (int i = 0; i < moves.length(); i += 3) {
            switch (moves.charAt(i)) {
                case 'U':
                    if (moves.charAt(i + 1) == '\'') {
                        cube.sideCounterClockwise(up);
                    } else if (moves.charAt(i + 1) == '2') {
                        cube.sideTwoTimes(up);
                    } else {
                        cube.sideClockwise(up);
                    }
                    break;
                case 'B':
                    if (moves.charAt(i + 1) == '\'') {
                        cube.sideCounterClockwise(back);
                    } else if (moves.charAt(i + 1) == '2') {
                        cube.sideTwoTimes(back);
                    } else {
                        cube.sideClockwise(back);
                    }
                    break;
                case 'D':
                    if (moves.charAt(i + 1) == '\'') {
                        cube.sideCounterClockwise(down);
                    } else if (moves.charAt(i + 1) == '2') {
                        cube.sideTwoTimes(down);
                    } else {
                        cube.sideClockwise(down);
                    }
                    break;
                case 'F':
                    if (moves.charAt(i + 1) == '\'') {
                        cube.sideCounterClockwise(front);
                    } else if (moves.charAt(i + 1) == '2') {
                        cube.sideTwoTimes(front);
                    } else {
                        cube.sideClockwise(front);
                    }
                    break;
                case 'L':
                    if (moves.charAt(i + 1) == '\'') {
                        cube.sideCounterClockwise(left);
                    } else if (moves.charAt(i + 1) == '2') {
                        cube.sideTwoTimes(left);
                    } else {
                        cube.sideClockwise(left);
                    }
                    break;
                case 'R':
                    if (moves.charAt(i + 1) == '\'') {
                        cube.sideCounterClockwise(right);
                    } else if (moves.charAt(i + 1) == '2') {
                        cube.sideTwoTimes(right);
                    } else {
                        cube.sideClockwise(right);
                    }
                    break;
                case '(':
                    movesNumberString = moves.substring(i + 1, i + 2) + "face turns!";
                    break;
            }
            try {
                System.out.print(moves.charAt(i));
                System.out.println(moves.charAt(i + 1));
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
