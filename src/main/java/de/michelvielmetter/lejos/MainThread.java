package de.michelvielmetter.lejos;

import de.michelvielmetter.lejos.util.LejosHelper;
import de.veravielmetter.lejos.Ubung2.Aufgabe1;

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


        new Aufgabe1();


        while (true) {
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                System.exit(0);
            }
        }
    }
}
