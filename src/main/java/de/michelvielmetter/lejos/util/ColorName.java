package de.michelvielmetter.lejos.util;

import lejos.robotics.Color;

/**
 * ╔================================ ColorName ====================================
 * ║
 * ║ Create Date: 19.05.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ ColorName ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util
 * ║
 * ╚================================ ColorName ====================================
 */
public class ColorName
{
    public static String nameColor(int ColorId)
    {
        switch (ColorId) {
            case Color.RED:
                return "RED";
            case Color.GREEN:
                return "GREEN";
            case Color.BLUE:
                return "BLUE";
            case Color.YELLOW:
                return "YELLOW";
            case Color.MAGENTA:
                return "MAGENTA";
            case Color.ORANGE:
                return "ORANGE";
            case Color.WHITE:
                return "WHITE";
            case Color.BLACK:
                return "BLACK";
            case Color.PINK:
                return "PINK";
            case Color.GRAY:
                return "GRAY";
            case Color.LIGHT_GRAY:
                return "LIGHT_GRAY";
            case Color.DARK_GRAY:
                return "DARK_GRAY";
            case Color.CYAN:
                return "CYAN";
            case Color.BROWN:
                return "BROWN";
            case Color.NONE:
                return "NONE";
            default:
                return "unknown";
        }
    }

    public static char colorChar(int ColorId)
    {
        switch (ColorId) {
            case Color.RED:
                return 'R';
            case Color.GREEN:
                return 'G';
            case Color.BLUE:
                return 'B';
            case Color.YELLOW:
                return 'Y';
            case Color.MAGENTA:
                return 'M';
            case Color.ORANGE:
                return 'O';
            case Color.WHITE:
                return 'W';
            case Color.BLACK:
                return 'b';
            case Color.PINK:
                return 'p';
            case Color.GRAY:
                return 't';
            case Color.LIGHT_GRAY:
                return 'g';
            case Color.DARK_GRAY:
                return 'd';
            case Color.CYAN:
                return 'c';
            case Color.BROWN:
                return 'n';
            case Color.NONE:
                return '-';
            default:
                return '-';
        }
    }
}
