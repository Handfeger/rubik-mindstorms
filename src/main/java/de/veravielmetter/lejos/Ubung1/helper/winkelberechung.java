package de.veravielmetter.lejos.Ubung1.helper;

import lejos.hardware.motor.EV3LargeRegulatedMotor;


public class winkelberechung {
    public static float ausererDurchmesser(int winkel,float breite){
        return 2*3.1415f*breite*winkel/360f;
    }
    public static void drehen(int winkel, float breite,float durchmesser, EV3LargeRegulatedMotor motor){
        float umfang=laengenberechnung.umfang(durchmesser);
        motor.rotate((int) (360 / umfang * ausererDurchmesser(winkel,breite)));
        try {
            Thread.sleep((int)(ausererDurchmesser(winkel,breite)/10)*999);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
