package cs.min2phase;

import de.michelvielmetter.lejos.util.Display;
import lejos.hardware.Brick;

/**
 * Created by Vera on 28.06.15.
 */
public class MainTest {
    public MainTest(Display display){
        display.drawString("Berechnung gestartet",1);
        //Test Anfangsstrings
        boolean inverse=false;
        String test1="UURUULBBLFFBDRRUBDURDLFRBLLLUFRDDRBBRFLFLDFUDUBFFBDRLD";
        String test2="UUFUUFUUFRRRRRRRRRFFDFFDFFDDDBDDBDDBLLLLLLLLLUBBUBBUBB";
        int maxDepth = 21, maxTime = 5;
        Search search=new Search();
        long t = System.nanoTime();
        int mask=inverse ? 6 : 4;
        String result = search.solution(test1, maxDepth, 100, 0, mask);
        long n_probe = search.numberOfProbes();
        while (result.startsWith("Error 8") && ((System.nanoTime() - t) < maxTime * 1.0e9)) {
            result = search.next(100, 0, mask);
            n_probe += search.numberOfProbes();
        }
        t = System.nanoTime() - t;

        // +++++++++++++++++++ Replace the error messages with more meaningful ones in your language ++++++++++++++++++++++
        if (result.contains("Error")) {
            switch (result.charAt(result.length() - 1)) {
                case '1':
                    result = "There are not exactly nine facelets of each color!";
                    break;
                case '2':
                    result = "Not all 12 edges exist exactly once!";
                    break;
                case '3':
                    result = "Flip error: One edge has to be flipped!";
                    break;
                case '4':
                    result = "Not all 8 corners exist exactly once!";
                    break;
                case '5':
                    result = "Twist error: One corner has to be twisted!";
                    break;
                case '6':
                    result = "Parity error: Two corners or two edges have to be exchanged!";
                    break;
                case '7':
                    result = "No solution exists for the given maximum move number!";
                    break;
                case '8':
                    result = "Timeout, no solution found within given maximum time!";
                    break;
            }
        }
        display.drawString(result,2);

    }
}
