package de.michelvielmetter.lejos.util.helper;

import de.michelvielmetter.lejos.util.thread.MotorThread;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;

/**
 * ╔================================ MotorHelper ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ MotorHelper ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util.helper
 * ║
 * ╚================================ MotorHelper ====================================
 */
public class MotorHelper
{
    private BaseRegulatedMotor motor;

    private MotorThread thread;

    public MotorHelper(Port port, String size) throws IllegalArgumentException
    {
        switch (size) {
            case "large":
                motor = new EV3LargeRegulatedMotor(port);
                break;
            case "medium":
                motor = new EV3MediumRegulatedMotor(port);
                break;
            default:
                throw new IllegalArgumentException("Only large & medium motors accepted");
        }
    }

    public void forward()
    {
        forward(Integer.MAX_VALUE);
    }

    public void forward(int seconds)
    {
        MotorThread thread = getNewThread();

        thread.setAction("forward");
        thread.setSeconds(seconds);

        thread.start();
    }

    public MotorThread getMotorThread()
    {
        return thread;
    }

    private MotorThread getNewThread()
    {
        if (thread != null && thread.isAlive()) {
            thread.interrupt();
        }

        thread = new MotorThread(motor);

        return thread;
    }

    public static int portToInt(Port port) throws IllegalArgumentException
    {
        switch (port.getName()) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
            default:
                throw new IllegalArgumentException("No such Motor port");
        }
    }
}
