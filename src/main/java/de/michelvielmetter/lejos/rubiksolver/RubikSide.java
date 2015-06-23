package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.ColorName;
import de.michelvielmetter.lejos.util.Display;
import lejos.robotics.Color;

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
    // The Sides that will be seen from the color picker!
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

    public boolean putTop()
    {
        switch (currentSide) {
            case RubikSide.DOWN:
                return cube.y(2);
            case RubikSide.LEFT:
                return cube.counterClockwise() && cube.y();
            case RubikSide.RIGHT:
                return cube.clockwise() && cube.y();
            case RubikSide.FRONT:
                return cube.y();
            case RubikSide.BACK:
                return cube.clockwise(2) && cube.y();
            case RubikSide.TOP:
                return true;
            default:
                return false;
        }
    }

    public boolean putDown()
    {
        switch (currentSide) {
            case RubikSide.TOP:
                return cube.y(2);
            case RubikSide.LEFT:
                return cube.clockwise() && cube.y();
            case RubikSide.RIGHT:
                return cube.counterClockwise() && cube.y();
            case RubikSide.BACK:
                return cube.y();
            case RubikSide.FRONT:
                return cube.clockwise(2) && cube.y();
            case RubikSide.DOWN:
                return true;
            default:
                return false;
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

    public void print(Display display)
    {
        display.clear();
        display.drawString("Side: " + getSideString(), 1);

        char[] line = new char[3];

        for (int i = 0; i < 3; i++) {
            line[i] = ColorName.colorChar(outerColors[i]);
        }
        display.drawString(String.valueOf(line), 2);

        line[0] = ColorName.colorChar(outerColors[7]);
        line[1] = ColorName.colorChar(middleColor);
        line[2] = ColorName.colorChar(outerColors[3]);
        display.drawString(String.valueOf(line), 3);

        line[0] = ColorName.colorChar(outerColors[6]);
        line[1] = ColorName.colorChar(outerColors[5]);
        line[2] = ColorName.colorChar(outerColors[4]);
        display.drawString(String.valueOf(line), 4);
    }

    public String getSideString()
    {
        return RubikSide.getSideString(currentSide);
    }

    public static String getSideString(int side)
    {
        switch (side) {
            case 0:
                return "TOP";
            case 2:
                return "DOWN";
            case 3:
                return "FRONT";
            case 1:
                return "BACK";
            case 4:
                return "LEFT";
            case 5:
                return "RIGHT";
            default:
                return "unknown";
        }
    }

    public boolean getColors()
    {
        if (!putTop()) {
            return false;
        }

        // TEST DATA
        middleColor = Color.GREEN;
        outerColors[0] = Color.WHITE;
        outerColors[1] = Color.RED;
        outerColors[2] = Color.RED;
        outerColors[3] = Color.YELLOW;
        outerColors[4] = Color.GREEN;
        outerColors[5] = Color.YELLOW;
        outerColors[6] = Color.BLUE;
        outerColors[7] = Color.WHITE;

        if (currentSide == RubikSide.BACK) {
            outerColors[0] = Color.GREEN;
        }

        return true;
    }
}
