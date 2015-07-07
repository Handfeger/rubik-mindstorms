package de.michelvielmetter.lejos.rubiksolver;

import de.michelvielmetter.lejos.util.Display;
import de.michelvielmetter.lejos.util.LejosHelper;
import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.KeyListener;
import lejos.hardware.sensor.EV3ColorSensor;

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

        LejosHelper.getKeyBinder().addKey("Up", "Read Color", new KeyListener()
        {
            @Override
            public void keyPressed(Key k)
            {
                if (k.getName().equals("Up")) {
                    cube.readSides();
                }
            }

            @Override
            public void keyReleased(Key k)
            {
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
                    String test1="UURUULBBLFFBDRRUBDURDLFRBLLLUFRDDRBBRFLFLDFUDUBFFBDRLD";
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
        //TODO check if calculating moves worked, reread cube if not
        //TODO convert Algorithm moves to robot moves

        // TODO Solve
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
