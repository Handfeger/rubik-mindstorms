package de.michelvielmetter.lejos;

import de.michelvielmetter.lejos.util.LejosHelper;
import de.michelvielmetter.lejos.util.helper.ColorSensorHelper;
import de.michelvielmetter.lejos.util.helper.MotorHelper;
import lejos.robotics.Color;

/**
 * ╔================================ MainThread ====================================
 * ║
 * ║ Create Date: 09.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ MainThread ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos
 * ║
 * ╚================================ MainThread ====================================
 */
public class MainThread extends Thread
{
    public void run()
    {
        LejosHelper.init();


        MotorHelper[] leftMotor = {LejosHelper.getLargeMotor("B")};
        MotorHelper[] rightMotor = {LejosHelper.getLargeMotor("C")};
        ColorSensorHelper lightSensor = LejosHelper.getColorSensor("S3");

        lightSensor.moveMotorWhileColorLevel(leftMotor, Color.WHITE, "forward");
        lightSensor.moveMotorWhileColorLevel(rightMotor, Color.BLACK, "forward");

//        for (MotorHelper motor : leftMotor) {
//            motor.forward();
//        }
//
//        for (MotorHelper motor: rightMotor) {
//            motor.forward();
//        }

        while (true) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.exit(0);
            }
        }
    }
}
