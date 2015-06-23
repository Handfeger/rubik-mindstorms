package de.michelvielmetter.lejos.rubiksolver;

/**
 * ╔================================ RubikSide ====================================
 * ║
 * ║ Create Date: 23.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ RubikSide ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ RubikSide ====================================
 */
public class RubikSide
{
    // The Sides that will be seen from the colorpicker!
    public final static int TOP = 0;
    public final static int DOWN = 2;
    public final static int FRONT = 3;
    public final static int BACK = 1;
    public final static int LEFT = 4;
    public final static int RIGHT = 5;

    private int middleColor;

    private int[] outerColors = new int[8];

    private RubikCube cube;

    private int currentSide;

    public RubikSide(int currentSide, RubikCube cube)
    {
        this.cube = cube;
        this.currentSide = currentSide;
    }

    public void putTop()
    {
        switch (currentSide) {
            case RubikSide.DOWN:
                cube.y(2);
                break;
            case RubikSide.LEFT:
                cube.counterClockwise();
                cube.y();
                break;
            case RubikSide.RIGHT:
                cube.clockwise();
                cube.y();
                break;
            case RubikSide.FRONT:
                cube.y();
                break;
            case RubikSide.BACK:
                cube.clockwise(2);
                cube.y();
            case RubikSide.TOP:
                // Fallthrough
            default:
                break;
        }
    }

    public void putDown()
    {
        switch (currentSide) {
            case RubikSide.TOP:
                cube.y(2);
                break;
            case RubikSide.LEFT:
                cube.clockwise();
                cube.y();
                break;
            case RubikSide.RIGHT:
                cube.counterClockwise();
                cube.y();
                break;
            case RubikSide.BACK:
                cube.y();
                break;
            case RubikSide.FRONT:
                cube.clockwise(2);
                cube.y();
            case RubikSide.DOWN:
                // Fallthrough
            default:
                break;
        }
    }

    public void clockwise()
    {
        // TODO Rotate outer Sides Clockwise
    }

    public void clockwise(int times)
    {
        for (int i = 0; i < times; i++) {
            clockwise();
        }
    }

    public void counterClockwise()
    {
        // TODO Rotate outer Sides CounterClockwise
    }

    public void counterClockwise(int times)
    {
        for (int i = 0; i < times; i++) {
            counterClockwise();
        }
    }

    public int getCurrentSide()
    {
        return currentSide;
    }
}
