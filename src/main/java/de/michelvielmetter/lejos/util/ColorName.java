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
}
