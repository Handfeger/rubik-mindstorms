package de.michelvielmetter.lejos.uebung2;

import de.michelvielmetter.lejos.util.Display;
import lejos.hardware.Brick;
import lejos.hardware.LED;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * ╔================================ Main ====================================
 * ║
 * ║ Create Date: 01.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ Main ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos
 * ║
 * ╚================================ Main ====================================
 */
public class TestThread implements Runnable
{
    private String threadName;
    private Brick brick;
    private LED led;
    private Display display;

    public TestThread(String name)
    {
        threadName = name;

        System.out.println("Creating normal Thread: " + threadName);
    }

    public TestThread(String name, Brick brick)
    {
        threadName = name;
        this.brick = brick;
//        led = brick.getLED();
//        display = new Display(brick);

        System.out.println("Creating Brick Thread: " + threadName);
    }

    public void run()
    {
        System.out.println("Running Thread: " + threadName);

//        led.setPattern(3);
        try {
            if (brick != null) {
                display.setAutoRefresh(true);

                EV3ColorSensor sensor = new EV3ColorSensor(brick.getPort("S3"));
                SensorMode colorId = sensor.getColorIDMode();
                float[] sample = new float[colorId.sampleSize()];

                for (int i = 0; i < 10; i++) {
//                display.clear();
                    colorId.fetchSample(sample, 0);
                    System.out.println("Thread " + threadName + " Color " + Integer.toString(Float.floatToIntBits(sample[0])));
//                display.draw(Integer.toString(Float.floatToIntBits(sample[0])), 1, 5);
                    Thread.sleep(1000);
                }
            } else {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Thread " + threadName + " iteration " + Integer.toString(i));
                    Thread.sleep(1000);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Thread: " + threadName + " interrupted");
        }

    }
}
