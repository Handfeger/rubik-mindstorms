package de.michelvielmetter.lejos;


import de.michelvielmetter.lejos.util.ColorName;
import de.michelvielmetter.lejos.util.Display;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

/**
 * ╔================================ Uebung1 ====================================
 * ║
 * ║ Create Date: 19.05.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ Uebung1 ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos
 * ║
 * ╚================================ Uebung1 ====================================
 */
public class Uebung1
{
    private final Brick brick;
    private final Display display;
    private final LED led;


    public Uebung1()
    {
        brick = BrickFinder.getDefault();
        led = brick.getLED();
        display = new Display(brick);


//        sensorRGB();
        sensorColorId();
    }

    public void sensorColorId()
    {
        led.setPattern(3);
        display.setAutoRefresh(true);

        EV3ColorSensor sensor = new EV3ColorSensor(brick.getPort("S3"));
        SensorMode colorId = sensor.getColorIDMode();
        float[] sample = new float[colorId.sampleSize()];

        while (Button.ESCAPE.isUp()) {
            display.clear();
            colorId.fetchSample(sample, 0);

            display.draw(Integer.toString(Float.floatToIntBits(sample[0])), 1, 5);
        }
    }

    public void sensorRGB()
    {
        led.setPattern(2);
        display.setAutoRefresh(false);

        EV3ColorSensor sensor = new EV3ColorSensor(brick.getPort("S3"));
        SensorMode rgb = sensor.getRGBMode();
        float[] sample = new float[rgb.sampleSize()];

        while (Button.ESCAPE.isUp()) {
            display.clear();
            rgb.fetchSample(sample, 0);
            display.draw(Float.toString(sample[0]), 1, 5);
            display.draw(Float.toString(sample[1]), 20, 5);
            display.draw(Float.toString(sample[2]), 40, 5);
            display.refresh();
        }
    }
}
