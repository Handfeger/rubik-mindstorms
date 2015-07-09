package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.ColorName;
import de.michelvielmetter.lejos.util.Display;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

import javax.activity.InvalidActivityException;

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
    public final static int BACK = 1;
    public final static int DOWN = 2;
    public final static int FRONT = 3;
    public final static int LEFT = 4;
    public final static int RIGHT = 5;

    public void setCurrentSide(int currentSide)
    {
        this.currentSide = currentSide;
    }


    private int middleColor = -1;

    private int[] outerColors = {-1, -1, -1, -1, -1, -1, -1, -1};

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
                return cube.x(2);
            case RubikSide.LEFT:
                return cube.counterClockwise() && cube.x();
            case RubikSide.RIGHT:
                return cube.clockwise() && cube.x();
            case RubikSide.FRONT:
                return cube.x();
            case RubikSide.BACK:
                return cube.clockwise(2) && cube.x();
            case RubikSide.TOP:
                return true;
            default:
                return false;
        }
    }

    public boolean putDown() {
        try {
            cube.getSolver().getArm().goToZero();
        } catch (InvalidActivityException e) {
            return false;
        }
        switch (currentSide) {
            case RubikSide.TOP:
                return cube.x(2, false);
            case RubikSide.LEFT:
                return cube.clockwise() && cube.x(false);
            case RubikSide.RIGHT:
                return cube.counterClockwise() && cube.x(false);
            case RubikSide.BACK:
                return cube.clockwise(2) && cube.x(false);
            case RubikSide.FRONT:
                return cube.x(false);
            case RubikSide.DOWN:
                return true;
            default:
                return false;
        }
    }

    public void clockwise()
    {
        int[] newArr = new int[8];
        for (int i = 0; i < 8; i++) {
            newArr[(i + 2) % 8] = outerColors[i];
        }

        outerColors = newArr;
    }

    public void clockwise(int times)
    {
        for (int i = 0; i < times; i++) {
            clockwise();
        }
    }

    public void counterClockwise()
    {
        int[] newArr = new int[8];
        for (int i = 0; i < 8; i++) {
            newArr[(i + 6) % 8] = outerColors[i];
        }

        outerColors = newArr;
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

    public int[] getOuterColors()
    {
        return outerColors;
    }

    public int[] convertToBlockColors()
    {
        int[] blockColors = new int[9];
        for (int i = 0; i < 3; i++) {
            blockColors[i] = outerColors[i];
        }
        blockColors[3] = outerColors[7];
        blockColors[4] = middleColor;
        blockColors[5] = outerColors[3];
        blockColors[6] = outerColors[6];
        blockColors[7] = outerColors[5];
        blockColors[8] = outerColors[4];
        return blockColors;
    }

    public int getMiddleColor()
    {
        return middleColor;
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

    public boolean readColors()
    {
        if (!putTop()) {
            return false;
        }

        ColorArm arm = cube.getSolver().getColorArm();
        Table table = cube.getSolver().getTable();
        float[] sample = new float[1];

        SensorMode mode = cube.getSolver().getColorSensor().getColorIDMode();
        int sampleInt = 0;

        try {
            arm.goToPos(ColorArm.POS_MIDDLE);
            mode.fetchSample(sample, 0);
            sampleInt = (int) sample[0];
            if (sampleInt == Color.NONE || sampleInt == Color.BROWN) {
                sampleInt = Color.BLACK;
            }
            middleColor = sampleInt;

            for (int i = 0; i < 8; i++) {
                if (i % 2 == 0) {
                    arm.goToPos(ColorArm.POS_EDGE);
                    if (i != 0) {
                        table.goToPos(-Table.POS_EDGE);
                    }
                } else {
                    arm.goToPos(ColorArm.POS_CORNER);
                    table.goToPos(-Table.POS_CORNER);
                }
                mode.fetchSample(sample, 0);
                sampleInt = (int) sample[0];
                if (sampleInt == Color.NONE || sampleInt == Color.BROWN) {
                    sampleInt = Color.BLACK;
                }
                outerColors[(i + 5) % 8] = sampleInt;
            }

            table.goToPos(-Table.POS_EDGE);
            arm.goToZero();

        } catch (InvalidActivityException e) {
            return false;
        }

        return true;
    }
}
