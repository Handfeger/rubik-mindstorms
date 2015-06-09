package de.michelvielmetter.lejos.uebung2;

import de.michelvielmetter.lejos.util.LejosHelper;
import de.michelvielmetter.lejos.util.helper.ColorSensorHelper;
import de.michelvielmetter.lejos.util.helper.MotorHelper;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.robotics.Color;

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
public class Main
{
    public Main(String name)
    {
        LejosHelper.init();
//        MotorHelper[] leftMotor = {LejosHelper.getLargeMotor("B")};
//        MotorHelper[] rightMotor = {LejosHelper.getLargeMotor("C")};
//        ColorSensorHelper lightSensor = LejosHelper.getColorSensor("S2");
//
//        lightSensor.moveMotorWhileColorLevel(leftMotor, Color.WHITE);
//        lightSensor.moveMotorWhileColorLevel(rightMotor, Color.BLACK);
    }
}
