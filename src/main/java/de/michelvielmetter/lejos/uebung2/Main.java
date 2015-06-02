package de.michelvielmetter.lejos.uebung2;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
        try {
            ExecutorService service = Executors.newCachedThreadPool();
            Brick brick = BrickFinder.getDefault();
            TestThread thread1 = service.submit(new TestThread("Test-1"));
            TestThread thread2 = service.submit(new TestThread("Test-2"));



            Thread.sleep(3000);

            Thread.sleep(1000);

        } catch (InterruptedException e) {
            System.out.print("Alles zuende");
        }
    }
}
