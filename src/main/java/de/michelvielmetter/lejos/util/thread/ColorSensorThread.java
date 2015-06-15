package de.michelvielmetter.lejos.util.thread;

import de.michelvielmetter.lejos.util.helper.MotorHelper;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

import java.util.LinkedList;
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

    private LinkedList<LinkedList<Object>> modeList;

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
                    case "colorId":
                        colorId();
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

    public void addColorIdMode(int colorId, LinkedList<Object> action)
    {
        setColorIdMode();

        modeList.set(colorId, action);
    }

    private void setColorIdMode()
    {
        setColorIdMode(false);
    }

    private void colorId() throws InterruptedException, MissingFormatArgumentException
    {
        if (Thread.currentThread().isInterrupted()) {
            throw new InterruptedException("No more ColorId Motor matching");
        }

        if (modeList.size() == 0) {
            throw new MissingFormatArgumentException("No modeList given for ColorId");
        }

        synchronized (sensor) {
            SensorMode fetcher = sensor.getColorIDMode();
            float[] sample = new float[fetcher.sampleSize()];
            LinkedList<MotorHelper> runningMotors = new LinkedList<>();

            while (!Thread.currentThread().isInterrupted()) {
                fetcher.fetchSample(sample, 0);

                if (modeList.get((int) sample[0]) != null) {
                    switch ((String) modeList.get((int) sample[0]).get(0)) {
                        case "moveMotor":
                            MotorHelper[] motors = (MotorHelper[]) modeList.get((int) sample[0]).get(1);
                            String direction = (String) modeList.get((int) sample[0]).get(2);

                            for (MotorHelper motor : motors) {
                                runningMotors.remove(runningMotors.indexOf(motor));
                            }

                            while (runningMotors.size() > 0) {
                                runningMotors.pop().stop();
                            }

                            for (MotorHelper motor : motors) {
                                motor.move(direction);
                                runningMotors.push(motor);
                            }

                            break;
                        default:
                            throw new MissingFormatArgumentException("No matching mode for colorId");
                    }
                }
            }
        }
    }

    private void setColorIdMode(boolean force)
    {
        if (force || !this.mode.equals("colorId")) {
            this.mode = "colorId";
            this.modeList = new LinkedList<>();
        }
    }

    public void setMode(String mode)
    {
        this.mode = mode;
    }
}
