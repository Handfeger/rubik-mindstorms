package de.michelvielmetter.lejos.util.thread;

import lejos.hardware.motor.BaseRegulatedMotor;

import java.util.MissingFormatArgumentException;

/**
 * ╔================================ MotorThread ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ MotorThread ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.util.helper
 * ║
 * ╚================================ MotorThread ====================================
 */
public class MotorThread extends Thread
{
    private String action;

    private int seconds;

    private final BaseRegulatedMotor motor;

    public MotorThread(BaseRegulatedMotor motor)
    {
        this.motor = motor;
    }

    public void run() throws MissingFormatArgumentException
    {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                switch (action) {
                    case "forward":
                        forward();
                        break;
                    default:
                        throw new MissingFormatArgumentException("Action \"" + action + "\" not supported");
                }
            } catch (InterruptedException e) {
                motor.stop();
                break;
            }
        }
    }

    private void forward() throws MissingFormatArgumentException, InterruptedException
    {
        if (motor == null) {
            throw new MissingFormatArgumentException("No Motor was given for forward command");
        }

        if (seconds == 0) {
            seconds = Integer.MAX_VALUE;
        }

        synchronized (motor) {
        motor.forward();
        Thread.sleep(seconds);}
        motor.stop();
    }

    public void setAction(String action)
    {
        this.action = action;
    }

    public void setSeconds(int seconds)
    {
        this.seconds = seconds;
    }
}
