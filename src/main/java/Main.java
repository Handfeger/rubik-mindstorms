/**
 * ╔================================ Main ====================================
 * ║
 * ║ Create Date: 14.05.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ Main ====================================
 * ║
 * ║ Package:   PACKAGE_NAME
 * ║
 * ╚================================ Main ====================================
 */
public class Main
{
    public static void main(String[] args)
    {
        try {
            System.out.println("test start");
            de.michelvielmetter.lejos.uebung2.Main thread = new de.michelvielmetter.lejos.uebung2.Main("test");
            Thread.sleep(Integer.MAX_VALUE);
            System.out.println("test end");
        } catch (InterruptedException e) {
            System.exit(0);
        }
    }
}
