package de.michelvielmetter.lejos.util.helper;

import lejos.hardware.port.Port;

/**
 * ╔================================ SensorHelper ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ SensorHelper ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util.helper
 * ║
 * ╚================================ SensorHelper ====================================
 */
public class SensorHelper
{
    public static int portToInt(Port port) throws IllegalArgumentException
    {
        switch (port.getName()) {
            case "S1":
                return 0;
            case "S2":
                return 1;
            case "S3":
                return 2;
            case "S4":
                return 3;
            default:
                throw new IllegalArgumentException("No such Sensor port");
        }
    }
}
