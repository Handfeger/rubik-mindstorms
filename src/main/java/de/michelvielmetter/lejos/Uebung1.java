package de.michelvielmetter.lejos;

import lejos.hardware.motor.Motor;

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
    public Uebung1()
    {
        try {
            Motor.B.forward();
            Motor.C.forward();
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.print("interrupted");
        }


    }
}
