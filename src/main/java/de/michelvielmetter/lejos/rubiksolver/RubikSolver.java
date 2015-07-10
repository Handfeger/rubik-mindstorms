package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.Display;
import de.michelvielmetter.lejos.util.LejosHelper;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;

import javax.activity.InvalidActivityException;

/**
 * ╔================================ RubikSolver ====================================
 * ║
 * ║ Create Date: 17.06.2015
 * ║
 * ║ Author(s): Michel Vielmetter <contact@michelvielmetter.de>
 * ║ Copyright: Michel Vielmetter 2015
 * ║ Licence:   MIT License
 * ║
 * ╠================================ RubikSolver ====================================
 * ║
 * ║ Package:   de.michelvielmetter.lejos.rubiksolver
 * ║
 * ╚================================ RubikSolver ====================================
 */
public class RubikSolver extends Thread
{
    private Brick brick;

    private Arm arm;
    private ColorArm colorArm;
    private Table table;
    private RubikCube cube;
    private Display display;

    private EV3ColorSensor colorSensor;

    public final boolean debug;

    public RubikSolver()
    {
        this(false);
    }

    public RubikSolver(boolean debug)
    {
        brick = BrickFinder.getDefault();
        arm = new Arm(this);
        colorArm = new ColorArm(this);
        table = new Table(this);
        cube = new RubikCube(this);
        display = LejosHelper.getDisplay();

        colorSensor = new EV3ColorSensor(brick.getPort("S4"));

        arm.start();
        colorArm.start();
        table.start();

        this.debug = debug;
    }

    public void run()
    {
        // Debug
        if (debug) {
            LejosHelper.getKeyBinder().addKey("Enter", "Debug", new KeyListener()
            {
                private int currentSide = RubikSide.TOP;

                @Override
                public void keyPressed(Key k)
                {
                    LejosHelper.getKeyBinder().setInMenu(true);
                    printSide(cube.getSide(currentSide++));
                    if (currentSide > 5) {
                        currentSide = RubikSide.TOP;
                    }
                }

                @Override
                public void keyReleased(Key k)
                {

                }
            });
        }

        // TODO find zero positions
        arm.findZero();
        new EV3UltrasonicSensor(brick.getPort("S1"));
        LejosHelper.getKeyBinder().addKey("Up", "Read Color", new KeyListener() {
            @Override
            public void keyPressed(Key k) {
                if (k.getName().equals("Up")) {
                    cube.readSides();
                }
//                try {
//                    colorArm.goToPos(ColorArm.POS_MIDDLE);
//                    Thread.sleep(4000);
//                    colorArm.goToPos(ColorArm.POS_EDGE+30);
//                    Thread.sleep(4000);
//                    colorArm.goToPos(ColorArm.POS_CORNER);
//                    table.goToPos(Table.POS_CORNER);
//                    Thread.sleep(4000);
//                    table.goToZero();
//                    colorArm.goToZero();
//                } catch (InvalidActivityException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void keyReleased(Key k) {
            }
        });
        LejosHelper.getKeyBinder().addKey("Down", "Algorithm with Input-String", new KeyListener()
        {
            @Override
            public void keyPressed(Key k)
            {
                if (k.getName().equals("Down")) {
                    display.clear();
                    Algorithm algorithm=new Algorithm(true);
                    //String test1="UURUULBBLFFBDRRUBDURDLFRBLLLUFRDDRBBRFLFLDFUDUBFFBDRLD";
                    String moves=algorithm.run(cube, display);
                    display.clear();
                    System.out.println(moves);
                }
            }

            @Override
            public void keyReleased(Key k)
            {
            }
        });

        LejosHelper.getKeyBinder().addKey("Left", "Solve", new KeyListener()
        {
            @Override
            public void keyPressed(Key k)
            {
                if (k.getName().equals("Left")) {
                    display.clear();
                    //Algorithm algorithm=new Algorithm(true);
                    //String test1="UURUULBBLFFBDRRUBDURDLFRBLLLUFRDDRBBRFLFLDFUDUBFFBDRLD";
                    //String moveString=algorithm.runDebug(test1, display);
                    display.clear();
                    Moves moves=new Moves(cube,"R2 U' R2 B2 U  D  F' D' R' B2 U' L2 F2 U  F2 U2 L2 U2 R2 B2 D  (21f)");
                }
            }

            @Override
            public void keyReleased(Key k)
            {
            }
        });


        LejosHelper.getKeyBinder().addKey("Right", "All", new KeyListener()
        {

            @Override
            public void keyPressed(Key k)
            {

                if (k.getName().equals("Right")) {
                    display.clear();

                    String moveString;
                    Algorithm algorithm = new Algorithm(false);

                    do {
                        if (!cube.readSides()) {
                            display.drawString("Read sides failed");
                            return;
                        }
                        display.clear();
                        display.drawString("Calculating Moves...");

                        moveString = algorithm.run(cube, display);

                    } while (algorithm.isCubeError());

                    display.clear();
                    display.drawString("Solving...");

                    if (!algorithm.isMovesCalculated()) {
                        return;
                    }
                    Moves moves =new Moves(cube,moveString);

                    try {
                        arm.goToZero();
                    } catch (InvalidActivityException e) {
                        e.printStackTrace();
                    }

                    display.clear();
                    display.drawString(moves.getMovesNumberString());
                }
            }

            @Override
            public void keyReleased(Key k)
            {

            }
        });

    }

    public void printSide(RubikSide side)
    {
        side.print(display);
    }

    public Brick getBrick()
    {
        return brick;
    }

    public Table getTable()
    {
        return table;
    }

    public ColorArm getColorArm()
    {
        return colorArm;
    }

    public Arm getArm()
    {
        return arm;
    }

    public EV3ColorSensor getColorSensor()
    {
        return colorSensor;
    }
}
