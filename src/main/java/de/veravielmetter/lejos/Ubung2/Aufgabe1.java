package de.veravielmetter.lejos.Ubung2;

import de.michelvielmetter.lejos.util.Display;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.LED;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

/**
 * Created by Vera on 09.06.15.
 */
public class Aufgabe1
{
    private final Brick brick;
    private final Display display;
    private final LED led;


    public Aufgabe1()
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
