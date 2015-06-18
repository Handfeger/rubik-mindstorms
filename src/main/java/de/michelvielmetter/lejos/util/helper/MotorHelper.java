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

    private boolean running = false;

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

    public void backward()
    {
        backward(Integer.MAX_VALUE);
    }

    public void backward(int seconds)
    {
        move(seconds, "backward");
    }

    public void forward()
    {
        forward(Integer.MAX_VALUE);
    }

    public void forward(int seconds)
    {
        move(seconds, "forward");
    }

    public void move(String direction)
    {
        move(Integer.MAX_VALUE, direction);
    }

    public void move(int seconds, String direction)
    {
        if (running) {
            stop();
        }

        MotorThread thread = getNewThread();

        thread.setAction(direction);
        thread.setSeconds(seconds);

        running = true;

        thread.start();
    }

    public void stop()
    {
        getMotorThread().interrupt();
        running = false;
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

    public boolean isRunning()
    {
        return running;
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

    public void rotate(int degrees)
    {
        // TODO
    }
}
