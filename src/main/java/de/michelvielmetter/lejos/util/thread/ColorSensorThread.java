package de.michelvielmetter.lejos.util.thread;

import lejos.hardware.sensor.EV3ColorSensor;

import java.util.MissingFormatArgumentException;

/**
 * ╔================================ ColorSensorThread ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ ColorSensorThread ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util.thread
 * ║
 * ╚================================ ColorSensorThread ====================================
 */
public class ColorSensorThread extends Thread
{
    private final EV3ColorSensor sensor;

    private String mode;

    private String[] modeArray;

    public ColorSensorThread(EV3ColorSensor sensor)
    {
        super();

        this.sensor = sensor;
    }

    public void run() throws MissingFormatArgumentException
    {
        interruptedLoop:
        while (!Thread.currentThread().isInterrupted()) {
            try {
                switch (mode) {
                    case "motorColorId":
                        motorColorId();
                        break;
                    default:
                        Thread.currentThread().interrupt();
                        break interruptedLoop;
                }
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    public void motorColorId() throws InterruptedException, MissingFormatArgumentException
    {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("No more ColorId Motor matching");
        }

        if (modeArray.length == 0) {
            throw new MissingFormatArgumentException("No modeArray provided for motorColorId()");
        }

        synchronized (sensor) {

        }
    }

    public void setModeArray(String[] modeArray)
    {
        this.modeArray = modeArray;
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }
}
