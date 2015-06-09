package de.michelvielmetter.lejos.util;

import de.michelvielmetter.lejos.util.helper.ColorSensorHelper;
import de.michelvielmetter.lejos.util.helper.MotorHelper;
import de.michelvielmetter.lejos.util.helper.SensorHelper;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.port.Port;

/**
 * ╔================================ LejosHelper ====================================
 * ║
 * ║ Create Date: 02.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ LejosHelper ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util
 * ║
 * ╚================================ LejosHelper ====================================
 */
public class LejosHelper
{
    private static boolean init = false;

    private static KeyBinder keyBinder;

    private static MotorHelper[] motors = new MotorHelper[4];
    private static SensorHelper[] sensors = new SensorHelper[4];

    public static MotorHelper getLargeMotor(String port)
    {
        return getLargeMotor(port, BrickFinder.getDefault());
    }

    public static MotorHelper getLargeMotor(String port, Brick brick)
    {
        return getLargeMotor(brick.getPort(port));
    }

    public static MotorHelper getLargeMotor(Port port)
    {
        return getMotor(port, "large");
    }

    public static MotorHelper getMediumMotor(String port)
    {
        return getMediumMotor(port, BrickFinder.getDefault());
    }

    public static MotorHelper getMediumMotor(String port, Brick brick)
    {
        return getMediumMotor(brick.getPort(port));
    }

    public static MotorHelper getMediumMotor(Port port)
    {
        return getMotor(port, "medium");
    }

    public static MotorHelper getMotor(Port port, String size) throws IllegalArgumentException
    {
        if (motors[MotorHelper.portToInt(port)] == null) {
            motors[MotorHelper.portToInt(port)] = new MotorHelper(port, size);
        }

        return motors[MotorHelper.portToInt(port)];
    }

    public static ColorSensorHelper getColorSensor(String port)
    {
        return getColorSensor(port, BrickFinder.getDefault());
    }

    public static ColorSensorHelper getColorSensor(String port, Brick brick)
    {
        return getColorSensor(brick.getPort(port));
    }

    public static ColorSensorHelper getColorSensor(Port port)
    {
        return (ColorSensorHelper) getSensor(port, "color");
    }

    public static SensorHelper getSensor(Port port, String type) throws IllegalArgumentException
    {
        if (sensors[SensorHelper.portToInt(port)] == null) {
            switch (type) {
                case "color":
                    sensors[SensorHelper.portToInt(port)] = new ColorSensorHelper(port);
                default:
                    throw new IllegalArgumentException("This Sensor Type is not supported");
            }
        }

        return sensors[SensorHelper.portToInt(port)];
    }

    public static void init()
    {
        if (init) {
            return;
        }
        keyBinder = new KeyBinder(BrickFinder.getDefault());

        init = true;
    }
}
