package de.michelvielmetter.lejos.util.helper;

import de.michelvielmetter.lejos.util.thread.ColorSensorThread;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;

import java.util.LinkedList;

/**
 * ╔================================ ColorSensorHelper ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ ColorSensorHelper ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util.helper
 * ║
 * ╚================================ ColorSensorHelper ====================================
 */
public class ColorSensorHelper extends SensorHelper
{
    private EV3ColorSensor sensor;

    private ColorSensorThread thread;

    public ColorSensorHelper(Port port)
    {
        this.sensor = new EV3ColorSensor(port);
    }

    public ColorSensorThread moveMotorWhileColorLevel(MotorHelper[] motors, int level, String direction)
    {
        if (thread == null) {
            newThread();
        }

        LinkedList<Object> list = new LinkedList<>();
        list.push("moveMotor");
        list.push(motors);
        list.push(direction);

        thread.addColorIdMode(level, list);

        thread.start();

        return thread;
    }

    private ColorSensorThread newThread()
    {
        thread = new ColorSensorThread(sensor);

        return thread;
    }

    public void stop()
    {
        thread.interrupt();
    }
}
