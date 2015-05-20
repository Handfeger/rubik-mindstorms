package ubung1.helper;


import lejos.hardware.motor.EV3LargeRegulatedMotor;

public class laengenberechnung {
    public static float umfang(float durchmesser){
        float umfang=durchmesser*3.141562f;
        return umfang;
    }
    public static void cmFahren(float cmleange, float durchmesser, EV3LargeRegulatedMotor motorrechts,EV3LargeRegulatedMotor motorlinks) {
        float umfang = umfang(durchmesser);
        motorrechts.rotate((int) (360 / umfang * cmleange), true);
        motorlinks.rotate((int) (360 / umfang * cmleange), true);
    }
}
