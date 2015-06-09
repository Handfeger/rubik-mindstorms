package de.michelvielmetter.lejos.util.helper;

import de.michelvielmetter.lejos.util.thread.ColorSensorThread;
import de.michelvielmetter.lejos.util.thread.MotorThread;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

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

    private LinkedList<ColorSensorThread> threads = new LinkedList<>();

    public ColorSensorHelper(Port port)
    {
        this.sensor = new EV3ColorSensor(port);
    }

    public ColorSensorThread moveMotorWhileColorLevel(MotorHelper[] motors, int level)
    {
        SensorMode mode = sensor.getColorIDMode();

        ColorSensorThread thread = newThread();

        thread.setMode("motorColorId");

        return thread;
    }

    private ColorSensorThread newThread()
    {
        ColorSensorThread thread = new ColorSensorThread(sensor);

        threads.push(thread);

        return thread;
    }

    public void stop()
    {
        for (ColorSensorThread thread : threads) {
            thread.interrupt();
        }

        threads = new LinkedList<>();
    }
}
