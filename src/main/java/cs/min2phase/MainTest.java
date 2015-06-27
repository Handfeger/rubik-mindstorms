package cs.min2phase;

/**
 * Created by Vera on 28.06.15.
 */
public class MainTest {
    public MainTest(){
        int maxDepth = 21, maxTime = 5;
        Search search=new Search();
        int mask=0;
        String result = search.solution("UUUUUURRRRRRFFFFFFDDDDDDLLLLLLBBBBBB", maxDepth, 100, 0, mask);
        System.out.print(result);
    }
}
