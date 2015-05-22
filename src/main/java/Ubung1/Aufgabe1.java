package ubung1;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import ubung1.helper.*;

/**
 * Created by Vera on 19.05.15.
 */
public class Aufgabe1 {
    public static void Main() {
        float durchmesser = 5.7f;
        float breite=12.1f;
        LCD.clear();
        LCD.drawString("Programm gestartet", 0, 3);
        //Button.waitForAnyPress();
       // LCD.drawString("Beschleunigen", 0, 5);
        Brick brick = BrickFinder.getDefault();
        EV3LargeRegulatedMotor rightMotor = new EV3LargeRegulatedMotor(brick.getPort("C"));
        EV3LargeRegulatedMotor leftMotor = new EV3LargeRegulatedMotor(brick.getPort("B"));
        /*rightMotor.forward();
        leftMotor.forward();
        Button.waitForAnyPress();
        rightMotor.stop();
        leftMotor.stop();
        LCD.clear();
        LCD.refresh();
        LCD.drawString(String.valueOf(100) + "cm fahren", 0, 5);
        Button.waitForAnyPress();
        laengenberechnung.cmFahren(100, durchmesser, rightMotor, leftMotor);
        Button.waitForAnyPress();
        LCD.clear();
        LCD.refresh();
        LCD.drawString("Drehen", 0, 5);
        winkelberechung.drehen(90, breite, durchmesser, rightMotor);*/
        Button.waitForAnyPress();
        LCD.clear();
        LCD.refresh();
        LCD.drawString("Quadrat", 0, 5);
        for(int i=0; i<4; i++) {
            laengenberechnung.cmFahren(50, durchmesser, rightMotor, leftMotor);
            winkelberechung.drehen(90, breite, durchmesser, rightMotor);
        }
        LCD.clear();
        LCD.refresh();
    }
}
