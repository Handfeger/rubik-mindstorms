import de.michelvielmetter.lejos.MainThread;
import de.michelvielmetter.lejos.util.LejosHelper;

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
        LejosHelper.init();
        MainThread programm = new MainThread();
        programm.start();
    }
}
